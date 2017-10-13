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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;


public  class TaskCoordinatorBase implements TaskCoordinator {
    @NonNull
    private final String identifier;
    @Nullable
    private final Task task;


    public TaskCoordinatorBase(@NonNull String identifier, Task task) {
        this.identifier = identifier;
        this.task = task;
    }

    @NonNull
    public String getIdentifier() {
        return identifier;
    }

    @Nullable
    public Task getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskCoordinatorBase that = (TaskCoordinatorBase) o;
        return Objects.equal(identifier, that.identifier) &&
                Objects.equal(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier, task);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .add("task", task)
                .toString();
    }

    @Nullable
    @Override
    public Step getStep(@NonNull String identifier) {
        return null;
    }

    @Nullable
    @Override
    public Step getStepBefore(@NonNull Step step, @Nullable TaskResult taskResult) {
        return null;
    }

    @Nullable
    @Override
    public Step getStepAfter(@NonNull Step step, @Nullable TaskResult taskResult) {
        return null;
    }

    @NonNull
    @Override
    public Task.Progress getProgress(@NonNull Step step, @Nullable TaskResult taskResult) {
        return null;
    }
}

