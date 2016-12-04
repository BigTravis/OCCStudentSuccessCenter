package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;

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


    /**
     * Attempting to check if a userInfo file exists before moving to the next activity.
     * If it does exist move to Profile Activity, if not move directly to Edit Profile Activity.
     * @param v
     */
    public void onClickProfile (View v)
    {
        this.getFilesDir();
        File userInfo = new File(userInfo);

        if(fileExists(userInfo))

        Profile user = new Profile();
        if(user.getFirstName() == "")
        startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
        else
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    public void toAskOnClick (View v)
    {
        startActivity(new Intent(MainActivity.this, ToAskActivity.class));
    }

    public void onClickResources(View v)
    {
        startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
    }

    public boolean fileExists(String userInfo)
    {
        File file = getBaseContext().getFileStreamPath(userInfo);
        return file.exists();
    }
}
