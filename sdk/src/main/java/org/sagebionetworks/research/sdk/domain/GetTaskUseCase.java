package org.sagebionetworks.research.sdk.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.sagebionetworks.research.sdk.Duration;
import org.sagebionetworks.research.sdk.Schema;
import org.sagebionetworks.research.sdk.task.Task;

/**
 * Created by liujoshua on 10/11/2017.
 */

public class GetTaskUseCase implements UseCase<String, Task> {
    @Override
    public void execute(final String taskIdentifier, SuccessHandler<Task> successHandler,
                        FailureHandler
                                failureHandler) {
        Task t = new Task() {
            @NonNull
            @Override
            public String getIdentifier() {
                return taskIdentifier;
            }

            @Nullable
            @Override
            public Schema getSchema() {
                return null;
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

            @Nullable
            @Override
            public Duration getEstimatedDuration() {
                return null;
            }

            @Override
            public int getIcon() {
                return 0;
            }
        };
        if (successHandler != null) {
            successHandler.onSuccess(t);
        }
    }
}
