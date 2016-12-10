package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends NavDrawerActivity {

    private DBHelper db;
    private List<Course> mCourses;
    private List<UserCourse> userCourseList;
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

        User user;


        mCourses = db.getAllCourses();
        userCourseList = db.getAllUserCourses();

        mProfileAdapter = new ProfileListAdapter(this, R.layout.edit_course_item, userCourseList);

        fName = (EditText) findViewById(R.id.firstNameEditText);
        lName = (EditText) findViewById(R.id.lastNameEditText);
        studentNum = (EditText) findViewById(R.id.studentNumEditText);
        subjectSpinner = (Spinner) findViewById(R.id.editSubjectSpinner);
        classSpinner = (Spinner) findViewById(R.id.editClassSpinner);

        if (db.userExists()) {
            user = db.getUser(1);
            fName.setText(user.getfName());
            lName.setText(user.getlName());
            studentNum.setText(user.getUserNum());
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

    public void saveInfoOnClick(View v) {
        String first, last, num;
        first = fName.getText().toString();
        last = lName.getText().toString();
        num = studentNum.getText().toString();

        if (first.isEmpty() || last.isEmpty() || num.isEmpty())
            Toast.makeText(this, R.string.first_or_last_error, Toast.LENGTH_SHORT).show();
        else if (db.userExists()) {
            User user = db.getUser(1);
            user.setfName(first);
            user.setlName(last);
            user.setUserNum(num);
            db.updateUser(user);
            Toast.makeText(this, R.string.update_successful, Toast.LENGTH_SHORT).show();
        } else {
            User newUser = new User(1, first, last, num);

            db.updateUser(newUser);

            Toast.makeText(this, R.string.save_successful, Toast.LENGTH_SHORT).show();
        }
    }

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
            editCourseListView.requestFocus(userCourseList.size());

            db.addUserCourse(newCourse);

            subjectSpinner.setSelection(0);
            classSpinner.setSelection(0);
        }
    }

    public void changeCourseStatus(View v) {
        if (v instanceof CheckBox) {
            CheckBox selectedCheck = (CheckBox) v;
            UserCourse selectedCourse = (UserCourse) selectedCheck.getTag();

            selectedCourse.setIsSelected(selectedCheck.isChecked() ? 1 : 0);

            db.updateCourse(selectedCourse);
        }
    }


    public void removeSelectedOnClick(View v) {
        removeListItem();
    }

    public void removeListItem()
    {
        slide = AnimationUtils.loadAnimation(this, R.anim.slide_off_anim);
        for (int i = 0; i < userCourseList.size(); i++) {
            if (userCourseList.get(i).getIsSelected() == 1) {
                View item = editCourseListView.getChildAt(i);
                item.startAnimation(slide);
                item.setVisibility(View.INVISIBLE);
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < userCourseList.size(); i++) {
                    if (userCourseList.get(i).getIsSelected() == 1) {
                        userCourseList.remove(i);
                        i--;
                    }
                }

                mProfileAdapter.notifyDataSetChanged();
                db.deleteSelectedCourses();
            }
        }, 500);
    }

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

    private String[] getAllClassNumbers() {
        String[] classNumbers = new String[mCourses.size() + 1];
        classNumbers[0] = getString(R.string.default_course_search);
        int size = classNumbers.length;

        for (int i = 1; i < size; ++i)
            classNumbers[i] = mCourses.get(i - 1).getNumber();

        return classNumbers;
    }

    public void returnToProfileOnClick(View v) {
        String first, last, num;
        first = fName.getText().toString();
        last = lName.getText().toString();
        num = studentNum.getText().toString();

        if (first.isEmpty() || last.isEmpty() || num.isEmpty())
            Toast.makeText(this, "No name or student number given.", Toast.LENGTH_LONG).show();
        else {
            first = db.getUser(1).getfName();
            last = db.getUser(1).getlName();
            num = db.getUser(1).getUserNum();
            ArrayList<UserCourse> userCourse = db.getAllUserCourses();

            Intent profileIntent = new Intent(this, ProfileActivity.class);
            profileIntent.putExtra("First", first);
            profileIntent.putExtra("Last", last);
            profileIntent.putExtra("StudentNum", num);
            profileIntent.putExtra("Course", userCourse);
            saveInfoOnClick(v);
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}

