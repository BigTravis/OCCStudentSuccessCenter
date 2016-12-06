package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ProfileActivity extends NavDrawerActivity {

    private DBHelper userDB;
    private UserListAdapter mUserListAdapter;
    private TextView fName;
    private TextView lName;
    private TextView studentNum;
    private List<User> courses;
    private ListView coursesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        //userDB = new DBHelper(this);

        //courses = userDB.getAllUserCourses();

        fName = (TextView) findViewById(R.id.firstNameTextView);
        lName = (TextView) findViewById(R.id.lastNameTextView);
        studentNum = (TextView) findViewById(R.id.studentNumTextView);

       // mUserListAdapter = new UserListAdapter(this, R.layout.course_item, courses);

        coursesListView = (ListView) findViewById(R.id.coursesListView);

        //User user = userDB.getUser(0);
        //fName.setText(user.getfName());
        //lName.setText(user.getlName());
        //studentNum.setText(user.getUserNum());

       // coursesListView.setAdapter(mUserListAdapter);


    }

    public void editProfileOnClick (View v)
    {
        startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
    }
}
