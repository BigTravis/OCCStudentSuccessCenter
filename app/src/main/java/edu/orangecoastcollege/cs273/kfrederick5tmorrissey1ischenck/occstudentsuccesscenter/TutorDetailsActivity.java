package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mRelations;

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



        ArrayList<Course> allTutorCourses = new ArrayList<>();
        ArrayList<DayTime> allTutorStartTimes = new ArrayList<>();
        ArrayList<DayTime> allTutorEndTimes = new ArrayList<>();
        String allCourses = "", allTimes = "";

        for (TutorTimeRelation singleRelation : mRelations)
        {
            if (tutorTimeRelation.getTutor().equals(singleRelation.getTutor()))
            {
                if (!allTutorCourses.contains(singleRelation.getCourse())) {
                    allTutorCourses.add(singleRelation.getCourse());
                    allCourses += singleRelation.getCourse().toString() + "\n";
                }

                if (!allTutorStartTimes.contains(singleRelation.getStartTime())
                && !allTutorEndTimes.contains(singleRelation.getEndTime())) {

                    DayTime startTime = singleRelation.getStartTime();
                    DayTime endTime = singleRelation.getEndTime();
                    allTutorStartTimes.add(startTime);
                    allTutorEndTimes.add(endTime);

                    allTimes += startTime.getDay() + ": "
                            + startTime.convertFloatTimeToString() + " - "
                            + endTime.convertFloatTimeToString() + "\n";
                }
            }
        }

        tutorClassTextView.setText(allCourses);
        tutorHoursTextView.setText(allTimes);
    }

    public void returnToSearch(View view)
    {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
    }
}
