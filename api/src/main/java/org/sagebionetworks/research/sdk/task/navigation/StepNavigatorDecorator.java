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

package org.sagebionetworks.research.sdk.task.navigation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.task.navigation.rule.StepNavigator;

import static com.google.common.base.Preconditions.checkNotNull;

public class StepNavigatorDecorator implements StepNavigator {
    private final StepNavigator stepNavigatorToBeDecorated;

    public StepNavigatorDecorator(@NonNull StepNavigator stepNavigatorToBeDecorated) {
        checkNotNull(stepNavigatorToBeDecorated);
        this.stepNavigatorToBeDecorated = stepNavigatorToBeDecorated;
    }

    @Nullable
    @Override
    public Step getNextStep(Step step, @Nullable TaskResult taskResult) {
        return stepNavigatorToBeDecorated.getNextStep(step, taskResult);
    }


    @Nullable
    @Override
    public Step getPreviousStep(Step step, @Nullable TaskResult taskResult) {
        return stepNavigatorToBeDecorated.getPreviousStep(step, taskResult);
    }
}
