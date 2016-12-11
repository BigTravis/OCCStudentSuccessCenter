package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mCourses;
import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mUserCourses;

/**
 * This activity allows the user to add their name and student number to the app
 * along with a list of their classes.
 */
public class EditProfileActivity extends NavDrawerActivity {

    private DBHelper db;
    private ListView editCourseListView;
    private ProfileListAdapter mProfileAdapter;

    private EditText fName;
    private EditText lName;
    private EditText studentNum;
    private Spinner subjectSpinner;
    private Spinner classSpinner;
    private Animation slide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_edit_profile, contentFrameLayout);

        db = new DBHelper(this);

        mProfileAdapter = new ProfileListAdapter(this, R.layout.edit_course_item, mUserCourses);

        fName = (EditText) findViewById(R.id.firstNameEditText);
        lName = (EditText) findViewById(R.id.lastNameEditText);
        studentNum = (EditText) findViewById(R.id.studentNumEditText);
        subjectSpinner = (Spinner) findViewById(R.id.editSubjectSpinner);
        classSpinner = (Spinner) findViewById(R.id.editClassSpinner);

        if (db.getUser(1) != null) {
            User mUser = db.getUser(1);
            fName.setText(mUser.getfName());
            lName.setText(mUser.getlName());
            studentNum.setText(mUser.getUserNum());
        }
        editCourseListView = (ListView) findViewById(R.id.editCourseListView);

        editCourseListView.setAdapter(mProfileAdapter);

        ArrayAdapter<String> subjectSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllSubjectsNames());
        subjectSpinner.setAdapter(subjectSpinnerAdapter);
        subjectSpinner.setOnItemSelectedListener(subjectSpinnerListener);

        ArrayAdapter<String> classSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllClassNumbers());
        classSpinner.setAdapter(classSpinnerAdapter);
        classSpinner.setOnItemSelectedListener(classSpinnerListener);
    }

    /**
     * When the save info button is clicked the users name and number they provided will be saved
     * to the database
     * @param v the save info button
     */
    public void saveInfoOnClick(View v) {
        String first, last, num;
        first = fName.getText().toString();
        last = lName.getText().toString();
        num = studentNum.getText().toString();

        if (first.isEmpty() || last.isEmpty() || num.isEmpty())
            Toast.makeText(this, R.string.first_or_last_error, Toast.LENGTH_SHORT).show();
        else if (db.getUser(1) != null) {
            User user = db.getUser(1);
            user.setfName(first);
            user.setlName(last);
            user.setUserNum(num);
            db.updateUser(user);
            Toast.makeText(this, R.string.update_successful, Toast.LENGTH_SHORT).show();
        } else {
            User newUser = new User(1, first, last, num);

            db.addUser(newUser);

            Toast.makeText(this, R.string.save_successful, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * When the add class button is clicked a class is added to the UserCourses database
     * and display lists
     * @param v what the user clicks
     */
    public void addClassOnClick(View v) {
        String subject = subjectSpinner.getSelectedItem().toString();
        String course = classSpinner.getSelectedItem().toString();

        if (subject.equals(getString(R.string.default_subject_search)) ||
                course.equals(getString(R.string.default_course_search)))
            Toast.makeText(this, R.string.add_class_error,
                    Toast.LENGTH_SHORT).show();
        else {
            UserCourse newCourse = new UserCourse(0, subjectSpinner.getSelectedItem().toString(),
                    classSpinner.getSelectedItem().toString(), 0);

            mProfileAdapter.add(newCourse);
            editCourseListView.requestFocus(mUserCourses.size());

            db.addUserCourse(newCourse);

            subjectSpinner.setSelection(0);
            classSpinner.setSelection(0);
        }
    }

    /**
     * Changes the checkbox value on the indicated selected item depending on its current
     * status.
     * @param v the checkbox the user is selecting
     */
    public void changeCourseStatus(View v) {
        if (v instanceof CheckBox) {
            CheckBox selectedCheck = (CheckBox) v;
            UserCourse selectedCourse = (UserCourse) selectedCheck.getTag();

            selectedCourse.setIsSelected(selectedCheck.isChecked() ? 1 : 0);

            db.updateCourse(selectedCourse);
        }
    }


    /**
     * When the remove selected button is pushed this removes all courses that
     * are checked.
     * @param v the button being pressed
     */
    public void removeSelectedOnClick(View v) {
        removeListItem();
    }

    /**
     * Performs the actual removal of courses by running an animation of them sliding off
     * screen and then removing from the courses database and lists.
     */
    public void removeListItem()
    {
        slide = AnimationUtils.loadAnimation(this, R.anim.slide_off_anim);
        for (int i = 0; i < mUserCourses.size(); i++) {
            if (mUserCourses.get(i).getIsSelected() == 1) {
                View item = editCourseListView.getChildAt(i);
                item.startAnimation(slide);
                item.setVisibility(View.INVISIBLE);
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mUserCourses.size(); i++) {
                    if (mUserCourses.get(i).getIsSelected() == 1) {
                        mUserCourses.remove(i);
                        i--;
                    }
                }

                mProfileAdapter.notifyDataSetChanged();
                db.deleteSelectedCourses();
            }
        }, 300);
    }

    /**
     * Allows for selection of a subject and updates the spinners value when a
     * subject is chosen by the user.
     */
    public AdapterView.OnItemSelectedListener subjectSpinnerListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedSubject = parent.getItemAtPosition(position).toString();
                    if (!selectedSubject.equals(getString(R.string.default_subject_search))) {
                        classSpinner.setEnabled(true);
                        updateClassSpinner(selectedSubject);
                    } else {
                        classSpinner.setEnabled(false);
                        classSpinner.setSelection(0);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };

    /**
     * Allows for selection of a class and updates the spinners value when a
     * class is chosen by the user.
     */
    public AdapterView.OnItemSelectedListener classSpinnerListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // String selectedClass = parent.getItemAtPosition(position).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };

    /**
     * Activates the class spinner if the subject spinner has a value different from the default.
     * @param input value of the subject spinner.
     */
    private void updateClassSpinner(String input) {
        ArrayList<String> modifiedUserList = new ArrayList<>();
        modifiedUserList.add(getString(R.string.default_course_search));
        for (Course course : mCourses)
            if (course.getDepartment().equals(input))
                modifiedUserList.add(course.getNumber());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                modifiedUserList);
        classSpinner.setAdapter(adapter);
    }

    /**
     * Populates all the subjects from an array and allows them to be displayed for selection
     * @return returns the array of subject
     */
    private String[] getAllSubjectsNames() {
        ArrayList<String> subjectNames = new ArrayList<>();
        subjectNames.add(getString(R.string.default_subject_search));
        int size = mCourses.size();

        for (int i = 0; i < size; ++i) {
            String subject = mCourses.get(i).getDepartment();
            if (!subjectNames.contains(subject))
                subjectNames.add(subject);
        }
        return subjectNames.toArray(new String[0]);
    }

    /**
     * Populates the class spinner for user selection
     * @return returns an string array of all the classes
     */
    private String[] getAllClassNumbers() {
        String[] classNumbers = new String[mCourses.size() + 1];
        classNumbers[0] = getString(R.string.default_course_search);
        int size = classNumbers.length;

        for (int i = 1; i < size; ++i)
            classNumbers[i] = mCourses.get(i - 1).getNumber();

        return classNumbers;
    }

    /**
     * When the return to profile button is clicked this saves the users latest info changes
     * then sends them back to the profile
     * @param v
     */
    public void returnToProfileOnClick(View v) {
        if (db.getUser(1) == null)
            Toast.makeText(this, "Please save user info before returning to the Profile page",
                    Toast.LENGTH_LONG).show();
        else {
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}

