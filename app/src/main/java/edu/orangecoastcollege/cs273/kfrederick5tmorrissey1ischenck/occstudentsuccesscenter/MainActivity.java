package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;

public class MainActivity extends NavDrawerActivity {



    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
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
        startActivity(new Intent(MainActivity.this, LocationActivity.class));
    }

    public void onClickFAQ (View v)
    {
        startActivity(new Intent(MainActivity.this, FAQActivity.class));
    }

    public void onClickContact (View v)
    {
        startActivity(new Intent(MainActivity.this, ContactActivity.class));
    }


    /**
     * Attempting to check if a userInfo file exists before moving to the next activity.
     * If it does exist move to Profile Activity, if not move directly to Edit Profile Activity.
     * @param v
     */
    public void onClickProfile (View v)
    {
        //if(!db.userExists())
            startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
       // else
            //startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    public void toAskOnClick (View v)
    {
        startActivity(new Intent(MainActivity.this, ToAskActivity.class));
    }

    public void onClickResources(View v)
    {
        startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
    }
}
