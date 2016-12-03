package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FAQActivity extends ListActivity {

    private ArrayAdapter faqListAdapter;
    private ListView mFAQListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        String[] questions = getResources().getStringArray(R.array.faq_list);


        //faqListAdapter = new ListAdapter (this, R.layout.activity_faq_item, )
        mFAQListView = (ListView) findViewById(R.id.faqListView);
        faqListAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, questions);

        mFAQListView.setAdapter(faqListAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {
        String[] temp = getResources().getStringArray(R.array.faq_details);

        Intent faqDetails = new Intent(this, FAQDetailActivity.class);
        faqDetails.putExtra("Answer", temp[pos]);




        startActivity(faqDetails);
    }
}
