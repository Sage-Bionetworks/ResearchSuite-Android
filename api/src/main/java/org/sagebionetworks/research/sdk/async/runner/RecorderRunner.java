package org.sagebionetworks.research.sdk.async.runner;

import android.support.annotation.CallSuper;

import org.sagebionetworks.research.sdk.async.Recorder;
import org.sagebionetworks.research.sdk.step.Step;

/**
 * Created by liujoshua on 10/11/2017.
 */

public abstract class RecorderRunner extends AsyncActionRunner {
    private final Recorder recorder;

    public RecorderRunner(Recorder recorder) {
        super(recorder);
        this.recorder = recorder;
    }

    @Override
    @CallSuper
    public final void onFinishStep(Step step) {
        super.onFinishStep(step);
        if (recorder.getStopStepIdentifier().equals(step.getIdentifier()));

    }

    public abstract void doFInish();

}
