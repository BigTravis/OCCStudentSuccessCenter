package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


/**
 * uses accelerometer to detect shakes past a certain threshold
 */
public class ShakeDetector implements SensorEventListener {

    // constant to rep a shake threshold
    private static final float SHAKE_THRESHOLD = 25f;
    // constant to rep how long between shakes
    private static final int SHAKE_TIME_LAPSE = 2000;

    // what was the last time the event occurred
    private long timeOfLastShake;

    // define a listener to register onShake events
    private OnShakeListener shakeListener;

    // make constructor to create new ShakeDetector passing in an OnShakeListener
    public ShakeDetector(OnShakeListener listener)
    {
        shakeListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //determine first if the event is an accelerometer
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            // get the x, y, and z values when this event occurs:
            //value[0] = x, 1 = y, 2 = z
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            //compare all 3 values against gravity
            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            // Compute sum of squares

            double vector = Math.pow(gForceX, 2.0) + Math.pow(gForceY, 2.0) +
                    Math.pow(gForceZ, 2.0);
            // compute gForce
            float gForce = (float) Math.sqrt(vector);

            //compare gForce against SHAKE_THRESHOLD
            if (gForce > SHAKE_THRESHOLD)
            {
                // get current time
                long now = System.currentTimeMillis();
                if (now - timeOfLastShake >= SHAKE_TIME_LAPSE)
                {
                    timeOfLastShake = now;

                    // register a shake event
                    shakeListener.onShake();


                }

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    // Define our own interface (method for other classes to implement)
    // called onShake()
    // it's the responsibility of the MagicAnswerActivity to do this
    public interface OnShakeListener {
        void onShake();
    }
}
