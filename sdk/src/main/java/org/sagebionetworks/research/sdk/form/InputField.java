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

package org.sagebionetworks.research.sdk.form;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import static org.sagebionetworks.research.sdk.form.InputField.DataType.BOOLEAN;
import static org.sagebionetworks.research.sdk.form.InputField.DataType.DATE;
import static org.sagebionetworks.research.sdk.form.InputField.DataType.DATE_COMPONENTS;
import static org.sagebionetworks.research.sdk.form.InputField.UIHint.CHECKBOX;
import static org.sagebionetworks.research.sdk.form.InputField.UIHint.COMBOBOX;

/**
 * Describes a form input within a step. Contains information about data type and hints on how
 * the UI should be displayed.
 */
public interface InputField {
    /**
     * @return identifier that is unique among form items within the step
     */
    @NonNull
    String getIdentifier();

    /**
     * @return short text offering hint for data to be entered
     */
    @StringRes
    int getPrompt();

    /**
     * @return text for display in a text field or text area to help users understand how to
     * answer the item's question
     */
    @StringRes
    int getPlaceholderText();

    /**
     * @return data type for this input field. The data type can have an associated ui hint
     */
    @InputField.DataType
    String getFormDataType();

    /**
     * @return UI hint for how the study would prefer that the input field is displayed to the user
     */
    @InputField.UIHint
    String getFormUIHint();

    @StringDef({BOOLEAN, DATE, DATE_COMPONENTS})
    @interface DataType {
        String BOOLEAN = "boolean";
        String DATE = "date";
        String DATE_COMPONENTS = "dateComponents";
        // TODO: populate remaining types
    }

    @StringDef({CHECKBOX, COMBOBOX})
    @interface UIHint {
        String CHECKBOX = "checkbox";
        String COMBOBOX = "combobox";
        // TODO: populate remaining types
    }
}
