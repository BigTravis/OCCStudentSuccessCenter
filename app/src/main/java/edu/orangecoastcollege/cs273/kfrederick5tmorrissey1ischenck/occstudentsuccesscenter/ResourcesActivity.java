package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Holds a series of buttons that link to additional online resources for students
 */
public class ResourcesActivity extends NavDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_resources, contentFrameLayout);
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
