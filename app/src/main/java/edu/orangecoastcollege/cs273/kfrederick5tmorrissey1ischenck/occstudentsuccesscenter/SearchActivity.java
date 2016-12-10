package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends NavDrawerActivity {

    private DBHelper db;
    private List<Course> mCourses;
    private List<DayTime> mDayTimes;
    private List<Tutor> mTutors;
    private List<TutorTimeRelation> mRelations;
    private Spinner subjectSpinner;
    private Spinner classSpinner;
    private Spinner daySpinner;
    private Spinner hourSpinner;
    private Spinner minuteSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_search, contentFrameLayout);

        db = new DBHelper(this);
        mCourses = db.getAllCourses();
        mDayTimes = db.getAllDayTimes();
        mTutors = db.getAllTutors();
        mRelations = db.getAllRelations();

        subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        classSpinner = (Spinner) findViewById(R.id.classSpinner);
        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        hourSpinner = (Spinner) findViewById(R.id.hourSpinner);
        minuteSpinner = (Spinner) findViewById(R.id.minuteSpinner);

        ArrayAdapter<String> subjectSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllSubjectNames());
        subjectSpinner.setAdapter(subjectSpinnerAdapter);
        subjectSpinner.setOnItemSelectedListener(subjectSpinnerListener);

        ArrayAdapter<String> classSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllClassNumbers());
        classSpinner.setAdapter(classSpinnerAdapter);
        classSpinner.setOnItemSelectedListener(classSpinnerListener);

        ArrayAdapter<String> daySpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllDays());
        daySpinner.setAdapter(daySpinnerAdapter);
        daySpinner.setOnItemSelectedListener(daySpinnerListener);

        ArrayAdapter<String> hourSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllHours());
        hourSpinner.setAdapter(hourSpinnerAdapter);
        hourSpinner.setOnItemSelectedListener(hourSpinnerListener);

        ArrayAdapter<String> minuteSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getAllMinutes());
        minuteSpinner.setAdapter(minuteSpinnerAdapter);

    }

    public AdapterView.OnItemSelectedListener subjectSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedSubject = parent.getItemAtPosition(position).toString();
            if (!selectedSubject.equals(getString(R.string.default_subject_search))) {
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
            if (!selectedClass.equals(getString(R.string.default_course_search)))
                daySpinner.setEnabled(true);

            else {
                daySpinner.setEnabled(false);
                daySpinner.setSelection(0);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public AdapterView.OnItemSelectedListener daySpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedDay = parent.getItemAtPosition(position).toString();
            if (!selectedDay.equals(getString(R.string.default_day_search)))
                hourSpinner.setEnabled(true);

            else {
                hourSpinner.setEnabled(false);
                hourSpinner.setSelection(0);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public AdapterView.OnItemSelectedListener hourSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedHour = parent.getItemAtPosition(position).toString();
            if (!selectedHour.equals((getString(R.string.default_hour_search))))
                minuteSpinner.setEnabled(true);

            else {
                minuteSpinner.setEnabled(false);
                minuteSpinner.setSelection(0);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void updateClassSpinner(String input) {
        ArrayList<String> modifiedCourseList = new ArrayList<>();
        modifiedCourseList.add(getString(R.string.default_course_search));
        for (Course course : mCourses)
            if (course.getDepartment().equals(input))
                modifiedCourseList.add(course.getNumber());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                modifiedCourseList);
        classSpinner.setAdapter(adapter);
    }

    private String[] getAllSubjectNames() {
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

    private String[] getAllDays() {
        String[] days = {getString(R.string.default_day_search), getString(R.string.monday), getString(R.string.tuesday),
                getString(R.string.wednesday), getString(R.string.thursday), getString(R.string.friday),
                getString(R.string.saturday)};
        return days;
    }

    private String[] getAllHours() {
        String[] hours = {getString(R.string.default_hour_search), "9", "10", "11", "12", "1", "2", "3", "4", "5", "6", "7"};
        return hours;
    }

    private String[] getAllMinutes() {
        String[] minutes = {getString(R.string.default_minutes_search), ":00", ":30"};
        return minutes;
    }

    public void search(View v) {
        String subject = subjectSpinner.getSelectedItem().toString();
        String classNumber = classSpinner.getSelectedItem().toString();
        if (subject.equals(getString(R.string.default_subject_search)) || classNumber.equals(getString(R.string.default_course_search)))
            Toast.makeText(this, getString(R.string.search_subject_class_error), Toast.LENGTH_SHORT).show();

        else {
            String day = daySpinner.getSelectedItem().toString();
            float time;

            // Format time from 12 hour to 24 hour format
            String hourString = hourSpinner.getSelectedItem().toString();
            if (!hourString.equals(getString(R.string.default_hour_search))) {
                time = Float.parseFloat(hourSpinner.getSelectedItem().toString());
                time += (minuteSpinner.getSelectedItem().toString().equals(":30")) ? .50f : 0.0;

                if (!(hourString.equals("9") || hourString.equals("10")
                        || hourString.equals("11") || hourString.equals("12")))
                    time += 12.0f;
            }

            else // User did not select a time and search will not filter by time
                time = 25.0f;

            ArrayList<TutorTimeRelation> tutorTimeResults = new ArrayList<>();
            for (TutorTimeRelation relation : mRelations) {
                Course course = relation.getCourse();
                DayTime startTime = relation.getStartTime();
                DayTime endTime = relation.getEndTime();

                if (course.getDepartment().equals(subject) && course.getNumber().equals(classNumber)) {
                    if (!day.equals(getString(R.string.default_day_search))) {
                        if (startTime.getDay().equals(day)) {
                            if (time < 25.0f) {
                                if (startTime.getTime() <= time && endTime.getTime() >= time)
                                    tutorTimeResults.add(relation);
                            }
                            else
                                tutorTimeResults.add(relation);
                        }

                    }
                    else // No day was selected and all qualified tutors available at the specified

                        tutorTimeResults.add(relation);
                }
            }

            Intent listIntent = new Intent(this, TutorListActivity.class);
            listIntent.putExtra("Tutor Results", tutorTimeResults);
            listIntent.putExtra("Subject", subject);
            listIntent.putExtra("Class Number", classNumber);
            listIntent.putExtra("Day", daySpinner.getSelectedItem().toString());
            listIntent.putExtra("Time", hourString + minuteSpinner.getSelectedItem().toString());
            if(tutorTimeResults.isEmpty())
                Toast.makeText(this, R.string.no_tutors_error, Toast.LENGTH_LONG).show();
            else
                startActivity(listIntent);
        }
    }

    public void clearSearch(View v) {
        subjectSpinner.setSelection(0);
        daySpinner.setSelection(0);
        hourSpinner.setSelection(0);
        minuteSpinner.setSelection(0);
    }
}
