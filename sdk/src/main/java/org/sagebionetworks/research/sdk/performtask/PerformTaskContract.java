/*
 *    Copyright 2017 Sage Bionetworks
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package org.sagebionetworks.research.sdk.performtask;

import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import org.sagebionetworks.research.sdk.BaseContract;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.task.Task;

import static org.sagebionetworks.research.sdk.step.ui.UIAction.UIActionType;

/**
 * Created by liujoshua on 10/4/2017.
 */

public interface PerformTaskContract {
    @AnyThread
    interface Presenter extends BaseContract.BasePresenter<View> {
        void initStep();

        void handleAction(@UIActionType String actionType);
    }

    @UiThread
    interface View extends BaseContract.BaseView {
        void hideProgress();

        void showProgress(@Nullable Task.Progress progress);

        void showNextStep(Step step);

        void showPreviousStep(Step step);
    }
}
