package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView fName;
    private TextView lName;
    private TextView studentNum;
    private ListView subjectClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fName = (TextView) findViewById(R.id.firstNameTextView);
        lName = (TextView) findViewById(R.id.lastNameTextView);
        studentNum = (TextView) findViewById(R.id.studentNumTextView);
        subjectClass = (ListView) findViewById(R.id.subjectClassListView);
    }

    public void editProfileOnClick (View v)
    {
        Intent editProfile = new Intent(ProfileActivity.this, EditProfileActivity.class);


        startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
    }
}
