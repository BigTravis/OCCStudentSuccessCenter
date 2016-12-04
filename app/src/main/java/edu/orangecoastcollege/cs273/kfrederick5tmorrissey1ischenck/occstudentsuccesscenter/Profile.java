package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import java.lang.reflect.Array;

/**
 * Created by Link on 12/4/2016.
 */

public class Profile {

    private String mFirstName;
    private String mLastName;
    private String mStudentNum;
    private String[] mSubjects;
    private String[] mClasses;
    private int mNumSubjects;
    private int mNumClasses;



    public Profile()
    {
        mFirstName = "";
        mLastName = "";
        mStudentNum = "";
        mSubjects[0] = "";
        mClasses[0] = "";
        mNumSubjects = 0;
        mNumClasses = 0;
    }

    public Profile(String fName, String lName, String studentNum, String[] subjects,
                   int numSubjects, String[] classes, int numClasses)
    {
        mFirstName = fName;
        mLastName = lName;
        mStudentNum = studentNum;
        mSubjects = subjects;
        mNumSubjects = numSubjects;
        mClasses = classes;
        mNumClasses = numClasses;
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

    public String[] getSubjects() {
        return mSubjects;
    }

    public void setSubjects(String[] subjects) {
        mSubjects = subjects;
    }

    public String[] getClasses() {
        return mClasses;
    }

    public void setClasses(String[] classes) {
        mClasses = classes;
    }

    public int getNumClasses() {
        return mNumClasses;
    }

    public void setNumClasses(int numClasses) {
        mNumClasses = numClasses;
    }

    public int getNumSubjects() {
        return mNumSubjects;
    }

    public void setNumSubjects(int numSubjects) {
        mNumSubjects = numSubjects;
    }

    public void addClass() {

    }
}
