package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
        List<String> questionsList = Arrays.asList(questions);
        //faqListAdapter = new ListAdapter (this, R.layout.activity_faq_item, )

        mFaqListAdapter = new FaqListAdapter(this, R.layout.faq_list_item, questionsList);

        mFAQListView = (ListView) findViewById(R.id.faqListView);

        mFAQListView.setAdapter(mFaqListAdapter);
    }

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
