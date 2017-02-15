package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mTutors;

/**
 * An entrance screen that the user sees every time the app starts allowing for background
 * start up processes to take place before the user begins to interact with the program.
 */
public class SplashActivity extends AppCompatActivity {
    private final Context context = this;

    /**
     * Initializes all loaders
     * @param savedInstanceState last saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadDatabase loadDB = new LoadDatabase();
        loadDB.execute(context);
    }

    private class LoadDatabase extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... params) {
            DBHelper db = new DBHelper(params[0]);
            if (mTutors == null)
                db.syncStaticLists();

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (getResources().getConfiguration().isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_LARGE))
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.activity_splash);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }
}

