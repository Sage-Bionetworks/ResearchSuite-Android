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
 * Created by liujoshua on 10/13/2017.
 */

public class NextRuleNavigationDecorator extends StepNavigatorDecorator {
    private static final Logger LOGGER = LoggerFactory.getLogger(NextRuleNavigationDecorator
            .class);

    private final StepNavigator stepNavigatorToBeDecorated;
    private final NavigationRuleFactory<NavigationRule.Next> navigationRuleFactory;

    public NextRuleNavigationDecorator(
            @NonNull StepNavigator stepNavigatorToBeDecorated,
            @NonNull NavigationRuleFactory<NavigationRule.Next> navigationRuleFactory) {
        super(stepNavigatorToBeDecorated);
        checkNotNull(stepNavigatorToBeDecorated);
        checkNotNull(navigationRuleFactory);

        this.stepNavigatorToBeDecorated = stepNavigatorToBeDecorated;
        this.navigationRuleFactory = navigationRuleFactory;
    }

    @Nullable
    @Override
    public Step getNextStep(Step step, @Nullable TaskResult taskResult) {
        NavigationRule.Next rule = navigationRuleFactory.create(step);
        if (rule != null) {
            Step nextStep = rule.getNextStep(step, taskResult);
            if (nextStep != null) {
                LOGGER.debug("NavigationRule produced next step: " + nextStep);
                return nextStep;
            }
            LOGGER.debug("NavigationRule produced no next step, continuing");
        }

        return stepNavigatorToBeDecorated.getNextStep(step, taskResult);
    }
}
