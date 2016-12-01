package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        mTutorListAdapter = new TutorListAdapter(this, R.layout.tutor_list_item, tutors);
        mTutorListView = (ListView) findViewById(R.id.tutorListView);
        mTutorListView.setAdapter(mTutorListAdapter);


    }
}
