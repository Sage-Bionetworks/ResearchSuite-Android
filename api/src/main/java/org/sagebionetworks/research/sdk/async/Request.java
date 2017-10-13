package org.sagebionetworks.research.sdk.async;

import android.support.annotation.Nullable;

import org.sagebionetworks.research.sdk.Duration;

/**
 * Created by liujoshua on 10/11/2017.
 */

public interface Request extends AsyncAction {
    /**
     * An identifier marking a step to wait to display until the asyncAction is completed. This is only valid for actions
     * that are single result actions and not continuous recorders.
     *
     * @return step identifier, or null
     */
    @Nullable
    String getWaitStepIdentifier();

    /**
     * A time interval after which the asyncAction should be reset. For example, if the asyncAction queries a weather service and
     * the user backgrounds the app for more than the reset time, then the weather service should be queried again.
     *
     * @return duration
     */
    @Nullable
    Duration getResetTimeInterval();

    /**
     * A time interval after which the asyncAction should be stopped.
     *
     * @return duration
     */
    @Nullable
    Duration getTimeoutTimeInterval();
}
