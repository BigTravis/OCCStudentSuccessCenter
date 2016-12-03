package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTutor (View v)
    {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }

    public void onClickStudy (View v)
    {
        startActivity(new Intent(MainActivity.this, StudyGroupActivity.class));
    }

    public void onClickLocation(View v)
    {
        startActivity(new Intent(MainActivity.this, LocationsListActivity.class));
    }


    public void onClickFAQ (View v)
    {
        startActivity(new Intent(MainActivity.this, FAQActivity.class));
    }


    public void onClickContact (View v)
    {
        startActivity(new Intent(MainActivity.this, ContactActivity.class));
    }

    public void onClickProfile (View v)
    {

    }

}
