package org.sagebionetworks.research.sdk.step;

import android.support.annotation.NonNull;

/**
 * Created by liujoshua on 10/11/2017.
 */

public interface StepChangeListener {
    void onShowStep(@NonNull Step step);

    void onCancelStep(@NonNull Step step);

    void onFinishStep(@NonNull Step step);
}
