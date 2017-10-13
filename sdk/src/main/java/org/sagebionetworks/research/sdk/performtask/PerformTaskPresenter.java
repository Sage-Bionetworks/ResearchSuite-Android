/*
 *    Copyright 2017 Sage Bionetworks
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package org.sagebionetworks.research.sdk.performtask;

import android.arch.lifecycle.LifecycleObserver;
import android.support.annotation.NonNull;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import org.sagebionetworks.research.sdk.domain.UseCase;
import org.sagebionetworks.research.sdk.performtask.step.StepChangeListenerFactory;
import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.step.StepChangeListener;
import org.sagebionetworks.research.sdk.step.ui.UIAction.UIActionType;
import org.sagebionetworks.research.sdk.task.Task;
import org.sagebionetworks.research.sdk.task.navigation.rule.StepNavigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by liujoshua on 10/4/2017.
 */

public class PerformTaskPresenter implements PerformTaskContract.Presenter, LifecycleObserver {
    public final class TaskState {
        public final Step step;
        public final TaskResult taskResult;

        public TaskState(Step step, TaskResult taskResult) {
            this.step = step;
            this.taskResult = taskResult;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformTaskPresenter.class);

    private final String taskIdentifier;

    private final UseCase<String, Task> getTaskUseCase;
    private final UseCase<TaskState, Step> getNextStepUseCase;
    private final UseCase<TaskState, Step> getPreviousStepUseCase;
    private final List<StepChangeListener> stepChangeListeners;

    private Task task;
    private TaskResult result;
    private PerformTaskContract.View view;

//    private StepNavigator navigator;

    private Step currentStep;


    public PerformTaskPresenter(@NonNull String taskIdentifier,
                                @NonNull UseCase<String, Task> getTaskUseCase,
                                @NonNull UseCase<TaskState, Step> getNextStepUseCase,
                                @NonNull UseCase<TaskState, Step> getPreviousStepUseCase,
                                @NonNull List<StepChangeListener> stepChangeListeners
    ) {
        checkArgument(!Strings.isNullOrEmpty(taskIdentifier),
                "taskIdentifier cannot be null or rsi_empty");
        checkNotNull(getTaskUseCase, "getTaskUseCase cannot be null");
        checkNotNull(getNextStepUseCase, "getNextStepUseCase cannot be null");
        checkNotNull(getPreviousStepUseCase, "getPreviousStepUseCase cannot be null");
        checkNotNull(stepChangeListeners, "stepChangeListeners cannot be null");

        this.taskIdentifier = taskIdentifier;
        this.getTaskUseCase = getTaskUseCase;
        this.getNextStepUseCase = getNextStepUseCase;
        this.getPreviousStepUseCase = getPreviousStepUseCase;
        this.stepChangeListeners = stepChangeListeners;

        this.currentStep = null;
    }

    @Override
    public void handleAction(@UIActionType String actionType) {
        TaskState taskState = new TaskState(currentStep, result);
        switch (actionType) {
            case UIActionType.NEXT:
                this.getNextStepUseCase
                        .execute(taskState, this::showNextStep, this::showStepError);
                break;
            case UIActionType.PREVIOUS:
                this.getPreviousStepUseCase
                        .execute(taskState, this::showPreviousStep, this::showStepError);
                break;
            default:
                LOGGER.warn("Unknown asyncAction type: " + actionType);
        }
    }

    void showNextStep(Step step) {
        if (currentStep != null) {
            for (StepChangeListener listener : stepChangeListeners) {
                listener.onFinishStep(currentStep);
            }
        }
        // TODO: call view
        this.currentStep = step;
        if (step != null) {
            for (StepChangeListener listener : stepChangeListeners) {
                listener.onShowStep(step);
            }
        }
    }

    void showPreviousStep(Step step) {

    }

    void showStepError(Throwable t) {

    }

    @Override
    public void initStep() {
        getTaskUseCase.execute(taskIdentifier, this::onTaskLoad, this::onTaskLoadFail);
    }

    public void onTaskLoad(@NonNull Task task) {
        this.task = task;
        this.getNextStepUseCase.execute(null, this::showNextStep, this::onTaskLoadFail);
    }

    public void onTaskLoadFail(Throwable t) {

    }

    @Override
    public void setView(PerformTaskContract.View view) {
        this.view = view;
    }

    public static class Factory {
        private final StepNavigatorFactory stepNavigatorFactory;
        private final StepChangeListenerFactory stepChangeListenerFactory;

        public Factory(@NonNull StepNavigatorFactory stepNavigatorFactory,
                       @NonNull StepChangeListenerFactory stepChangeListenerFactory) {
            checkNotNull(stepNavigatorFactory);
            checkNotNull(stepChangeListenerFactory);

            this.stepNavigatorFactory = stepNavigatorFactory;
            this.stepChangeListenerFactory = stepChangeListenerFactory;
        }

        PerformTaskPresenter createPresenter(Task task) {
            StepNavigator navigator = stepNavigatorFactory.create(task);

            UseCase<PerformTaskPresenter.TaskState, Step> getNextStepUseCase =
                    (param, successHandler, failureHandler) -> {
                        try {
                            Step step1 = navigator.getNextStep(param.step, param.taskResult);
                            successHandler.onSuccess(step1);
                        } catch (Throwable t) {
                            failureHandler.onFailure(t);
                        }
                    };

            UseCase<PerformTaskPresenter.TaskState, Step> getPreviousStepUseCase =
                    (param, successHandler, failureHandler) -> {
                        try {
                            Step step1 = navigator.getPreviousStep(param.step, param.taskResult);
                            successHandler.onSuccess(step1);
                        } catch (Throwable t) {
                            failureHandler.onFailure(t);
                        }
                    };
            List<StepChangeListener> stepChangeListeners = Lists.newArrayList();

            return new PerformTaskPresenter(task.getIdentifier(), null, getNextStepUseCase,
                    getPreviousStepUseCase, stepChangeListeners);
        }
    }
}
