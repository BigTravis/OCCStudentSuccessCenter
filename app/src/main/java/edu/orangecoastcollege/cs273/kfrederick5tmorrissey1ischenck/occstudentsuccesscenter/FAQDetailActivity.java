package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * this activity displays the question the user clicked on in faqactivity
 * as well as the answer it is connected to
 */
public class FAQDetailActivity extends NavDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_faqdetail, contentFrameLayout);

        Intent intent = getIntent();

        TextView faqDetailQuestionTextView = (TextView) findViewById(R.id.faqDetailQuestionTextView);
        TextView faqDetailAnswerTextView = (TextView) findViewById(R.id.faqDetailAnswerTextView);

        faqDetailQuestionTextView.setText(intent.getStringExtra("Question"));
        faqDetailAnswerTextView.setText(intent.getStringExtra("Answer"));
    }
}
