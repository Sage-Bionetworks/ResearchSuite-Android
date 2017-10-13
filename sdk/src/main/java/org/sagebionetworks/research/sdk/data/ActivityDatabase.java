package org.sagebionetworks.research.sdk.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by liujoshua on 10/11/2017.
 */
@Database(entities = {TaskEntity.class}, version = 1)
public abstract class ActivityDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
}
