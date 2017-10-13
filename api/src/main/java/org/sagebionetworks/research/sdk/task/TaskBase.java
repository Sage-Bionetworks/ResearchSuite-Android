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

package org.sagebionetworks.research.sdk.task;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import org.sagebionetworks.research.sdk.Duration;
import org.sagebionetworks.research.sdk.Schema;

/**
 * Created by liujoshua on 10/2/2017.
 */
public class TaskBase implements Task {
    @NonNull
    private final String taskIdentifier;
    @Nullable
    private final Schema schema;
    @Nullable
    private final int title;
    @Nullable
    private final int detail;
    @Nullable
    private final int copyright;
    @Nullable
    private final Duration estimatedDuration;
    @DrawableRes
    private final int icon;

    public TaskBase(@NonNull String taskIdentifier, Schema schema, int title, int detail,
                    int copyright, Duration estimatedDuration, int icon) {
        this.taskIdentifier = taskIdentifier;
        this.schema = schema;
        this.title = title;
        this.detail = detail;
        this.copyright = copyright;
        this.estimatedDuration = estimatedDuration;
        this.icon = icon;
    }

    @Override
    @NonNull
    public String getIdentifier() {

        return taskIdentifier;
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return schema;
    }

    @Override
    public int getTitle() {
        return 0;
    }

    @Override
    public int getDetail() {
        return 0;
    }

    @Override
    public int getCopyright() {
        return 0;
    }

    @Override
    @Nullable
    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    @Override
    @DrawableRes
    public int getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskBase taskBase = (TaskBase) o;
        return icon == taskBase.icon &&
                Objects.equal(taskIdentifier, taskBase.taskIdentifier) &&
                Objects.equal(schema, taskBase.schema) &&
                Objects.equal(title, taskBase.title) &&
                Objects.equal(detail, taskBase.detail) &&
                Objects.equal(copyright, taskBase.copyright) &&
                Objects.equal(estimatedDuration, taskBase.estimatedDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taskIdentifier, schema, title, detail, copyright,
                estimatedDuration, icon);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("taskIdentifier", taskIdentifier)
                .add("schema", schema)
                .add("title", title)
                .add("detail", detail)
                .add("copyright", copyright)
                .add("estimatedDuration", estimatedDuration)
                .add("icon", icon)
                .toString();
    }
}
