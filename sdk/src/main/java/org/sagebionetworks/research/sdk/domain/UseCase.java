package org.sagebionetworks.research.sdk.domain;

import android.support.annotation.NonNull;

/**
 * Created by liujoshua on 10/11/2017.
 */

public interface UseCase<P, R> {
    void execute(P param, SuccessHandler<R> successHandler, FailureHandler failureHandler);

    interface SuccessHandler<R> {
        void onSuccess(R result);
    }

    interface FailureHandler {
        void onFailure(Throwable t);
    }
}
