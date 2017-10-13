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

public class Schema {
    @NonNull
    private final String identifier;
    private final int revision;

    public Schema(@NonNull String identifier, int revision) {
        this.identifier = identifier;

        this.revision = revision;
    }

    /**
     * @return short string that uniquely identifies the associated result schema. If nil, then the `taskIdentifier` is used.
     */
    @NonNull
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return revision number associated with the result schema. If `0`, then this is ignored.
     */
    public int getRevision() {
        return revision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schema schema = (Schema) o;
        return revision == schema.revision &&
                Objects.equal(identifier, schema.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier, revision);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .add("revision", revision)
                .toString();
    }
}
