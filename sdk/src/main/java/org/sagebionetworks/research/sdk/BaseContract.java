package org.sagebionetworks.research.sdk;

/**
 * Created by liujoshua on 10/11/2017.
 */

public interface BaseContract {
    interface BaseView {

    }

    interface BasePresenter<T extends BaseView> {
        void setView(T view);
    }
}

