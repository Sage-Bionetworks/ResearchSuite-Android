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

package org.sagebionetworks.research.sdk.result;

import android.support.annotation.NonNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;


public class ResultBase implements Result {
    @NonNull
    private final String identifier;
    @NonNull
    private final Date startDate;
    @NonNull
    private final Date endDate;

    public ResultBase(@NonNull String identifier, @NonNull Date startDate, @NonNull Date endDate) {
        this.identifier = identifier;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @NonNull
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    @NonNull
    public Date getStartDate() {
        return startDate;
    }

    @Override
    @NonNull
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResultBase that = (ResultBase) o;
        return Objects.equal(identifier, that.identifier) &&
                Objects.equal(startDate, that.startDate) &&
                Objects.equal(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier, startDate, endDate);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .toString();
    }
}
