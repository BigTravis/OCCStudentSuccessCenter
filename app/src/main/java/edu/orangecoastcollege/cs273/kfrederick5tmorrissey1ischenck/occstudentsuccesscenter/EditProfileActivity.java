package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Course> mCourses;
    private List<User> userCourseList;
    private ListView editCourseListView;
    private ProfileListAdapter mProfileAdapter;

    private EditText fName;
    private EditText lName;
    private EditText studentNum;
    private Spinner subjectSpinner;
    private Spinner classSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = new DBHelper(this);

        mCourses = db.getAllCourses();
        userCourseList = db.getAllUserCourses();

        mProfileAdapter = new ProfileListAdapter(this, R.layout.edit_course_item, userCourseList);

        fName = (EditText) findViewById(R.id.firstNameEditText);
        lName = (EditText) findViewById(R.id.lastNameEditText);
        studentNum = (EditText) findViewById(R.id.studentNumEditText);
        subjectSpinner = (Spinner) findViewById(R.id.editSubjectSpinner);
        classSpinner = (Spinner) findViewById(R.id.editClassSpinner);

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

    public void saveInfoOnClick(View v)
    {
        String first, last, num;
        first = fName.getText().toString();
        last = lName.getText().toString();
        num = studentNum.getText().toString();

        if(first.isEmpty() || last.isEmpty() || num.isEmpty())
            Toast.makeText(this, R.string.first_or_last_error, Toast.LENGTH_SHORT).show();
        else
        {
            User newUser = new User(0, first, last, num);

            db.addUser(newUser);

            Toast.makeText(this, R.string.save_successful, Toast.LENGTH_SHORT).show();
        }
    }

    public void addClassOnClick(View v)
    {
        Spinner subject = subjectSpinner;
        Spinner course = classSpinner;

        if(subject.equals(R.string.subject_drop_down_text)
                || course.equals(R.string.class_drop_down_text))
            Toast.makeText(this, R.string.add_class_error,
                    Toast.LENGTH_SHORT).show();
        else
        {
            User newCourse = new User(subject.getSelectedItem().toString(), course.getSelectedItem().toString(), 0);

            mProfileAdapter.add(newCourse);

            db.addUserCourse(newCourse);

            subjectSpinner.setSelection(0);
            classSpinner.setSelection(0);
        }
    }

    public void changeCourseStatus(View v)
    {
        if(v instanceof CheckBox)
        {
            CheckBox selectedCheck = (CheckBox) v;
            User selectedCourse = (User) selectedCheck.getTag();

            selectedCourse.setIsSelected(selectedCheck.isChecked()? 1 : 0);

            db.updateCourse(selectedCourse);
        }
    }

    public void removeSelectedOnClick(View v)
    {
        for(int i = 0; i < userCourseList.size(); i++){
            if(userCourseList.get(i).getIsSelected() == 1)
            {
                userCourseList.remove(i);
                i--;
            }
        }
        db.deleteSelectedCourses();

        mProfileAdapter.notifyDataSetChanged();


    }

    public AdapterView.OnItemSelectedListener subjectSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedSubject = parent.getItemAtPosition(position).toString();
            if (!selectedSubject.equals("[Select subject]")) {
                classSpinner.setEnabled(true);
                updateClassSpinner(selectedSubject);
            }
            else {
                classSpinner.setEnabled(false);
                classSpinner.setSelection(0);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public AdapterView.OnItemSelectedListener classSpinnerListener = new AdapterView.OnItemSelectedListener() {
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
        modifiedUserList.add("[Select class]");
        for (Course course : mCourses)
            if (course.getDepartment().equals(input))
                modifiedUserList.add(course.getNumber());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                modifiedUserList);
        classSpinner.setAdapter(adapter);
    }

    private String[] getAllSubjectsNames() {
        ArrayList<String> subjectNames = new ArrayList<>();
        subjectNames.add("[Select subject]");
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
        classNumbers[0] = "[Select class]";
        int size = classNumbers.length;

        for (int i = 1; i < size; ++i)
            classNumbers[i] = mCourses.get(i - 1).getNumber();

        return classNumbers;
    }

    public void returnToProfileOnClick(View v)
    {
        String first = db.getUser(0).getfName();
        String last = db.getUser(0).getlName();
        String num = db.getUser(0).getUserNum();
        Array[] userCourse = (Array[]) db.getAllUserCourses().toArray();

        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("First", first);
        profileIntent.putExtra("Last", last);
        profileIntent.putExtra("StudentNum", num);
        profileIntent.putExtra("Course", userCourse);
    }
}
