package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TutorDetailsActivity extends NavDrawerActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_tutor_details, contentFrameLayout);

        TextView tutorNameTextView = (TextView) findViewById(R.id.tutorNameTextView);
        TextView tutorClassTextView = (TextView) findViewById(R.id.tutorClassTextView);
        TextView tutorHoursTextView = (TextView) findViewById(R.id.tutorHoursTextView);

        TutorTimeRelation tutorTimeRelation = getIntent().getParcelableExtra("SelectedRelation");

        tutorNameTextView.setText(tutorTimeRelation.getTutor().getFirstName() + " "
                + tutorTimeRelation.getTutor().getLastName());

        DBHelper db = new DBHelper(this);
        ArrayList<TutorTimeRelation> allRelations = db.getAllRelations();
        ArrayList<Course> allTutorCourses = new ArrayList<>();
        ArrayList<DayTime> allTutorStartTimes = new ArrayList<>();
        ArrayList<DayTime> allTutorEndTimes = new ArrayList<>();

        for (TutorTimeRelation singleRelation : allRelations)
        {
            if (tutorTimeRelation.getTutor().equals(singleRelation.getTutor()))
            {
                if (!allTutorCourses.contains(singleRelation.getCourse()))
                    allTutorCourses.add(singleRelation.getCourse());
                if (!allTutorStartTimes.contains(singleRelation.getStartTime())
                && !allTutorEndTimes.contains(singleRelation.getEndTime())) {
                    allTutorStartTimes.add(singleRelation.getStartTime());
                    allTutorEndTimes.add(singleRelation.getEndTime());
                }
            }
        }

        String allCourses = "";
        for (Course singleCourse : allTutorCourses)
        {
            allCourses += singleCourse.toString() + "\n";
        }

        tutorClassTextView.setText(allCourses);

        String allTimes = "";
        for (int i = 0; i < allTutorStartTimes.size(); ++i)
        {
            DayTime startTime = allTutorStartTimes.get(i);
            DayTime endTime = allTutorEndTimes.get(i);
            allTimes += startTime.getDay() + ": "
                    + startTime.convertFloatTimeToString() + " - "
                    + endTime.convertFloatTimeToString() + "\n";
        }
        tutorHoursTextView.setText(allTimes);

    }

    public void returnToSearch(View view)
    {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
    }
}
