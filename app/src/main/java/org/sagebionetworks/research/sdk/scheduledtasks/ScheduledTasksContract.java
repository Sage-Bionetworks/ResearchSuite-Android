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

package org.sagebionetworks.research.sdk.scheduledtasks;

import android.support.annotation.NonNull;

import org.sagebionetworks.research.sdk.task.Task;

import java.util.List;

/**
 * Created by liujoshua on 10/2/2017.
 */

public interface ScheduledTasksContract {
    interface View {
        void showTasks(List<Task> tasks);

        void showPerformTask(Task task);
    }

    interface Presenter {
        void loadTasks(boolean forceUpdate);

        void performTask(@NonNull Task task);

        void skipTask(@NonNull Task task);
    }
}