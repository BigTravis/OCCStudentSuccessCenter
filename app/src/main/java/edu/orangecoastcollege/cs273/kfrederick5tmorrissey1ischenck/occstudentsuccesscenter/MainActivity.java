package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

/**
 * This is an activity that holds buttons that directs users to different parts
 * of the app
 */
public class MainActivity extends NavDrawerActivity {


    private DBHelper db;
    private  boolean phoneDevice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            phoneDevice = false;

        if (phoneDevice)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /**
     * When clicking the search button it takes the user to the search activity.
     * @param v the search button
     */
    public void onClickTutor (View v)
    {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }

    /**
     * When clicking the study groupbutton it takes the user to the study groupactivity.
     * @param v the study groupbutton
     */
    public void onClickStudy (View v)
    {
        startActivity(new Intent(MainActivity.this, StudyGroupActivity.class));
    }

    /**
     * When clicking the location button it takes the user to the location activity.
     * @param v the location button
     */
    public void onClickLocation(View v)
    {
        startActivity(new Intent(MainActivity.this, LocationActivity.class));
    }

    /**
     * When clicking the FAQ button it takes the user to the FAQ activity.
     * @param v the FAQ button
     */
    public void onClickFAQ (View v)
    {
        startActivity(new Intent(MainActivity.this, FAQActivity.class));
    }

    /**
     * When clicking the contact button it takes the user to the contact activity.
     * @param v the contact button
     */
    public void onClickContact (View v)
    {
        startActivity(new Intent(MainActivity.this, ContactActivity.class));
    }

    /**
     * When clicking the Profile button it takes the user to the Profile activity unless there is
     * no user information in which case it takes the user to the edit profile activity.
     * @param v the Profile button
     */
    public void onClickProfile (View v) {
        DBHelper db = new DBHelper(this);
        if (db.getUser(1) == null) {
            startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
        }
       else
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    /**
     * When clicking the questions for tutor button it takes the user to the
     * questions for tutor activity.
     * @param v the questions for tutor button
     */
    public void toAskOnClick (View v)
    {
        startActivity(new Intent(MainActivity.this, ToAskActivity.class));
    }

    /**
     * When clicking the resources button it takes the user to the resources activity.
     * @param v the resources button
     */
    public void onClickResources(View v)
    {
        startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
    }
}
