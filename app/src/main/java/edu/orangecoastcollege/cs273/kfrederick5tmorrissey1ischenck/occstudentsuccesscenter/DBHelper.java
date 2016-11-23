package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ischenck on 11/15/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    static final String DATABASE_NAME = "OCCSSC";
    private static final int DATABASE_VERSION = 1;

    private static final String COURSES_TABLE = "Courses";
    private static final String COURSES_KEY_FIELD_ID = "id";
    private static final String FIELD_COURSE_DEPARTMENT = "course_department";
    private static final String FIELD_COURSE_NUMBER = "course_department";

    private static final String TUTORS_TABLE = "Tutors";
    private static final String TUTORS_KEY_FIELD_ID = "id";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_FIRST_NAME = "first_name";

    private static final String TIMES_TABLE = "Times";
    private static final String TIMES_KEY_FIELD_ID = "id";
    private static final String FIELD_DAY = "day";
    private static final String FIELD_HALF_HOUR = "half_hour";

    private static final String TUTOR_TIME_TABLE = "TutorAvailability";
    private static final String FIELD_TUTOR_ID = "tutor_id";
    private static final String FIELD_COURSE_ID = "course_id";
    private static final String FIELD_START_TIME_ID = "start_time_id";
    private static final String FIELD_END_TIME_ID = "end_time_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String table = "CREATE TABLE " + COURSES_TABLE + "("
                + COURSES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_COURSE_DEPARTMENT + " TEXT"
                + FIELD_COURSE_NUMBER + " TEXT" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TUTORS_TABLE + "("
                + TUTORS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_FIRST_NAME + " TEXT" + FIELD_LAST_NAME + " TEXT, " + ")";
        database.execSQL(table);


        table = "CREATE TABLE " + TIMES_TABLE + "("
                + TIMES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_DAY + " TEXT" + FIELD_HALF_HOUR + " INTEGER" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TUTOR_TIME_TABLE + "("
                + FIELD_START_TIME_ID + " INTEGER, "
                + FIELD_END_TIME_ID + " INTEGER, "
                + FIELD_TUTOR_ID + " INTEGER, "
                + FIELD_COURSE_ID + " TEXT, "
                + "FOREIGN KEY(" + FIELD_START_TIME_ID + ") REFERENCES "
                + TIMES_TABLE + "(" + TIMES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_END_TIME_ID + ") REFERENCES "
                + TIMES_TABLE + "(" + TIMES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_COURSE_ID + ") REFERENCES "
                + COURSES_TABLE + "(" + FIELD_COURSE_ID + "), "
                + "FOREIGN KEY(" + FIELD_TUTOR_ID + ") REFERENCES "
                + TUTORS_TABLE + "(" + TUTORS_KEY_FIELD_ID + ")"
                + ")";
        database.execSQL(table);

    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTORS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TIMES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTOR_TIME_TABLE);

        onCreate(database);
    }

    public void addCourse(Course course)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_COURSE_DEPARTMENT, course.getDepartment());
        values.put(FIELD_COURSE_NUMBER, course.getNumber());

        db.insert(COURSES_TABLE, null, values);

        db.close();
    }

    public void addTutor(Tutor tutor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_FIRST_NAME, tutor.getFirstName());
        values.put(FIELD_LAST_NAME, tutor.getLastName());

        db.insert(TUTORS_TABLE, null, values);

        db.close();
    }

    public void addDayTime(DayTime dayTime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_DAY, dayTime.getDay());
        values.put(FIELD_HALF_HOUR, dayTime.getTime());

        db.insert(TIMES_TABLE, null, values);

        db.close();
    }

    public void addTutorTimeRelation(int startTimeId, int endTimeId, int tutorId, int courseId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_START_TIME_ID, startTimeId);
        values.put(FIELD_END_TIME_ID, endTimeId);
        values.put(FIELD_TUTOR_ID, tutorId);
        values.put(FIELD_COURSE_ID, courseId);

        db.insert(TUTOR_TIME_TABLE, null, values);

        db.close();
    }

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
        return tutorsList;
    }

    public ArrayList<DayTime> getAllDayTimes() {
        ArrayList<DayTime> dayTimesList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                TUTORS_TABLE,
                new String[]{TIMES_KEY_FIELD_ID, FIELD_DAY, FIELD_HALF_HOUR},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                DayTime dayTime =
                        new DayTime(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2));
                dayTimesList.add(dayTime);
            }while(cursor.moveToNext());
        }
        return dayTimesList;
    }
}