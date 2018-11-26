package com.example.amitkumar.myroomdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/*
MAIN ACTIVITY for room data base sample
Note:This is sample app to explain the uses of room database only,Not handled the negative and Ui cases.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCollegeDatabase mMyCollegeDataBaseObj;
    private TextView mTxtDisplay;
    private EditText mEdtName;
    private String mGetNameFromEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pageSetup();
    }

    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                mGetNameFromEditText=mEdtName.getText().toString();
                new DatabaseAnysc(1).execute();
                break;
            default:
                break;
        }
    }

    /**
     * call data base operation in worker thread
     */
    private class DatabaseAnysc extends AsyncTask<Void, Void, Void> {
        private final int mType;

        public DatabaseAnysc(int type) {
            mType = type;
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... voids) {
            //Switch case so that we can use on switch case for all the action -insert,update ,delete,get
            switch (mType) {
                case 1:
                    CollegeEntity collegeEntity = new CollegeEntity();
                    //Hard coded data for sample app
                    collegeEntity.setmBranchName("cs");
                    collegeEntity.setmStudentName(mGetNameFromEditText);
                    mMyCollegeDataBaseObj.myCollegeDao().insert(collegeEntity);
                    break;

                default:
                    break;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    /**
     * Basic setup
     */
    public void pageSetup() {
        // Define Ids here
        findViewById(R.id.btnAdd).setOnClickListener(this);
        mTxtDisplay = findViewById(R.id.txtDisplay);
        mEdtName=findViewById(R.id.edtName);
        //MyDatabase instance
        mMyCollegeDataBaseObj = Room.databaseBuilder(getApplicationContext(),
                MyCollegeDatabase.class, "sample-db")
                .addMigrations(MyCollegeDao.MIGRATION_1_2)//Whenever we change any thing in existing table at that time we have to add this line
                .build();
        //Live data registration to get update
        LiveData<List<CollegeEntity>> universityLiveData = mMyCollegeDataBaseObj.myCollegeDao().getStudentName();
        universityLiveData.observe(this, new Observer<List<CollegeEntity>>() {
            @Override
            public void onChanged(@Nullable List<CollegeEntity> universities) {
                //Update your UI here.
                Toast.makeText(MainActivity.this, "Observe", Toast.LENGTH_SHORT).show();
                mTxtDisplay.setText(universities.get(universities.size() - 1).getmStudentName());
            }
        });
    }
}

