package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * this activity displays a list of study groups
 */
public class StudyGroupActivity extends NavDrawerActivity {

    private StudyGroupAdapter mStudyGroupAdapter;
    private ListView mStudyGroupListView;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_study_group, contentFrameLayout);
        db = new DBHelper(this);
        ArrayList<StudyGroup> studyGroups = db.getAllStudyGroups();

        mStudyGroupAdapter = new StudyGroupAdapter(this, R.layout.study_group_item, studyGroups);
        mStudyGroupListView = (ListView) findViewById(R.id.studyGroupListView);
        mStudyGroupListView.setAdapter(mStudyGroupAdapter);

    }
}
