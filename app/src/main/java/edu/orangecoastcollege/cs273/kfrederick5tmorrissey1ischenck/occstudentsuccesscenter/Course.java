package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a Course offered at Orange Coast Community
 * Created by tmorrissey1 on 11/15/2016.
 */

public class Course implements Parcelable {
    private int id;
    private String department;
    private String number;

    /**
     * Default Constructor
     */
    public Course() {
        id = -1;
        department = "";
        number = "";
    }

    /**
     * Creates a overloaded constructor with all items created
     * @param id Primary key for the course
     * @param department Department of the course
     * @param number Course number
     */
    public Course(int id, String department, String number) {
        this.id = id;
        this.department = department;
        this.number = number;
    }

    /**
     *
     * @param in
     */
    protected Course(Parcel in) {
        id = in.readInt();
        department = in.readString();
        number = in.readString();
    }

    /**
     *
     */
    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    /**
     * Calls for the course ID
     * @return an int for the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set the course ID Primary key
     * @param id an int value for the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Calls for the department name of the course
     * @return a String value of the Course department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets a string value for the Course department
     * @param department passes a String for the Course department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets the Course number
     * @return A string value for the Course numbers
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets a String value for the Course number
     * @param number passes a String for to the Course number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course otherCourse = (Course) obj;
            return this.id == otherCourse.id && this.number.equals(otherCourse.number) &&
                    this.department.equals(otherCourse.department);
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes all Course values to a Parcelable value for passing
     * between activities
     * @param parcel Where the course details will be written
     * @param i Additional values if needed
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(department);
        parcel.writeString(number);
    }
}
