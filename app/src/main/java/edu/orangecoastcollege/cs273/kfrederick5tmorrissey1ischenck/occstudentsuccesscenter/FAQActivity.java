package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FAQActivity extends AppCompatActivity {

    private FaqListAdapter mFaqListAdapter;
    private ListView mFAQListView;
    private String[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        questions = getResources().getStringArray(R.array.faq_list);
        List<String> questionsList = new ArrayList<>(Arrays.asList(questions));
        //faqListAdapter = new ListAdapter (this, R.layout.activity_faq_item, )

        mFAQListView = (ListView) findViewById(R.id.faqListView);
        mFaqListAdapter = new FaqListAdapter(this, R.layout.faq_list_item, questionsList);

        mFAQListView.setAdapter(mFaqListAdapter);
    }

    public void viewAnswer(View view)
    {
        if (view instanceof LinearLayout)
        {
            LinearLayout selectedLayout = (LinearLayout) view;
            String questionSelected = (String) selectedLayout.getTag();
            String[] answers = getResources().getStringArray(R.array.faq_details);
            List<String> answerList = new ArrayList<>(Arrays.asList(answers));
            Intent faqDetails = new Intent(this, FAQDetailActivity.class);
            faqDetails.putExtra("Question", questionSelected);
            faqDetails.putExtra
                    ("Answer", answerList.get(mFaqListAdapter.getPosition(questionSelected)));
            startActivity(faqDetails);

        }
    }
}
