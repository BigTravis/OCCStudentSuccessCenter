package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FAQDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqdetail);

        Intent intent = getIntent();

        TextView faqDetailQuestionTextView = (TextView) findViewById(R.id.faqDetailQuestionTextView);
        TextView faqDetailAnswerTextView = (TextView) findViewById(R.id.faqDetailAnswerTextView);

        faqDetailQuestionTextView.setText(intent.getStringExtra("Question"));
        faqDetailAnswerTextView.setText(intent.getStringExtra("Answer"));
    }
}
