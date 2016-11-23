package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Tutor at Orange Coast Community College
 * Created by tmorrissey1 on 11/15/2016.
 */

public class Tutor implements Parcelable{
    private int id;
    private String firstName;
    private String lastName;
    private List<Course> courseList;
    private List<DayTime> dayTimeList;

    /**
     * Constructor.
     * @param id int The unique id of the Tutor.
     * @param firstName String The first name of the Tutor.
     * @param lastName String The last name of the Tutor.
     */
    public Tutor(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        courseList = new ArrayList();
        dayTimeList = new ArrayList();
    }

    /**
     * Constructor.
     * @param id int The unique id of the Tutor.
     * @param firstName String The first name of the Tutor
     * @param lastName String The last name of the Tutor.
     * @param courseList List<Course> List of Courses that the Tutor tutors.
     * @param dayTimeList List<DayTime> List of DayTimes that the Tutor is available.
     */
    public Tutor(int id, String firstName, String lastName, List<Course> courseList,
                 List<DayTime> dayTimeList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseList = courseList;
        this.dayTimeList = dayTimeList;
    }

    protected Tutor(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        courseList = in.createTypedArrayList(Course.CREATOR);
        dayTimeList = in.createTypedArrayList(DayTime.CREATOR);
    }

    public static final Creator<Tutor> CREATOR = new Creator<Tutor>() {
        @Override
        public Tutor createFromParcel(Parcel in) {
            return new Tutor(in);
        }

        @Override
        public Tutor[] newArray(int size) {
            return new Tutor[size];
        }
    };

    /**
     * Gets first name of this Tutor.
     * @return String first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of this Tutor.
     * @param firstName String the new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of this Tutor
     * @return String last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of this Tutor
     * @param lastName String the new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the list of courses that this Tutor can tutor.
     * @return List<Course> list of courses.
     */
    public List<Course> getCourseList() {
        return courseList;
    }

    /**
     * Sets the course list of this Tutor.
     * @param courseList List<Course> list of Courses.
     */
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    /**
     * Gets the list of day/times that this Tutor can tutor.
     * @return List<DayTime> list of day/times.
     */
    public List<DayTime> getDayTimeList() {
        return dayTimeList;
    }

    /**
     * Sets the list of day/times that this Tutor can tutor.
     * @param dayTimeList list of day/times.
     */
    public void setDayTimeList(List<DayTime> dayTimeList) {
        this.dayTimeList = dayTimeList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeTypedList(courseList);
        parcel.writeTypedList(dayTimeList);
    }
}
