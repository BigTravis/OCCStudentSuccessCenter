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

import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mUserCourses;
import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mRelations;

/**
 * Displays the users saved name and student number as well as their list of classes which they
 * can click on and it will take them to the list of tutors that tutor that subject.
 */
public class ProfileActivity extends NavDrawerActivity {

    private UserListAdapter mUserListAdapter;
    private TextView name;
    private TextView studentNum;

    private ListView coursesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        DBHelper db = new DBHelper(this);
        User mUser = db.getUser(1);

        String first = mUser.getfName();
        String last = mUser.getlName();
        String num = mUser.getUserNum();

        name = (TextView) findViewById(R.id.nameTextView);
        studentNum = (TextView) findViewById(R.id.studentNumTextView);


        mUserListAdapter = new UserListAdapter(this, R.layout.course_item, mUserCourses);

        coursesListView = (ListView) findViewById(R.id.coursesListView);
        coursesListView.setAdapter(mUserListAdapter);

        name.setText(first + " " + last);
        studentNum.setText(num);
    }

    /**
     * When the text for the edit profile is clicked it will take the user to the
     * edit profile activity so they can change their info and classes
     * @param v the edit profile text view
     */
    public void editProfileOnClick(View v) {
        startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
    }

    /**
     * When one of the list items in the users course list is selected it will run a
     * search for the tutors that tutor that subject.
     * @param v the selected course list item the user selects
     */
    public void userSearchOnClick(View v) {
        if (v instanceof LinearLayout) {
            LinearLayout selectedItem = (LinearLayout) v;
            UserCourse selectedCourse = (UserCourse) selectedItem.getTag();

            if (selectedCourse.getDepartment().equals("ENGL")) {
                Toast.makeText(this, getString(R.string.english_toast), Toast.LENGTH_SHORT).show();
            } else {
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
                if (tutorTimeResults.isEmpty())
                    Toast.makeText(this, R.string.no_tutors_error, Toast.LENGTH_LONG).show();
                else
                    startActivity(listIntent);
            }
        }
        }
    }
