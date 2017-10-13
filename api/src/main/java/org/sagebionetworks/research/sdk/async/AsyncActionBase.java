package org.sagebionetworks.research.sdk.async;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by liujoshua on 10/11/2017.
 */

public class AsyncActionBase implements AsyncAction {
    private final String identifier;
    private final String startStepIdentifier;

    public AsyncActionBase(String identifier, String startStepIdentifier) {
        this.identifier = identifier;
        this.startStepIdentifier = startStepIdentifier;
    }

    @NonNull
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Nullable
    @Override
    public String getStartStepIdentifier() {
        return startStepIdentifier;
    }
}
