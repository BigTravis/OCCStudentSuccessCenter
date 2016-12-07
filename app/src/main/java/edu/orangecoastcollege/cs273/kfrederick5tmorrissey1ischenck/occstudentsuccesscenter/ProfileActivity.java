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
    private TextView fName;
    private TextView lName;
    private TextView studentNum;

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

        fName = (TextView) findViewById(R.id.firstNameTextView);
        lName = (TextView) findViewById(R.id.lastNameTextView);
        studentNum = (TextView) findViewById(R.id.studentNumTextView);


        mUserListAdapter = new UserListAdapter(this, R.layout.course_item, courses);

        coursesListView = (ListView) findViewById(R.id.coursesListView);
        coursesListView.setAdapter(mUserListAdapter);

        fName.setText(first);
        lName.setText(last);
        studentNum.setText(num);
    }

    public void editProfileOnClick (View v)
    {
        startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
    }

//    public void userSearchOnClick (View v)
//    {
//        if (v instanceof LinearLayout){
//
//            String selectedItem = coursesListView.getSelectedItem().toString();
//            String subject = selectedCourse.
//            if (subject.equals("[Select subject]") || classNumber.equals("[Select class]"))
//                Toast.makeText(this, "You must select a subject and class.", Toast.LENGTH_SHORT).show();
//
//            else {
//                String day = daySpinner.getSelectedItem().toString();
//                float time;
//
//                // Format time from 12 hour to 24 hour format
//                String hourString = hourSpinner.getSelectedItem().toString();
//                if (!hourString.equals("[Select hours]")) {
//                    time = Float.parseFloat(hourSpinner.getSelectedItem().toString());
//                    time += (minuteSpinner.getSelectedItem().toString().equals(":30")) ? .50f : 0.0;
//
//                    if (!(hourString.equals("9") || hourString.equals("10")
//                            || hourString.equals("11") || hourString.equals("12")))
//                        time += 12.0f;
//                }
//
//                else // User did not select a time and search will not filter by time
//                    time = 25.0f;
//
//                ArrayList<TutorTimeRelation> tutorTimeResults = new ArrayList<>();
//                for (TutorTimeRelation relation : mRelations) {
//                    Course course = relation.getCourse();
//                    DayTime startTime = relation.getStartTime();
//                    DayTime endTime = relation.getEndTime();
//
//                    if (course.getDepartment().equals(subject) && course.getNumber().equals(classNumber)) {
//                        if (!day.equals("[Select day]")) {
//                            if (startTime.getDay().equals(day)) {
//                                if (time < 25.0f) {
//                                    if (startTime.getTime() <= time && endTime.getTime() >= time)
//                                        tutorTimeResults.add(relation);
//                                }
//                                else
//                                    tutorTimeResults.add(relation);
//                            }
//
//                        }
//                        else // No time was selected and all qualified tutors available at the specified
//                            // day will be selected
//                            tutorTimeResults.add(relation);
//                    }
//                }
//
//                Intent listIntent = new Intent(this, TutorListActivity.class);
//                listIntent.putExtra("Tutor Results", tutorTimeResults);
//                listIntent.putExtra("Subject", subject);
//                listIntent.putExtra("Class Number", classNumber);
//                listIntent.putExtra("Day", daySpinner.getSelectedItem().toString());
//                listIntent.putExtra("Time", hourString + minuteSpinner.getSelectedItem().toString());
//                startActivity(listIntent);
//            }
//        }
//    }
}
