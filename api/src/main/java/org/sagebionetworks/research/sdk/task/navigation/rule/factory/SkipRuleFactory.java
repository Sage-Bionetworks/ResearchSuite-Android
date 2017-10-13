package org.sagebionetworks.research.sdk.task.navigation.rule.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.task.navigation.rule.NavigationRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liujoshua on 10/13/2017.
 */

public class SkipRuleFactory implements NavigationRuleFactory<NavigationRule.Skip> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkipRuleFactory.class);

    @Nullable
    @Override
    public NavigationRule.Skip create(@NonNull Step step) {
        checkNotNull(step);

        NavigationRule.Skip rule = null;
        LOGGER.debug("Creating skip rule for step: " + step, ", created: " + rule);
        return rule;
    }
}
