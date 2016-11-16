package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ischenck on 11/15/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    static final String DATABASE_NAME = "OCCSSC";
    private static final int DATABASE_VERSION = 1;

    private static final String COURSES_TABLE = "Courses";
    private static final String COURSES_KEY_FIELD_ID = "id";
    private static final String FIELD_COURSE_NAME = "course_name";

    private static final String TUTORS_TABLE = "Tutors";
    private static final String TUTORS_KEY_FIELD_ID = "id";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_FIRST_NAME = "first_name";

    private static final String DAYS_TABLE = "Days";
    private static final String DAYS_KEY_FIELD_ID = "id";
    private static final String FIELD_DAY_NAME = "day_name";

    private static final String TIMES_TABLE = "Times";
    private static final String TIMES_KEY_FIELD_ID = "id";
    private static final String FIELD_TIME = "time";

    private static final String TUTOR_COURSE_TABLE = "TutorCourseRelationship";
    private static final String FIELD_COURSE_ID = "course_id";
    private static final String FIELD_TUTOR_ID_COURSE_R = "tutor_id";

    private static final String TUTOR_TIME_TABLE = "TutorTimeRelationship";
    private static final String FIELD_TUTOR_ID_TIME_R = "tutor_id";
    private static final String FIELD_DAY_ID = "day_id";
    private static final String FIELD_TIME_ID = "time_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String table = "CREATE TABLE " + COURSES_TABLE + "("
                + COURSES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_COURSE_NAME + " TEXT" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TUTORS_TABLE + "("
                + TUTORS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_LAST_NAME + " TEXT, " + FIELD_FIRST_NAME + " TEXT" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + DAYS_TABLE + "("
                + DAYS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_DAY_NAME + " TEXT" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TIMES_TABLE + "("
                + TIMES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_TIME + " TEXT" + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TUTOR_COURSE_TABLE + "("
                + FIELD_COURSE_ID + " INTEGER, "
                + FIELD_TUTOR_ID_COURSE_R + " INTEGER, "
                + "FOREIGN KEY(" + FIELD_COURSE_ID + ") REFERENCES "
                + COURSES_TABLE + "(" + COURSES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_TUTOR_ID_COURSE_R + ") REFERENCES "
                + TUTORS_TABLE + "(" + TUTORS_KEY_FIELD_ID + ")"
                + ")";
        database.execSQL(table);

        table = "CREATE TABLE " + TUTOR_TIME_TABLE + "("
                + FIELD_DAY_ID + " INTEGER, "
                + FIELD_TIME_ID + " INTEGER, "
                + FIELD_TUTOR_ID_TIME_R + " INTEGER, "
                + "FOREIGN KEY(" + FIELD_DAY_ID + ") REFERENCES "
                + DAYS_TABLE + "(" + DAYS_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_TIME_ID + ") REFERENCES "
                + TIMES_TABLE + "(" + TIMES_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_TUTOR_ID_TIME_R + ") REFERENCES "
                + TUTORS_TABLE + "(" + TUTORS_KEY_FIELD_ID + ")"
                + ")";
        database.execSQL(table);

    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTORS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + DAYS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TIMES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTORS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTOR_COURSE_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + TUTOR_TIME_TABLE);

        onCreate(database);
    }
}