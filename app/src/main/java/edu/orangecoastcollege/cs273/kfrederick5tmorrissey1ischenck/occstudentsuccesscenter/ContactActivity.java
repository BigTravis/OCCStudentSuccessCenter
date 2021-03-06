package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * this activity gives the user contact information
 * and allows them to directly call the SSC and go to the location activity
 */
public class ContactActivity extends NavDrawerActivity {

    private static final int CALL_PHONE_REQUEST_CODE = 111;
    private TextView addressTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_contact, contentFrameLayout);
    }

    /**
     * checks for call phone permissions, then sends user to call the SSC
     * @param view
     */
    public void callNumber(View view)
    {
        if (ActivityCompat.checkSelfPermission(getBaseContext(),
                android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    CALL_PHONE_REQUEST_CODE);
        }
        else{
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.fromParts("tel", "7144325559", null));
            startActivity(callIntent);
        }
    }
}