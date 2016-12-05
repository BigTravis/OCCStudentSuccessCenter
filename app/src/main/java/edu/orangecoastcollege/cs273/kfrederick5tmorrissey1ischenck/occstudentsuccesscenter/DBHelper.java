package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the database that holds all information about
 * tutors, courses, times, tutor time relations, and study groups
 */
public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    static final String DATABASE_NAME = "OCCSSC";
    private static final int DATABASE_VERSION = 1;

//    private static final String CURRENT_DATABASE_TABLE = "current_database";
//    private static final String CURRENT_DATABASE_VERSION = "current_database_version";

    private static final String COURSES_TABLE = "Courses";
    private static final String COURSES_KEY_FIELD_ID = "id";
    private static final String FIELD_COURSE_DEPARTMENT = "course_department";
    private static final String FIELD_COURSE_NUMBER = "course_number";

    private static final String TUTORS_TABLE = "Tutors";
    private static final String TUTORS_KEY_FIELD_ID = "id";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_FIRST_NAME = "first_name";

    private static final String TIMES_TABLE = "Times";
    private static final String TIMES_KEY_FIELD_ID = "id";
    private static final String FIELD_DAY = "day";
    private static final String FIELD_TIME = "time";

    private static final String TUTOR_TIME_TABLE = "TutorAvailability";
    private static final String FIELD_TUTOR_ID = "tutor_id";
    private static final String FIELD_COURSE_ID = "course_id";
    private static final String FIELD_START_TIME_ID = "start_time_id";
    private static final String FIELD_END_TIME_ID = "end_time_id";


    private static final String STUDY_GROUPS_TABLE = "StudyGroups";
    private static final String STUDY_GROUPS_KEY_FIELD_ID = "id";
    private static final String FIELD_INSTRUCTOR = "instructor";
    private static final String FIELD_STUDY_COURSE_ID = "course_id";
    private static final String FIELD_TIME_ID = "time_id";
    private static final String FIELD_ROOM = "room";


    private static final String USER_INFO_TABLE = "UserInfo";
    private static final String USER_INFO_KEY_FIELD_ID = "id";
    private static final String FIELD_USER_FNAME = "first_name";
    private static final String FIELD_USER_LNAME = "last_name";
    private static final String FIELD_USER_NUMBER = "student_num";

    private static final String USER_COURSES_TABLE = "UserCourses";
    private static final String USER_COURSES_KEY_FIELD_ID = "id";
    private static final String FIELD_SUBJECT = "course_subject";
    private static final String FIELD_CLASS = "course_class";
    private static final String FIELD_IS_SELECTED = "selected";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    /**
     * creates tables for courses, tutors, times, tutor-time relationships, and study groups
     * @param database the database that the tables are being created in
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
//        String table = "CREATE TABLE " + CURRENT_DATABASE_TABLE + "("
//                + CURRENT_DATABASE_VERSION + " INTEGER" + ")";
//        database.execSQL(table);

        String table = "CREATE TABLE " + COURSES_TABLE + "("
                + COURSES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_COURSE_DEPARTMENT + " TEXT, "
                + FIELD_COURSE_NUMBER + " TEXT" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TUTORS_TABLE + "("
                + TUTORS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_FIRST_NAME + " TEXT, " + FIELD_LAST_NAME + " TEXT" + ")";
        database.execSQL(table);


        table = "CREATE TABLE " + TIMES_TABLE + "("
                + TIMES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DAY + " TEXT, " + FIELD_TIME + " REAL" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TUTOR_TIME_TABLE + "("
                + FIELD_TUTOR_ID + " INTEGER, "
                + FIELD_COURSE_ID + " TEXT, "
                + FIELD_START_TIME_ID + " INTEGER, "
                + FIELD_END_TIME_ID + " INTEGER, "
                + "FOREIGN KEY(" + FIELD_START_TIME_ID + ") REFERENCES "
                + TIMES_TABLE + "(" + TIMES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_END_TIME_ID + ") REFERENCES "
                + TIMES_TABLE + "(" + TIMES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_COURSE_ID + ") REFERENCES "
                + COURSES_TABLE + "(" + COURSES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_TUTOR_ID + ") REFERENCES "
                + TUTORS_TABLE + "(" + TUTORS_KEY_FIELD_ID + ")"
                + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + STUDY_GROUPS_TABLE + "("
                + STUDY_GROUPS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_INSTRUCTOR + " TEXT, "
                + FIELD_STUDY_COURSE_ID + " INTEGER, "
                + FIELD_TIME_ID + " INTEGER, "
                + FIELD_ROOM + " TEXT, "
                + "FOREIGN KEY(" + FIELD_STUDY_COURSE_ID + ") REFERENCES "
                + COURSES_TABLE + "(" + COURSES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_TIME_ID + ") REFERENCES "
                + TIMES_TABLE + "(" + TIMES_KEY_FIELD_ID + ")"
                + ")";
        database.execSQL(table);

        table ="CREATE TABLE " + USER_INFO_TABLE + "("
                + USER_INFO_KEY_FIELD_ID + " INTEGER, "
                + FIELD_USER_FNAME + " TEXT, "
                + FIELD_USER_LNAME + " TEXT, "
                + FIELD_USER_NUMBER + " TEXT"
                + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + USER_COURSES_TABLE + "("
                + USER_COURSES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_SUBJECT + " TEXT, "
                + FIELD_CLASS + " TEXT, "
                + FIELD_IS_SELECTED + " INTEGER" + ")";
        database.execSQL(table);
    }

    /**
     * drops table then creates all new tables
     * @param database
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        // database.execSQL("DROP TABLE IF EXISTS " + CURRENT_DATABASE_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTORS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TIMES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTOR_TIME_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + STUDY_GROUPS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + USER_INFO_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + USER_COURSES_TABLE);

        onCreate(database);
    }

//    // CURRENT DATABASE TABLE OPERATIONS: add, get
//    public void addDatabaseVersion()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(CURRENT_DATABASE_VERSION, DATABASE_VERSION);
//        db.insert(CURRENT_DATABASE_TABLE, null, values);
//
//        db.close();
//    }
//
//    public int getDatabaseVersion()
//    {
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = database.query(
//                CURRENT_DATABASE_TABLE,
//                new String[]{CURRENT_DATABASE_VERSION},
//                null, null, null, null, null, null);
//        int currentVersion = 0;
//        if (cursor.moveToFirst()) {
//            do {
//                currentVersion = cursor.getInt(0);
//            }while(cursor.moveToNext());
//        }
//        return currentVersion;
//    }

    // COURSE TABLE OPERATIONS: add, get, getAll, delete

    /**
     * adds a new course to the course table
     * @param course course object containing id, department and number
     */
    public void addCourse(Course course)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSES_KEY_FIELD_ID, course.getId());
        values.put(FIELD_COURSE_DEPARTMENT, course.getDepartment());
        values.put(FIELD_COURSE_NUMBER, course.getNumber());

        db.insert(COURSES_TABLE, null, values);

        db.close();
    }

    /**
     * gets a specific course from the course table
     * @param id the id of the course you want
     * @return course that matches the id
     */
    public Course getCourse(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                COURSES_TABLE, new String[]
                        {COURSES_KEY_FIELD_ID, FIELD_COURSE_DEPARTMENT, FIELD_COURSE_NUMBER},
                COURSES_KEY_FIELD_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Course course = new Course(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));

        db.close();
        return course;
    }

    /**
     * makes an ArrayList of all courses
     * @return arrayList of courses
     */
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> coursesList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                COURSES_TABLE,
                new String[]{COURSES_KEY_FIELD_ID, FIELD_COURSE_DEPARTMENT, FIELD_COURSE_NUMBER},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Course course =
                        new Course(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2));
                coursesList.add(course);
            }while(cursor.moveToNext());
        }
        return coursesList;
    }

    /**
     * deletes the courses table
     */
    public void deleteAllCourses() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COURSES_TABLE, null, null);
        db.close();
    }

    // TUTOR TABLE OPERATIONS: add, get, getAll, delete

    /**
     * adds a new tutor to the tutor table
     * @param tutor tutor object that contains id, first name and last name
     */
    public void addTutor(Tutor tutor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TUTORS_KEY_FIELD_ID, tutor.getId());
        values.put(FIELD_FIRST_NAME, tutor.getFirstName());
        values.put(FIELD_LAST_NAME, tutor.getLastName());

        db.insert(TUTORS_TABLE, null, values);

        db.close();
    }

    /**
     * gets a specific tutor from the tutor table
     * @param id the id of the tutor you want
     * @return tutor that matches the id
     */
    public Tutor getTutor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TUTORS_TABLE, new String[]
                        {TUTORS_KEY_FIELD_ID, FIELD_FIRST_NAME, FIELD_LAST_NAME},
                TUTORS_KEY_FIELD_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Tutor tutor = new Tutor(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));

        db.close();
        return tutor;
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USER_INFO_TABLE, new String[]{
                        USER_INFO_KEY_FIELD_ID, FIELD_USER_FNAME, FIELD_USER_LNAME,
                        FIELD_USER_NUMBER}, USER_INFO_KEY_FIELD_ID + "=?", new String[]
                        {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        db.close();
        return user;
    }

    /**
     * makes an ArrayList of all tutors
     * @return arrayList of tutors
     */
    public ArrayList<Tutor> getAllTutors() {
        ArrayList<Tutor> tutorsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                TUTORS_TABLE,
                new String[]{TUTORS_KEY_FIELD_ID, FIELD_FIRST_NAME, FIELD_LAST_NAME},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Tutor tutor =
                        new Tutor(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2));
                tutorsList.add(tutor);
            }while(cursor.moveToNext());
        }

        database.close();
        return tutorsList;
    }

    /**
     * deletes the tutors table
     */
    public void deleteAllTutors() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TUTORS_TABLE, null, null);
        db.close();
    }

    // DAY TIME TABLE OPERATIONS: add, get, getAll, delete

    /**
     * adds a new DayTime to the times table
     * @param dayTime DayTime object that contains id, day and time
     */
    public void addDayTime(DayTime dayTime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIMES_KEY_FIELD_ID, dayTime.getId());
        values.put(FIELD_DAY, dayTime.getDay());
        values.put(FIELD_TIME, dayTime.getTime());

        db.insert(TIMES_TABLE, null, values);

        db.close();
    }

    /**
     * gets a specific DayTime from the times table
     * @param id the id of the DayTime you want
     * @return DayTime that matches the id
     */
    public DayTime getDayTime(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TIMES_TABLE, new String[]
                        {TIMES_KEY_FIELD_ID, FIELD_DAY, FIELD_TIME},
                TIMES_KEY_FIELD_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        DayTime dayTime = new DayTime(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getFloat(2));

        db.close();
        return dayTime;
    }

    /**
     * makes an ArrayList of all DayTimes
     * @return arrayList of DayTimes
     */
    public ArrayList<DayTime> getAllDayTimes() {
        ArrayList<DayTime> dayTimesList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                TIMES_TABLE,
                new String[]{TIMES_KEY_FIELD_ID, FIELD_DAY, FIELD_TIME},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                DayTime dayTime =
                        new DayTime(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getFloat(2));
                dayTimesList.add(dayTime);
            }while(cursor.moveToNext());
        }

        database.close();
        return dayTimesList;
    }

    /**
     * deletes the times table
     */
    public void deleteAllDayTimes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TIMES_TABLE, null, null);
        db.close();
    }

    // RELATION TABLE OPERATIONS: add, getAll, delete


    /**
     * adds a new TutorTimeRelation to the tutor_time table
     * @param tutorId id of a tutor object
     * @param courseId id of a course object
     * @param startTimeId id of a DayTime object
     * @param endTimeId id of a EndTime object
     */
    public void addTutorTimeRelation(int tutorId, int courseId, int startTimeId, int endTimeId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_TUTOR_ID, tutorId);
        values.put(FIELD_COURSE_ID, courseId);
        values.put(FIELD_START_TIME_ID, startTimeId);
        values.put(FIELD_END_TIME_ID, endTimeId);

        db.insert(TUTOR_TIME_TABLE, null, values);

        db.close();
    }

    /**
     * creates an ArrayList of all tutor time relations
     * @return ArrayList of tutor time relations
     */
    public ArrayList<TutorTimeRelation> getAllRelations() {
        ArrayList<TutorTimeRelation> relationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TUTOR_TIME_TABLE, new String[]
                        {FIELD_TUTOR_ID, FIELD_COURSE_ID, FIELD_START_TIME_ID, FIELD_END_TIME_ID},
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Tutor tutor = getTutor(cursor.getInt(0));
                Course course = getCourse(cursor.getInt(1));
                DayTime startTime = getDayTime(cursor.getInt(2));
                DayTime endTime = getDayTime(cursor.getInt(3));

                TutorTimeRelation newRelation =
                        new TutorTimeRelation(tutor, course, startTime, endTime);
                relationsList.add(newRelation);

            }while (cursor.moveToNext());
        }

        db.close();
        return relationsList;
    }

    /**
     * deletes the tutor_time table
     */
    public void deleteAllRelations() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TUTOR_TIME_TABLE, null, null);
        db.close();
    }

    // STUDY GROUP TABLE OPERATIONS: add, getAll, delete

    /**
     * adds a new study group to the study group table
     * @param id id of study group
     * @param instructor instructor for study group's class
     * @param courseId id of the course
     * @param dayTimeId id of the time
     * @param room room study group is held in
     */
    public void addStudyGroup(int id, String instructor, int courseId, int dayTimeId, String room)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDY_GROUPS_KEY_FIELD_ID, id);
        values.put(FIELD_INSTRUCTOR, instructor);
        values.put(FIELD_STUDY_COURSE_ID, courseId);
        values.put(FIELD_TIME_ID, dayTimeId);
        values.put(FIELD_ROOM, room);

        db.insert(STUDY_GROUPS_TABLE, null, values);

        db.close();
    }

    /**
     * creates an ArrayList of Study Groups
     * @return an ArrayList of study groups
     */
    public ArrayList<StudyGroup> getAllStudyGroups() {
        ArrayList<StudyGroup> studyGroupsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                STUDY_GROUPS_TABLE, new String[]
                        {STUDY_GROUPS_KEY_FIELD_ID, FIELD_INSTRUCTOR,
                                FIELD_STUDY_COURSE_ID, FIELD_TIME_ID, FIELD_ROOM},
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String instructor = cursor.getString(1);
                Course course = getCourse(cursor.getInt(2));
                DayTime dayTime = getDayTime(cursor.getInt(3));
                String room = cursor.getString(4);

                StudyGroup newGroup =
                        new StudyGroup(id, instructor, course, dayTime, room);
                studyGroupsList.add(newGroup);

            }while (cursor.moveToNext());
        }

        db.close();
        return studyGroupsList;
    }

    /**
     * deletes the study group table
     */
    public void deleteAllStudyGroups() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDY_GROUPS_TABLE, null, null);
        db.close();
    }

    // CSV import functions

    /**
     * imports courses information from a csv file
     * @param csvFileName name of csv file
     * @return true if import works, false if it doesnt
     */
    public boolean importCoursesFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 3) {
                    Log.d("OCC SSC", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String department = fields[1].trim();
                String number = fields[2].trim();
                addCourse(new Course(id, department, number));
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * imports tutor information from a csv file
     * @param csvFileName name of csv file
     * @return true if import works, false if it doesnt
     */
    public boolean importTutorsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 3) {
                    Log.d("OCC SSC", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                addTutor(new Tutor(id, firstName, lastName));
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * imports DayTime information from a csv file
     * @param csvFileName name of csv file
     * @return true if import works, false if it doesnt
     */
    public boolean importDayTimesFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 3) {
                    Log.d("OCC SSC", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String day = fields[1].trim();
                float time = Float.parseFloat(fields[2].trim());
                addDayTime(new DayTime(id, day, time));
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * imports relation information from a csv file
     * @param csvFileName name of csv file
     * @return true if import works, false if it doesnt
     */
    public boolean importRelationsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    Log.d("OCC SSC", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int  tutorId = Integer.parseInt(fields[0].trim());
                int courseId = Integer.parseInt(fields[1].trim());
                int startTimeId = Integer.parseInt(fields[2].trim());
                int endTimeId = Integer.parseInt(fields[3].trim());
                addTutorTimeRelation(tutorId, courseId, startTimeId, endTimeId);
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean importStudyGroupsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 5) {
                    Log.d("OCC SSC", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int  id = Integer.parseInt(fields[0].trim());
                String instructor = fields[1].trim();
                int courseId = Integer.parseInt(fields[2].trim());
                int dayTimeId = Integer.parseInt(fields[3].trim());
                String room = fields[4].trim();
                addStudyGroup(id, instructor, courseId, dayTimeId, room);
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void addUser(User newUser)
    {
        SQLiteDatabase userDB = this.getWritableDatabase();

        ContentValues val = new ContentValues();

        val.put(FIELD_USER_FNAME, newUser.getfName());
        val.put(FIELD_USER_LNAME, newUser.getlName());
        val.put(FIELD_USER_NUMBER, newUser.getUserNum());

        userDB.insert(USER_INFO_TABLE, null, val);

        userDB.close();
    }

    public void addUserCourse(User newCourse)
    {
        SQLiteDatabase courseDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIELD_SUBJECT, newCourse.getSubject());
        values.put(FIELD_CLASS, newCourse.getuClass());
        values.put(FIELD_IS_SELECTED, newCourse.getIsSelected());

        courseDB.insert(USER_COURSES_TABLE, null, values);

        courseDB.close();
    }

    public ArrayList<User> getAllUserCourses()
    {
        SQLiteDatabase courseDB = this.getReadableDatabase();

        ArrayList<User> allUserCourses = new ArrayList<>();

        Cursor results = courseDB.query(USER_COURSES_TABLE, null, null, null, null, null, null);

        if(results.moveToFirst())
        {
            do {
                int id = results.getInt(0);
                String uSubject = results.getString(1);
                String uClass = results.getString(2);
                int isSelected = results.getInt(3);
                allUserCourses.add(new User(id, uSubject, uClass, isSelected));
            }while (results.moveToNext());
        }

        courseDB.close();
        results.close();
        return allUserCourses;
    }

    public void updateCourse(User existingCourse)
    {
        SQLiteDatabase courseDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIELD_SUBJECT, existingCourse.getSubject());
        values.put(FIELD_CLASS, existingCourse.getuClass());
        values.put(FIELD_IS_SELECTED, existingCourse.getIsSelected());

        courseDB.update(USER_INFO_TABLE, values, USER_COURSES_KEY_FIELD_ID + "=?",
                new String[] {String.valueOf(existingCourse.getId())});

        courseDB.close();
    }

    public void deleteSelectedCourses()
    {
        SQLiteDatabase courseDB = this.getReadableDatabase();
        courseDB.delete(USER_INFO_TABLE, FIELD_IS_SELECTED + "=1", null);
        courseDB.close();
    }
}