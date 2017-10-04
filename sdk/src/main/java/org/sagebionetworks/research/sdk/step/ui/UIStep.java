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
import android.support.annotation.StringRes;

import org.sagebionetworks.research.sdk.step.Step;

/**
 * Defines a single "display unit".
 */
public interface UIStep extends Step, UIActionHandler {
    /**
     * @return primary text to display for the step
     */
    @StringRes
    int getTitle();

    /**
     * Additional text is displayed in a smaller font below Title.
     *
     * @return additional text to display
     */
    @StringRes
    int getText();

    /**
     * @return detailed explanation for the step
     */
    @StringRes
    int getDetail();

    /**
     * @return text to display for the step at the bottom of the view
     */
    @StringRes
    int getFootnote();

    /**
     * @return image to display before title, text, and detail
     */
    @DrawableRes
    int getImageBefore();

    /**
     * @return image to display after title, text, and detail
     */
    @DrawableRes
    int getImageAfter();
}
