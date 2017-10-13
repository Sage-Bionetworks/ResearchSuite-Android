package org.sagebionetworks.research.sdk.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by liujoshua on 10/11/2017.
 */
@Entity(tableName = "tasks")
public class TaskEntity {
    @NonNull
    @PrimaryKey
    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
