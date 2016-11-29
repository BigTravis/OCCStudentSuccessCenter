package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Course> mCourses;
    private List<DayTime> mDayTimes;
    private List<Tutor> mTutors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
