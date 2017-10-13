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

package org.sagebionetworks.research.sdk.task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;


public interface TaskCoordinator {
//    /**
//     * @return the task
//     */
//    @Nullable
//    Task getTask();
//
//    /**
//     * @return async actions associated with the task
//     */
//    @NonNull
//    List<AsyncAction> getAsyncActions();

    /**
     * Returns the step associated with a given identifier.
     *
     * @param identifier identifier for the step
     * @return step associated with identifier, or null
     */
    @Nullable
    Step getStep(@NonNull String identifier);

    /**
     * The step to go before the given step.
     *
     * @param step       the current step
     * @param taskResult the current task results
     * @return the previous step or null if backwards navigation is not allowed
     */
    @Nullable
    Step getStepBefore(@NonNull Step step, @Nullable TaskResult taskResult);

    /**
     * The step to go after the given step.
     *
     * @param step       the current step
     * @param taskResult the current task results
     * @return the next step or null if this is the end of the task
     */
    @Nullable
    Step getStepAfter(@NonNull Step step, @Nullable TaskResult taskResult);

    /**
     * Return the progress through the task for a given step with the current result.
     *
     * @param step       the current step
     * @param taskResult the current task results
     * @return the current progress
     */
    @NonNull
    Task.Progress getProgress(@NonNull Step step, @Nullable TaskResult taskResult);


}
