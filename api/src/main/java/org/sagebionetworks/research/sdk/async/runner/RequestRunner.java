package org.sagebionetworks.research.sdk.async.runner;

import org.sagebionetworks.research.sdk.async.Request;

/**
 * Created by liujoshua on 10/11/2017.
 */

public abstract class RequestRunner extends AsyncActionRunner {
    private final Request request;

    public RequestRunner(Request request) {
        super(request);
        this.request = request;
    }
}
