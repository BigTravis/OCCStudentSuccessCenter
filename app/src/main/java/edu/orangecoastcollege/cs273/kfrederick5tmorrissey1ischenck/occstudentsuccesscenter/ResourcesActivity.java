package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
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
