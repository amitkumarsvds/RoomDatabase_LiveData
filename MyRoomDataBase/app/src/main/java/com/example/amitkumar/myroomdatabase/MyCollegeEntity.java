package com.example.amitkumar.myroomdatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "college")
public class MyCollegeEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rollno")
    public int mRollNumber;

    @ColumnInfo(name = "stuname")
    public String mStudentName;

    @ColumnInfo(name = "branchname")
    public String mBranchName;

    public String getmStudentName() {
        return mStudentName;
    }

    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public String getmBranchName() {
        return mBranchName;
    }

    public void setmBranchName(String mBranchName) {
        this.mBranchName = mBranchName;
    }


}
