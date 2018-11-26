package com.example.amitkumar.myroomdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.Nullable;

import java.util.List;

@Dao
public interface MyCollegeDao {

    @Insert
    void insert(MyCollegeEntity myCollegeEntity);

    @Query("select * from college where branchname='cs'")
    LiveData<List<MyCollegeEntity>> getStudentName();


    Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@Nullable SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

}
