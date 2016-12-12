package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    public void goToAnswer(View view){
        String question = (getResources().getStringArray(R.array.faq_list))[7];
        String answer = (getResources().getStringArray(R.array.faq_details))[7];

        Intent faqDetailsIntent = new Intent(this, FAQDetailActivity.class);
        faqDetailsIntent.putExtra("Question", question);
        faqDetailsIntent.putExtra
                ("Answer", answer);
        startActivity(faqDetailsIntent);
    }
}
