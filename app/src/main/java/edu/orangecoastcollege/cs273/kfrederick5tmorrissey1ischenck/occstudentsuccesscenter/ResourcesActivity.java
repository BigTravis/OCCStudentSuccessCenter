package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class ResourcesActivity extends NavDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_resources, contentFrameLayout);
    }

    public void blackboardOnClick(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://occ.blackboard.com/")));
    }

    public void khanAcademyOnClick(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.khanacademy.org/")));
    }

    public void codecademyOnClick(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.codecademy.com/")));
    }
}
