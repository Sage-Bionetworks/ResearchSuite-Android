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
import android.support.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AnswerResult<T> extends ResultBase {
    @NonNull
    private final T answer;
    @NonNull
    private final Map<String, T> metadata;

    public AnswerResult(@NonNull String identifier, @NonNull Date startDate, @NonNull Date endDate, @NonNull T answer) {
        this(identifier, startDate, endDate, answer, new HashMap<String, T>());
    }


    public AnswerResult(@NonNull String identifier, @NonNull Date startDate, @NonNull Date endDate, @NonNull T answer,
                        @NonNull Map<String, T> metadata) {
        super(identifier, startDate, endDate);

        this.answer = answer;
        this.metadata = metadata;
    }

    @NonNull
    public T getAnswer() {
        return answer;
    }

    @Nullable
    public T getMetadata(String key) {
        return metadata.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnswerResult<?> that = (AnswerResult<?>) o;
        return Objects.equal(answer, that.answer) &&
                Objects.equal(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(answer, metadata);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("answer", answer)
                .add("metadata", metadata)
                .toString();
    }
}
