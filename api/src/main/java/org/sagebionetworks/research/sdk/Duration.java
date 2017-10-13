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

package org.sagebionetworks.research.sdk;

import android.support.annotation.NonNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.concurrent.TimeUnit;

public class Duration {
    @NonNull
    public final double value;
    @NonNull
    public final TimeUnit unit;

    public Duration(double seconds) {
        this.value = seconds;
        this.unit = TimeUnit.SECONDS;
    }

    public double getValue() {
        return value;
    }

    @NonNull
    public TimeUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Duration duration = (Duration) o;
        return Double.compare(duration.value, value) == 0 &&
                unit == duration.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value, unit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .add("unit", unit)
                .toString();
    }
}
