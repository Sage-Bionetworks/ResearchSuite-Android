package org.sagebionetworks.research.sdk.performtask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import org.sagebionetworks.research.sdk.R;
import org.sagebionetworks.research.sdk.domain.UseCase;
import org.sagebionetworks.research.sdk.performtask.step.PerformTaskStepContract;
import org.sagebionetworks.research.sdk.performtask.step.PerformTaskStepPresenter;
import org.sagebionetworks.research.sdk.performtask.step.PerformTaskStepPresenterFactory;
import org.sagebionetworks.research.sdk.performtask.step.PerformTaskStepViewFactory;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.step.StepChangeListener;
import org.sagebionetworks.research.sdk.step.ui.UIAction;
import org.sagebionetworks.research.sdk.task.Task;
import org.sagebionetworks.research.sdk.task.navigation.rule.StepNavigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liujoshua on 10/11/2017.
 */

public class PerformTaskFragment extends Fragment implements PerformTaskContract.View,
        PerformTaskStepContract.View {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformTaskFragment.class);
    private static final String TAG_RETAINED_FRAGMENT = "PresenterRetainedFragment";

    private PresenterRetainedFragment<PerformTaskPresenter> retainedFragment;

    private String taskId;
    private PerformTaskPresenter presenter;

    private StepNavigatorFactory stepNavigatorFactory;
    private PerformTaskStepViewFactory performTaskStepViewFactory;
    private PerformTaskStepPresenterFactory performTaskStepPresenterFactory;


    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull String taskIdentifier) {
        checkNotNull(context);
        checkArgument(!Strings.isNullOrEmpty(taskIdentifier));

        return new Intent(context, PerformTaskFragment.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        retainedFragment = (PresenterRetainedFragment<PerformTaskPresenter>) fm.findFragmentByTag
                (TAG_RETAINED_FRAGMENT);
        if (retainedFragment == null) {
            retainedFragment = new PresenterRetainedFragment<>();
        }
        presenter = retainedFragment.getPresenter();

        if (presenter == null) {
            // TODO: create presenter
            retainedFragment.setPresenter(presenter);
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.rsi_fragment_perform_task, container, false);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress(@Nullable Task.Progress progress) {

    }

    @Override
    public void showNextStep(Step step) {
        View view = performTaskStepViewFactory.createView(step);
    }

    @Override
    public void showPreviousStep(Step step) {

    }

    void setupStep(Step step) {
        View view = performTaskStepViewFactory.createView(step);
        PerformTaskStepPresenter stepPresenter = performTaskStepPresenterFactory
                .createTaskStepPresenter(step);
    }

    @Override
    public void showAction(@UIAction.UIActionType String actionType, UIAction action) {

    }

    @Override
    public void hideAction(@UIAction.UIActionType String actionType) {

    }
}
