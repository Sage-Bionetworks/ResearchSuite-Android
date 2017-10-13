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
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;

import org.sagebionetworks.research.sdk.Duration;
import org.sagebionetworks.research.sdk.Schema;

/**
 * Created by liujoshua on 10/2/2017.
 */

public interface Task {
    @NonNull
    String getIdentifier();

    @Nullable
    Schema getSchema();

    @StringRes
    int getTitle();

    @StringRes
    int getDetail();

    @StringRes
    int getCopyright();

    @Nullable
    Duration getEstimatedDuration();

    @DrawableRes
    int getIcon();

    class Progress {
        private final int progress;
        private final int total;
        private final boolean isEstimated;

        public Progress(int progress, int total, boolean isEstimated) {
            this.progress = progress;
            this.total = total;
            this.isEstimated = isEstimated;
        }

        public int getProgress() {
            return progress;
        }

        public int getTotal() {
            return total;
        }

        public boolean isEstimated() {
            return isEstimated;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Progress progress1 = (Progress) o;
            return progress == progress1.progress &&
                    total == progress1.total &&
                    isEstimated == progress1.isEstimated;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(progress, total, isEstimated);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("progress", progress)
                    .add("total", total)
                    .add("isEstimated", isEstimated)
                    .toString();
        }
    }
}
