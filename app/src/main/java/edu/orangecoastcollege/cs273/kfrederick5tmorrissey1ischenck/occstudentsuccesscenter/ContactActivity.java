package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.FrameLayout;

/**
 * this activity gives the user contact information
 * and allows them to direc
 */
public class ContactActivity extends NavDrawerActivity {

    private static final int CALL_PHONE_REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_contact, contentFrameLayout);

//        findViewById(R.id.phoneNumberTextView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ActivityCompat.checkSelfPermission(getBaseContext(),
//                        android.Manifest.permission.CALL_PHONE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(getParent(),
//                            new String[]{android.Manifest.permission.CALL_PHONE},
//                            CALL_PHONE_REQUEST_CODE);
//                }
//                else{
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.fromParts("tel", "7144325559", null));
//                    startActivity(callIntent);
//                }
//            }
//        });

        findViewById(R.id.addressTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactActivity.this, LocationActivity.class));
            }
        });
    }

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