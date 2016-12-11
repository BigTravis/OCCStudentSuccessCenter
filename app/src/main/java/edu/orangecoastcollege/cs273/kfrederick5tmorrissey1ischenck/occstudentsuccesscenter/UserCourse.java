package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This builds an item that is an extension of courses and adds a check value to the item
 */

public class UserCourse extends Course {
    private int isSelected;


    /**
     * Constructor for a UserCourse provided by the user
     * @param id for the primary key, auto generated
     * @param department department object selected by user
     * @param number class object selected by user
     * @param isSelected a value of 1 or 0 indicating if course is selected
     */
    public UserCourse(int id, String department, String number, int isSelected) {
        super(id, department, number);
        this.isSelected = isSelected;
    }


    /**
     * Returns a value of 1 or 0 depending on if the item is selected or not.
     * @return an integer of 1 or 0
     */
    public int getIsSelected() {
        return isSelected;
    }

    /**
     * Sets the value of the item with an integer of 1 or 0
     * @param isSelected integer of value 1 or 0
     */
    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }
}
