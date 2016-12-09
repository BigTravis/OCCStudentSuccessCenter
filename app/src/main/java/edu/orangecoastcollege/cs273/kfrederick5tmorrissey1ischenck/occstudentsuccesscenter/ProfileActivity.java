package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends NavDrawerActivity {

    private DBHelper userDB = new DBHelper(this);
    private UserListAdapter mUserListAdapter;
    private TextView name;
    private TextView studentNum;
    private List<TutorTimeRelation> mRelations;

    private List<UserCourse> courses;
    private ListView coursesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        User user = userDB.getUser(1);

        courses = userDB.getAllUserCourses();
        String first = user.getfName();
        String last = user.getlName();
        String num = user.getUserNum();

        mRelations = userDB.getAllRelations();

        name = (TextView) findViewById(R.id.nameTextView);
        studentNum = (TextView) findViewById(R.id.studentNumTextView);


        mUserListAdapter = new UserListAdapter(this, R.layout.course_item, courses);

        coursesListView = (ListView) findViewById(R.id.coursesListView);
        coursesListView.setAdapter(mUserListAdapter);

        name.setText(first + " " + last);
        studentNum.setText(num);
    }

    public void editProfileOnClick(View v) {
        startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
    }

    public void userSearchOnClick(View v) {
        if (v instanceof LinearLayout) {
            LinearLayout selectedItem = (LinearLayout) v;
            UserCourse selectedCourse = (UserCourse) selectedItem.getTag();



                ArrayList<TutorTimeRelation> tutorTimeResults = new ArrayList<>();
                for (TutorTimeRelation relation : mRelations) {
                    Course course = relation.getCourse();
//                    DayTime startTime = relation.getStartTime();
//                    DayTime endTime = relation.getEndTime();

                    if (course.getDepartment().equals(selectedCourse.getDepartment()) &&
                            course.getNumber().equals(selectedCourse.getNumber())) {
                            tutorTimeResults.add(relation);
                    }
                }

                Intent listIntent = new Intent(this, TutorListActivity.class);
                listIntent.putExtra("Tutor Results", tutorTimeResults);
                listIntent.putExtra("Subject", selectedCourse.getDepartment());
                listIntent.putExtra("Class Number", selectedCourse.getNumber());
                listIntent.putExtra("Day", "[Select day]");
                listIntent.putExtra("Time", "[Select hour]");
            if(tutorTimeResults.isEmpty())
                Toast.makeText(this, R.string.no_tutors_error, Toast.LENGTH_LONG).show();
            else
                startActivity(listIntent);
            }
        }
    }
