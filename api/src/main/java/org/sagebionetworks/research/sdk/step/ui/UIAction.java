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

package org.sagebionetworks.research.sdk.step.ui;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.sagebionetworks.research.sdk.step.ui.UIAction.UIActionType.END;
import static org.sagebionetworks.research.sdk.step.ui.UIAction.UIActionType.NEXT;
import static org.sagebionetworks.research.sdk.step.ui.UIAction.UIActionType.PREVIOUS;

/**
 * Defines a title and image for an asyncAction.
 */
public interface UIAction {

    @Retention(SOURCE)
    @StringDef({NEXT, PREVIOUS, END})
    @interface UIActionType {
        String NEXT = "next";
        String PREVIOUS = "previous";
        String END = "end";
    }

    @StringRes
    int getButtonTitle();

    @DrawableRes
    int getButtonIcon();
}
