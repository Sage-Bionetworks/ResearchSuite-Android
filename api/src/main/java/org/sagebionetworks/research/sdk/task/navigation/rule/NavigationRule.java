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

/**
 * Created by liujoshua on 10/4/2017.
 */

public interface NavigationRule {
    interface Next extends NavigationRule {
        /**
         * @param step       step that just finished
         * @param taskResult current task result
         * @return identifier of next step, or null if this is the last step
         */
        @Nullable
        Step getNextStep(@NonNull Step step, @Nullable TaskResult taskResult);
    }

    /**
     * A conditional rule is appended to the navigable task to check a secondary source for
     * whether or not the step should be displayed.
     */
    interface Conditional extends NavigationRule {

        /**
         * Allows conditional rule to mutate or replace step that the navigation rules have
         * determined should be the return step.
         *
         * @param step       step that navigation has opted to return
         * @param taskResult current task result
         * @return mutated/replaced step, or original step for no mutation or replacement
         */
        @NonNull
        Step getReplacementStep(@NonNull Step step, @Nullable TaskResult taskResult);
    }

    interface Skip extends NavigationRule {
        /**
         * @param step       step about to be displayed
         * @param taskResult current task result
         * @return true if step should be skipped
         */
        boolean shouldSkip(@NonNull Step step, @Nullable TaskResult taskResult);
    }

    interface Back extends NavigationRule {
        boolean isBackAllowed(@NonNull Step step, @Nullable TaskResult taskResult);
    }
}
