package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);

//        deleteDatabase(DBHelper.DATABASE_NAME);
//        db = new DBHelper(this);
//        populateDatabase();

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

        ArrayList<Course> allCourses = db.getAllCourses();
        Log.i("OCC SSC", allCourses.toString());

        ArrayList<Tutor> allTutors = db.getAllTutors();
        Log.i("OCC SSC", allTutors.toString());

        ArrayList<DayTime> allDayTimes = db.getAllDayTimes();
        Log.i("OCC SSC", allDayTimes.toString());

        ArrayList<TutorTimeRelation> allRelations = db.getAllRelations();
        Log.i("OCC SSC", allRelations.toString());

        ArrayList<StudyGroup> allStudyGroups = db.getAllStudyGroups();
        Log.i("OCC SSC", allStudyGroups.toString());
    }
}

