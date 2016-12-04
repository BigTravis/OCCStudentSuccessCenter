package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;

/**
 * Created by Link on 12/4/2016.
 */

public class Profile {

    private String mFirstName;
    private String mLastName;
    private String mStudentNum;
    private String[] mSubClasses;
    private int mNumSubClasses;



    public Profile()
    {
        mFirstName = "";
        mLastName = "";
        mStudentNum = "";
        mSubClasses[0] = "";
        mNumSubClasses = 0;
    }

    public Profile(String fName, String lName, String studentNum, String[] subClasses,
                   int numSubClasses)
    {
        mFirstName = fName;
        mLastName = lName;
        mStudentNum = studentNum;
        mSubClasses = subClasses;
        mNumSubClasses = numSubClasses;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getStudentNum() {
        return mStudentNum;
    }

    public void setStudentNum(String studentNum) {
        mStudentNum = studentNum;
    }

    public String[] getSubClasses() {
        return mSubClasses;
    }

    public void setSubClasses(String[] subClasses) {
        mSubClasses = subClasses;
    }

    public int getNumSubClasses() {
        return mNumSubClasses;
    }

    public void setNumSubClasses(int numSubClasses) {
        mNumSubClasses = numSubClasses;
    }

    public void addSubClass()
    {
        mNumSubClasses++;
    }

    public void removeSubClass()
    {
        mNumSubClasses--;
    }
}
