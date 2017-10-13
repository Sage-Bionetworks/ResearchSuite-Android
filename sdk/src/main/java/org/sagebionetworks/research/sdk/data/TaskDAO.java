package org.sagebionetworks.research.sdk.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by liujoshua on 10/11/2017.
 */
@Dao
public interface TaskDAO {
    @Query("SELECT * FROM tasks WHERE identifier = :identifier")
    TaskEntity getById(String identifier);

    @Insert (onConflict = REPLACE)
    void upsert(TaskEntity... TaskEntity);

    @Delete
    void delete(TaskEntity... TaskEntity);
}
