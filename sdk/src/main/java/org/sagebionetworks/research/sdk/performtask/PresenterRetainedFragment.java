package org.sagebionetworks.research.sdk.performtask;

import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import org.sagebionetworks.research.sdk.BaseContract;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by liujoshua on 10/11/2017.
 */

public class PresenterRetainedFragment<T extends BaseContract.BasePresenter> extends
        Fragment {
    private T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
        if (presenter instanceof LifecycleObserver) {
            getLifecycle().addObserver((LifecycleObserver) presenter);
        }
    }
}
