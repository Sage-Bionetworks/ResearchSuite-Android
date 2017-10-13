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

import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.task.navigation.StepNavigatorDecorator;
import org.sagebionetworks.research.sdk.task.navigation.rule.factory.NavigationRuleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liujoshua on 10/6/2017.
 */

public class ConditionalStepNavigatorDecorator extends StepNavigatorDecorator {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ConditionalStepNavigatorDecorator.class);

    private final NavigationRuleFactory<NavigationRule.Conditional>
            conditionalNavigationRuleFactory;

    public ConditionalStepNavigatorDecorator(@NonNull StepNavigator stepNavigatorToBeDecorated,
                                             @NonNull NavigationRuleFactory<NavigationRule
                                                     .Conditional>
                                                     conditionalNavigationRuleFactory) {
        super(stepNavigatorToBeDecorated);
        checkNotNull(conditionalNavigationRuleFactory);

        this.conditionalNavigationRuleFactory = conditionalNavigationRuleFactory;
    }

    @Nullable
    @Override
    public Step getNextStep(Step step, @Nullable TaskResult taskResult) {
        Step nextStep = super.getNextStep(step, taskResult);

        return getReplacementStep(nextStep, taskResult);
    }

    @Nullable
    @Override
    public Step getPreviousStep(Step step, @Nullable TaskResult taskResult) {
        Step nextStep = super.getPreviousStep(step, taskResult);

        return getReplacementStep(nextStep, taskResult);
    }

    Step getReplacementStep(@Nullable Step step, @Nullable TaskResult taskResult) {
        if (step == null) {
            return null;
        }

        NavigationRule.Conditional conditionalRule = conditionalNavigationRuleFactory.create(step);
        if (conditionalRule == null) {
            return step;
        } else {
            Step replacementStep = conditionalRule.getReplacementStep(step, taskResult);

            LOGGER.debug("Applying conditional rule: " + conditionalRule + ", found replacement " +
                    "step: " + replacementStep);
            return replacementStep;
        }
    }
}
