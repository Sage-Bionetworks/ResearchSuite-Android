package org.sagebionetworks.research.sdk.async;

import android.support.annotation.Nullable;

/**
 * Created by liujoshua on 10/11/2017.
 */

public interface Recorder extends AsyncAction {
    /**
     * An identifier marking the step at which to stop the asyncAction. If `nil`, then the asyncAction will be stopped when the
     * task is stopped.
     *
     * @return step identifier, or null
     */
    @Nullable
    String getStopStepIdentifier();
}
