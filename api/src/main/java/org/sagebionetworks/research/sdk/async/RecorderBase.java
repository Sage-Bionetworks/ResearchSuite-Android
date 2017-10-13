package org.sagebionetworks.research.sdk.async;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

/**
 * Created by liujoshua on 10/11/2017.
 */
public class RecorderBase extends AsyncActionBase implements Recorder {
    private final String stopStepIdentifier;

    public RecorderBase(String identifier, String startStepIdentifier, String stopStepIdentifier) {
        super(identifier, startStepIdentifier);
        this.stopStepIdentifier = stopStepIdentifier;
    }

    @Nullable
    @Override
    public String getStopStepIdentifier() {
        return stopStepIdentifier;
    }
}
