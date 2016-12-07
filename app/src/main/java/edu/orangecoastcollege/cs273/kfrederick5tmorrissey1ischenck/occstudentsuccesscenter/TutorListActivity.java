package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TutorListActivity extends NavDrawerActivity {
    private TutorListAdapter mTutorListAdapter;
    private ListView mTutorListView;
    private TextView mTutorNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_tutor_list, contentFrameLayout);

        ArrayList<TutorTimeRelation> tutorTimeRelations = getIntent().getParcelableArrayListExtra("Tutor Results");
        String subject = getIntent().getStringExtra("Subject");
        String className = getIntent().getStringExtra("Class Number");
        String day = getIntent().getStringExtra("Day");
        String time = getIntent().getStringExtra("Time");

        mTutorListAdapter = new TutorListAdapter(this, R.layout.tutor_list_item, tutorTimeRelations);
        mTutorListView = (ListView) findViewById(R.id.tutorListView);
        mTutorListView.setAdapter(mTutorListAdapter);

        mTutorNameTextView = (TextView) findViewById(R.id.tutorNameTextView);

        String timeString = "";
        if (!day.equals("[Select day]"))
        {
            timeString += ("at " + day);
            if (!time.equals("[Select hours][Select minutes]"))
                timeString += ": " + time;
        }
        mTutorNameTextView.setText(String.format(mTutorNameTextView.getText().toString(),
                subject, className, timeString));


    }

    public void viewTutorDetails(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            TutorTimeRelation tutorTimeSelected  = (TutorTimeRelation) selectedLayout.getTag();
            Intent detailsIntent = new Intent(this, TutorDetailsActivity.class);
            detailsIntent.putExtra("SelectedRelation", tutorTimeSelected);
            startActivity(detailsIntent);
        }
    }
}
