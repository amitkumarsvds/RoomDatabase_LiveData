package com.example.amitkumar.myroomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = MyCollegeEntity.class, version = 2)
public abstract class MyCollegeDatabase extends RoomDatabase {
    public abstract MyCollegeDao myCollegeDao();
}
