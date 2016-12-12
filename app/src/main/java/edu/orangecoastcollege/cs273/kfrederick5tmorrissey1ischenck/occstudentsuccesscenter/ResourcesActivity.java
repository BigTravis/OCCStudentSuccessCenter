package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Holds a series of buttons that link to additional online resources for students
 * also, if you shake the phone, the links do a spin
 */
public class ResourcesActivity extends NavDrawerActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private Animation rotateAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_resources, contentFrameLayout);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final ImageView blackboardTextView = (ImageView) findViewById(R.id.blackboardTextView);
        final ImageView khanAcademyTextView = (ImageView) findViewById(R.id.khanAcademyTextView);
        final ImageView codecademyTextView = (ImageView) findViewById(R.id.codecademyTextView);

        rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);

        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                blackboardTextView.startAnimation(rotateAnim);
                khanAcademyTextView.startAnimation(rotateAnim);
                codecademyTextView.startAnimation(rotateAnim);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start accelerometer
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }

    /**
     * When the Blackboard image is clicked it directs the user to the OCC blackboard login page
     * @param v blackboard image view
     */
    public void blackboardOnClick(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://occ.blackboard.com/")));
    }

    /**
     * When the Khan Academy image is clicked takes the user to the homepage for Khan Academy
     * @param v Khan Academy image view
     */
    public void khanAcademyOnClick(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.khanacademy.org/")));
    }

    /**
     * When the Codecademy image is pressed it links the user to the Codecademy homepage
     * @param v Codecademy image view
     */
    public void codecademyOnClick(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.codecademy.com/")));
    }
}
