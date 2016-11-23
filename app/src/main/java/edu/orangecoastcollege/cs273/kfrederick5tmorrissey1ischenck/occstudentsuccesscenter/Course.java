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

    public Course(int id, String department, String number) {
        this.id = id;
        this.department = department;
        this.number = number;
    }

    protected Course(Parcel in) {
        id = in.readInt();
        department = in.readString();
        number = in.readString();
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(department);
        parcel.writeString(number);
    }
}
