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

package org.sagebionetworks.research.sdk.task.navigation.rule;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.task.navigation.StepNavigatorDecorator;

/**
 * Created by liujoshua on 10/6/2017.
 */

public class BackStepNavigatorDecorator extends StepNavigatorDecorator {
    public BackStepNavigatorDecorator(@NonNull StepNavigator stepNavigatorToBeDecorated) {
        super(stepNavigatorToBeDecorated);
    }

    @Override
    public Step getPreviousStep(Step step, @Nullable TaskResult taskResult) {
        // TODO: method for getting current step
        Step currentStep = null;

        if (isBackAllowed(currentStep, taskResult)) {
            return super.getPreviousStep(step, taskResult);
        }
        return null;
    }

    @VisibleForTesting
    boolean isBackAllowed(@NonNull Step currentStep, @Nullable TaskResult taskResult) {
        // TODO: verify true by default
        boolean isBackAllowed = true;
        // check if the current step has a rule that prevents going back
        NavigationRule.Back backRule = getBackRule(currentStep);
        if (backRule != null) {
            isBackAllowed = backRule.isBackAllowed(currentStep, taskResult);
        }
        return isBackAllowed;
    }

    @VisibleForTesting
    NavigationRule.Back getBackRule(Step step) {
        // TODO: method for retrieving rule
        return null;
    }
}
