package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mTutors;

public class SplashActivity extends AppCompatActivity {
    private DBHelper db;
    private final Context context = this;
    private  ImageView occLogoImageView;

    /**
     * An entrance screen that the user sees every time the app starts allowing for background
     * start up processes to take place before the user begins to interact with the program.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        occLogoImageView = (ImageView) findViewById(R.id.occLogoImageView);




//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 1500);



        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

               // deleteDatabase(DBHelper.DATABASE_NAME);
                db = new DBHelper(context);
                if (mTutors == null)
                    db.syncStaticLists();

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },1000);
    }


}

