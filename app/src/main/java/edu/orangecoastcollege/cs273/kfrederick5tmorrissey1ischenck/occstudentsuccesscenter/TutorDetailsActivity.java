package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class TutorDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_details);

        TextView tutorNameTextView = (TextView) findViewById(R.id.tutorNameTextView);
        TextView tutorClassTextView = (TextView) findViewById(R.id.tutorClassTextView);
        TextView tutorHoursTextView = (TextView) findViewById(R.id.tutorHoursTextView);

        TutorTimeRelation tutorTimeRelation = getIntent().getParcelableExtra("SelectedRelation");

        tutorNameTextView.setText(tutorTimeRelation.getTutor().getFirstName() + " "
                + tutorTimeRelation.getTutor().getLastName());

        DBHelper db = new DBHelper(this);
        ArrayList<TutorTimeRelation> allRelations = db.getAllRelations();
        String allCourses = "";
        for (TutorTimeRelation singleRelation : allRelations)
        {
            if (tutorTimeRelation.getTutor().equals(singleRelation.getTutor()))
            {
                allCourses += singleRelation.getCourse().toString() + "\n";
            }
        }

        tutorClassTextView.setText(allCourses);
        tutorHoursTextView.setText(tutorTimeRelation.getStartTime().convertFloatTimeToString() + " - "
                + tutorTimeRelation.getEndTime().convertFloatTimeToString());

    }

    public void returnToSearch(View view)
    {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
    }
}
