package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * this activity is a list of FAQs
 */
public class FAQActivity extends NavDrawerActivity {

    private FaqListAdapter mFaqListAdapter;
    private ListView mFAQListView;
    private String[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_faq, contentFrameLayout);

        questions = getResources().getStringArray(R.array.faq_list);
        List<String> questionsList = Arrays.asList(questions);

        mFaqListAdapter = new FaqListAdapter(this, R.layout.faq_list_item, questionsList);

        mFAQListView = (ListView) findViewById(R.id.faqListView);

        mFAQListView.setAdapter(mFaqListAdapter);
    }

    /**
     * this activity is called when you click a particular questions
     * it sends user to the faq details activity
     * @param view what the user clicks on
     */
    public void viewAnswer(View view)
    {
        if (view instanceof TextView)
        {
            TextView selectedView = (TextView) view;
            String questionSelected = (String) selectedView.getTag();

            questions = getResources().getStringArray(R.array.faq_list);
            List<String> questionsList = Arrays.asList(questions);
            int pos = questionsList.indexOf(questionSelected);
            String[] answers = getResources().getStringArray(R.array.faq_details);

            Intent faqDetailsIntent = new Intent(this, FAQDetailActivity.class);
            faqDetailsIntent.putExtra("Question", questionSelected);
            faqDetailsIntent.putExtra
                    ("Answer", answers[pos]);
            startActivity(faqDetailsIntent);

        }
    }
}
