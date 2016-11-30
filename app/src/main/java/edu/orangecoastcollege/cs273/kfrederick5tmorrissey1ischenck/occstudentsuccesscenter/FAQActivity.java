package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class FAQActivity extends ListActivity {

    private List<String> faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        setListAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.faq_list)));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {
        //Update

    }
}
