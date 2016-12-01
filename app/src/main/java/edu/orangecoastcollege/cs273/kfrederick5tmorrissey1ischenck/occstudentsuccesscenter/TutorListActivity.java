package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TutorListActivity extends AppCompatActivity {
    private TutorListAdapter mTutorListAdapter;
    private ListView mTutorListView;
    private TextView mTutorNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);
        ArrayList<Tutor> tutors = getIntent().getParcelableArrayListExtra("Tutor Results");
        String subject = getIntent().getStringExtra("Subject");
        String className = getIntent().getStringExtra("Class Number");
        String time = getIntent().getStringExtra("Time");

        mTutorListAdapter = new TutorListAdapter(this, R.layout.tutor_list_item, tutors);
        mTutorListView = (ListView) findViewById(R.id.tutorListView);
        mTutorListView.setAdapter(mTutorListAdapter);

        mTutorNameTextView = (TextView) findViewById(R.id.tutorNameTextView);
        mTutorNameTextView.setText(String.format(mTutorNameTextView.getText().toString(),
                subject, className, time));


    }

    public void viewTutorDetails(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Tutor tutorSelected  = (Tutor) selectedLayout.getTag();
            Intent detailsIntent = new Intent(this, TutorDetailsActivity.class);
            detailsIntent.putExtra("Selected tutor", tutorSelected);
            startActivity(detailsIntent);
        }
    }
}
