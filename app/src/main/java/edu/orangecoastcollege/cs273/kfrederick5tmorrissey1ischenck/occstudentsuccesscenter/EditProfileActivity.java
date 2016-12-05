package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.support.v7.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

//    private DBHelper db;
//    private List<User> userCourseList;
//    private EditProfileAdapter mProfileAdapter;
//
//    private EditText fName;
//    private EditText lName;
//    private EditText studentNum;
//    private Spinner subjectSpinner;
//    private Spinner classSpinner;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile);
//
//        db = new DBHelper(this);
//        userCourseList = db.getAllUserCourses();
//
//        mProfileAdapter = new EditProfileAdapter(this, R.layout.edit_course_item, userCourseList);
//
//        fName = (EditText) findViewById(R.id.firstNameEditText);
//        lName = (EditText) findViewById(R.id.lastNameEditText);
//        studentNum = (EditText) findViewById(R.id.studentNumEditText);
//        subjectSpinner = (Spinner) findViewById(R.id.editSubjectSpinner);
//        classSpinner = (Spinner) findViewById(R.id.editClassSpinner);
//
//        ArrayAdapter<String> subjectSpinnerAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, getAllSubjectsNames());
//        subjectSpinner.setAdapter(subjectSpinnerAdapter);
//        subjectSpinner.setOnItemSelectedListener(subjectSpinnerListener);
//
//        ArrayAdapter<String> classSpinnerAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, getAllClassNumbers());
//        classSpinner.setAdapter(classSpinnerAdapter);
//        classSpinner.setOnItemSelectedListener(classSpinnerListener);
//    }
//
//    public void saveInfoOnClick(View v)
//    {
//        User userInfo;
//        if(fName.equals("") && lName.equals(""))
//            Toast.makeText(this, R.string.first_or_last_error, Toast.LENGTH_SHORT);
//        else
//        {
//            userInfo = new User();
//            userInfo.setfName(fName.toString());
//            userInfo.setlName(lName.toString());
//            userInfo.setUserNum(studentNum.toString());
//            db.addUser(userInfo);
//            Toast.makeText(this, R.string.save_successful, Toast.LENGTH_SHORT);
//        }
//    }
//
//    public void addClassOnClick(View v)
//    {
//        User newCourse;
//        if(!subjectSpinner.equals(R.string.subject_drop_down_text))
//            Toast.makeText(this, R.string.add_class_error, Toast.LENGTH_SHORT);
//        else
//        {
//            newCourse = new User();
//            newCourse.setIsSelected(0);
//            newCourse.setSubject(subjectSpinner.toString());
//            newCourse.setuClass(classSpinner.toString());
//            db.addUserCourse(newCourse);
//            subjectSpinner.setSelection(0);
//            classSpinner.setSelection(0);
//        }
//    }
//
//    public void changeCourseStatus(View v)
//    {
//        if(v instanceof CheckBox)
//        {
//            CheckBox selectedCheck = (CheckBox) v;
//            User selectedCourse = (User) selectedCheck.getTag();
//
//            selectedCourse.setIsSelected(selectedCheck.isChecked()? 1 : 0);
//
//            db.updateCourse(selectedCourse);
//        }
//    }
//
//    public void removeSelectedOnClick(View v)
//    {
//        int listSize = userCourseList.size();
//        for(int i = 0; i < listSize; i++){
//            if(userCourseList.get(i).getIsSelected() == 1)
//            {
//                userCourseList.remove(i);
//                i--;
//            }
//        }
//        db.deleteSelectedCourses();
//
//        mProfileAdapter.notifyDataSetChanged();
//
//
//    }
//
//    public AdapterView.OnItemSelectedListener subjectSpinnerListener = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            String selectedSubject = parent.getItemAtPosition(position).toString();
//            if (!selectedSubject.equals("[Select subject]")) {
//                classSpinner.setEnabled(true);
//                updateClassSpinner(selectedSubject);
//            }
//            else {
//                classSpinner.setEnabled(false);
//                classSpinner.setSelection(0);
//            }
//
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//    };
//
//    public AdapterView.OnItemSelectedListener classSpinnerListener = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            String selectedClass = parent.getItemAtPosition(position).toString();
//
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//    };
//
//    private void updateClassSpinner(String input) {
//        ArrayList<String> modifiedUserList = new ArrayList<>();
//        modifiedUserList.add("[Select class]");
//        for (User user : userCourseList)
//            if (user.getSubject().equals(input))
//                modifiedUserList.add(user.getuClass());
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
//                modifiedUserList);
//        classSpinner.setAdapter(adapter);
//    }
//
//    private String[] getAllSubjectsNames() {
//        ArrayList<String> subjectNames = new ArrayList<>();
//        subjectNames.add("[Select subject]");
//        int size = userCourseList.size();
//
//        for (int i = 0; i < size; ++i) {
//            String subject = userCourseList.get(i).getSubject();
//            if (!subjectNames.contains(subject))
//                subjectNames.add(subject);
//        }
//        return subjectNames.toArray(new String[0]);
//    }
//
//    private String[] getAllClassNumbers() {
//        String[] classNumbers = new String[userCourseList.size() + 1];
//        classNumbers[0] = "[Select class]";
//        int size = classNumbers.length;
//
//        for (int i = 1; i < size; ++i)
//            classNumbers[i] = userCourseList.get(i - 1).getuClass();
//
//        return classNumbers;
//    }
}
