package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.app.ListActivity;
import android.os.Bundle;

public class FAQActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        //setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,R.array.faq_list))
    }
}
