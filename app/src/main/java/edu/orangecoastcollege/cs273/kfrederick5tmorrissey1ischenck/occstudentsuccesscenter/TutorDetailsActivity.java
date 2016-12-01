package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TutorDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_details);

        TextView tutorNameTextView = (TextView) findViewById(R.id.tutorNameTextView);
        TextView tutorClassTextView = (TextView) findViewById(R.id.tutorClassTextView);
        TextView tutorHoursTextView = (TextView) findViewById(R.id.tutorHoursTextView);

        Tutor tutor = getIntent().getParcelableExtra("Selected tutor");

        tutorNameTextView.setText(tutor.getFirstName() + " " + tutor.getLastName());
    }
}
