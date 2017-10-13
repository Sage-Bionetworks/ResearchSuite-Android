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

package org.sagebionetworks.research.sdk.async;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Defines general configuration for asynchronous asyncAction that should be run in the background. Depending upon the
 * parameters and how the asyncAction is setup, this could be something that is run continuously or else is paused or reset
 * based on a timeout interval.
 */
public interface AsyncAction {
    /**
     * A short string that uniquely identifies the asyncronous asyncAction within the task. The identifier is reproduced in
     * the results of a async results.
     *
     * @return identifier
     */
    @NonNull
    String getIdentifier();

    /**
     * An identifier marking the step to start the asyncAction. If `null`, then the asyncAction will be started when the task is
     * started.
     *
     * @return step identifier, or null
     */
    @Nullable
    String getStartStepIdentifier();

}
