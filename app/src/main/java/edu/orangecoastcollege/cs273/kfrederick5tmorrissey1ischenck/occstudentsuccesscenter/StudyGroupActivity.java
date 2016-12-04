package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class StudyGroupActivity extends AppCompatActivity {

    private StudyGroupAdapter mStudyGroupAdapter;
    private ListView mStudyGroupListView;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_group);
        db = new DBHelper(this);
        ArrayList<StudyGroup> studyGroups = db.getAllStudyGroups();

        mStudyGroupAdapter = new StudyGroupAdapter(this, R.layout.study_group_item, studyGroups);
        mStudyGroupListView = (ListView) findViewById(R.id.studyGroupListView);
        mStudyGroupListView.setAdapter(mStudyGroupAdapter);

    }
}
