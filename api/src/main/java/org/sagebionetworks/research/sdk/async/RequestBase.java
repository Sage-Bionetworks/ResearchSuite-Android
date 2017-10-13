package org.sagebionetworks.research.sdk.async;

import android.support.annotation.Nullable;

import org.sagebionetworks.research.sdk.Duration;

/**
 * Created by liujoshua on 10/11/2017.
 */

public class RequestBase extends AsyncActionBase implements Request {
    private final String waitStepIdentifier;
    private final Duration resetTimeInterval;
    private final Duration timeoutInterval;

    public RequestBase(String identifier, String startStepIdentifier, String waitStepIdentifier,
                       Duration resetTimeInterval, Duration timeoutInterval) {
        super(identifier, startStepIdentifier);
        this.waitStepIdentifier = waitStepIdentifier;
        this.resetTimeInterval = resetTimeInterval;
        this.timeoutInterval = timeoutInterval;
    }

    @Nullable
    @Override
    public String getWaitStepIdentifier() {
        return waitStepIdentifier;
    }

    @Nullable
    @Override
    public Duration getResetTimeInterval() {
        return resetTimeInterval;
    }

    @Nullable
    @Override
    public Duration getTimeoutTimeInterval() {
        return timeoutInterval;
    }
}
