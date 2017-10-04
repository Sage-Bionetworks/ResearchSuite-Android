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

package org.sagebionetworks.research.sdk.step.ui;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.google.common.base.Optional;

import org.sagebionetworks.research.sdk.Duration;

import java.util.Set;

/**
 * Created by liujoshua on 10/4/2017.
 */

public interface ActiveUIStep extends UIStep {
    /**
     * @return duration of time to run the step
     */
    @NonNull
    Optional<Duration> getDuration();

    /**
     * @return spoken instructions for the step
     */
    @NonNull
    Set<SpokenInstruction> getSpokenInstructions();

    /**
     * Localized text that represents an instructional voice prompt. Instructional speech begins
     * when the step passes the time indicated by the given time.  If `timeInterval` is greater
     * than or equal to `duration` or is equal to `Double.infinity`, then the spoken instruction
     * should be returned for when the step is finished.
     */
    interface SpokenInstruction {
        @NonNull
        Duration getDuration();

        @StringRes
        int getInstruction();
    }
}
