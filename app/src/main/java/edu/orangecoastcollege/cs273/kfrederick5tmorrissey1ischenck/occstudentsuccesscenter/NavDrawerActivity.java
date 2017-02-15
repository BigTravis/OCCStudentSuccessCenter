package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Controller for Navigation Drawer.
 * Handles all events involving the Navigation Drawer
 */
public class NavDrawerActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected Toolbar toolbar;
    private boolean phoneDevice = true;

    /**
     * Initializes all loaders
     * @param savedInstanceState last saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        navigationView.setNavigationItemSelectedListener(navListener);

//        int screenSize = getResources().getConfiguration().screenLayout &
//                Configuration.SCREENLAYOUT_SIZE_MASK;

        if (getResources().getConfiguration().isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_LARGE)) {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            phoneDevice = false;
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            drawerLayout.setScrimColor(0x000000);
            toolbar.setVisibility(View.GONE);
        }

        if (phoneDevice) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
        }
    }

    NavigationView.OnNavigationItemSelectedListener navListener =  new NavigationView.OnNavigationItemSelectedListener() {

        /**
         * Navigates to selected item's activity
         * @param item item selected
         * @return true to display the item as the selected item
         */
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            if (item.isChecked()) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }

            switch (item.getItemId()) {

                case R.id.nav_tutor_search:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    break;

                case R.id.nav_study_group:
                    startActivity(new Intent(getApplicationContext(), StudyGroupActivity.class));
                    break;

                case R.id.nav_faq:
                    startActivity(new Intent(getApplicationContext(), FAQActivity.class));
                    break;

                case R.id.nav_contact:
                    startActivity(new Intent(getApplicationContext(), ContactActivity.class));
                    break;

                case R.id.nav_location:
                    startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                    break;

                case R.id.nav_resources:
                    startActivity(new Intent(getApplicationContext(), ResourcesActivity.class));
                    break;

                case R.id.nav_questions_for_tutor:
                    startActivity(new Intent(getApplicationContext(), ToAskActivity.class));
                    break;

                case R.id.nav_profile:
                    DBHelper db = new DBHelper(getApplicationContext());
                    if (db.getUser(1) == null) {
                        startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                    }
                    else
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    break;
            }
            return true;
        }
    };

    /**
     * Syncs actionBarDrawerToggle
     * @param savedInstanceState last saved state
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (phoneDevice)
            actionBarDrawerToggle.syncState();
    }

    /**
     * Handles back pressed
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START) && phoneDevice)
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }




    /**
     * Starts mainActivity when occ symbol is clicked
     * @param v view
     */
    public void nav_main_menu(View v)
    {
        startActivity(new Intent(NavDrawerActivity.this, MainActivity.class));
    }
}
