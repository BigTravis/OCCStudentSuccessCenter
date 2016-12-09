package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kfrederick5 on 12/6/2016.
 */

public class UserCourse extends Course implements Parcelable {
    private int isSelected;

    public UserCourse(int isSelected) {
        this.isSelected = isSelected;
    }

    public UserCourse(int id, String department, String number, int isSelected) {
        super(id, department, number);
        this.isSelected = isSelected;
    }

    public UserCourse(Parcel in, int isSelected) {
        super(in);
        this.isSelected = isSelected;
    }

    public UserCourse(String s, String s1, int i) {

    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }
}
