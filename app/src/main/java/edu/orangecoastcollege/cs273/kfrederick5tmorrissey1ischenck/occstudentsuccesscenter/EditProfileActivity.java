package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class EditProfileActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Course> mCourses;
    private EditProfileAdapter mProfileAdapter;

    private EditText fName;
    private EditText lName;
    private Spinner subjectSpinner;
    private Spinner classSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = new DBHelper(this);
        mCourses = db.getAllCourses();

        fName = (EditText) findViewById(R.id.firstNameEditText);
        lName = (EditText) findViewById(R.id.lastNameEditText);
        subjectSpinner = (Spinner) findViewById(R.id.editSubjectSpinner);
        classSpinner = (Spinner) findViewById(R.id.editClassSpinner);

        ArrayAdapter<String> subjectSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllSubjectsNames());
        subjectSpinner.setAdapter(subjectSpinnerAdapter);
        subjectSpinner.setOnItemSelectedListener(subjectSpinnerListener);

        ArrayAdapter<String> classSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllClassNumbers());
        classSpinner.setAdapter(classSpinnerAdapter);
        classSpinner.setOnItemSelectedListener(classSpinnerListener);
    }

    public void addClass(View v)
    {
        String course = mCourses.toString();
        if(!subjectSpinner.equals(R.string.subject_drop_down_text));


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
            String selectedClass = parent.getItemAtPosition(position).toString();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void updateClassSpinner(String input) {
        ArrayList<String> modifiedCourseList = new ArrayList<>();
        modifiedCourseList.add("[Select class]");
        for (Course course : mCourses)
            if (course.getDepartment().equals(input))
                modifiedCourseList.add(course.getNumber());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                modifiedCourseList);
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
}
