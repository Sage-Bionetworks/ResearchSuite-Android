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

import com.google.common.collect.ImmutableList;

import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.task.navigation.rule.StepNavigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The OrderedStepNavigator moves through a linear series of steps.
 * <p>
 * Any simple sequential task, such as a survey or an active task, can be presented by an
 * OrderedStepNavigator.
 */
public class OrderedStepNavigator implements StepNavigator {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedStepNavigator.class);

    private final ImmutableList<Step> steps;

    public OrderedStepNavigator(List<? extends Step> steps) {
        this.steps = ImmutableList.copyOf(steps);
    }

    @Nullable
    @Override
    public Step getNextStep(@Nullable Step step, @Nullable TaskResult taskResult) {
        // default to the first step
        int nextStepIndex = 0;

        if (step != null) {
            int stepIndex = steps.indexOf(step);
            if (stepIndex < 0) {
                LOGGER.warn("Unable to locate step: " + step + ", returning null for next step");
                return null;
            }
            nextStepIndex = stepIndex + 1;
        }

        if (nextStepIndex >= steps.size()) {
            return null;
        }

        return steps.get(nextStepIndex);
    }

    @Nullable
    @Override
    public Step getPreviousStep(@NonNull Step step, @Nullable TaskResult taskResult) {
        checkNotNull(step);
        //TODO: ask Shannon how stepHistory works. does it pop off like backstack? does moving
        // backwards append to step history?

        int stepIndex = steps.indexOf(step);

        if (stepIndex < 0) {
            LOGGER.warn("Unable to locate step: " + step + ", returning null for previous step");
            return null;
        } else if (stepIndex == 0) {
            return null;
        }

        return steps.get(stepIndex - 1);
    }
}
