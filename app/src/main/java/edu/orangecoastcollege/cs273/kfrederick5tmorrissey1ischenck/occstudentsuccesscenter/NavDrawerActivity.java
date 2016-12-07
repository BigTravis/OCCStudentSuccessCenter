package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class NavDrawerActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(navListener);

    }

    NavigationView.OnNavigationItemSelectedListener navListener =  new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            if (item.isChecked()) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
            if (item.isChecked()) {
                drawerLayout.closeDrawer(GravityCompat.START);
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
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);

        else
            super.onBackPressed();
    }

    public void nav_main_menu(View v)
    {
        startActivity(new Intent(NavDrawerActivity.this, MainActivity.class));
    }
}
