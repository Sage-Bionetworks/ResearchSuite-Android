package org.sagebionetworks.research.sdk.performtask.step;

import android.support.annotation.Nullable;

import org.sagebionetworks.research.sdk.BaseContract;
import org.sagebionetworks.research.sdk.result.Result;
import org.sagebionetworks.research.sdk.step.ui.UIAction;

/**
 * Created by liujoshua on 10/11/2017.
 */

public interface PerformTaskStepContract {
    interface View extends BaseContract.BaseView{
        void showAction(@UIAction.UIActionType String actionType, UIAction action);

        void hideAction(@UIAction.UIActionType String actionType);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void initStep();

        void addStepResult(String key, Result result);
    }
}
