package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        deleteDatabase(DBHelper.DATABASE_NAME);
//        db = new DBHelper(this);
//        populateDatabase();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000);


//        try{
//            db.getReadableDatabase();
//            deleteDatabase(DBHelper.DATABASE_NAME);
//            populateDatabase();
//
//        }catch(SQLiteException e) {
//            populateDatabase();
//        }


    }

    private void populateDatabase()
    {
        db.importCoursesFromCSV("Courses.csv");
        db.importTutorsFromCSV("Tutors.csv");
        db.importDayTimesFromCSV("DayTime.csv");
        db.importRelationsFromCSV("TutorHelpRelationship.csv");
        db.importStudyGroupsFromCSV("Studygroups.csv");
    }

}

