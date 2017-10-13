package org.sagebionetworks.research.sdk.async.runner;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import org.sagebionetworks.research.sdk.async.AsyncAction;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.step.StepChangeListener;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liujoshua on 10/11/2017.
 */

public abstract class AsyncActionRunner implements StepChangeListener {
    private final AsyncAction asyncAction;

    public AsyncActionRunner(@NonNull AsyncAction asyncAction) {
        checkNotNull(asyncAction);
        this.asyncAction = asyncAction;
    }

    @Override
    @CallSuper
    public void onShowStep(Step step) {
        if (asyncAction.getStartStepIdentifier().equals(step.getIdentifier())) {
            runAction();
        }
    }

    protected abstract void runAction();

    @Override
    @CallSuper
    public void onCancelStep(Step step) {
        //no-op
    }

    @Override
    @CallSuper
    public void onFinishStep(Step step) {
        //no-op
    }
}
