package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);

        db.importCoursesFromCSV("Courses.csv");
        db.importTutorsFromCSV("Tutors.csv");
        db.importDayTimesFromCSV("DayTime.csv");
        db.importRelationsFromCSV("TutorHelpRelationship.csv");

        ArrayList<Course> allCourses = db.getAllCourses();
        Log.i("OCC SSC", allCourses.toString());

        ArrayList<Tutor> allTutors = db.getAllTutors();
        Log.i("OCC SSC", allTutors.toString());

        ArrayList<DayTime> allDayTimes = db.getAllDayTimes();
        Log.i("OCC SSC", allDayTimes.toString());

        ArrayList<TutorTimeRelation> allRelations = db.getAllRelations();
        Log.i("OCC SSC", allRelations.toString());

    }
}
