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
import static org.sagebionetworks.research.sdk.step.ui.UIAction.Type.NEXT;

/**
 * Defines a title and image for an action.
 */
public interface UIAction {
    class Type {
        public static final String NEXT = "next";
    }

    @Retention(SOURCE)
    @StringDef({NEXT})
    @interface UIActionType {
    }

    @StringRes
    int getButtonTitle();

    @DrawableRes
    int getButtonIcon();
}
