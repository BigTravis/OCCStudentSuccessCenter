package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Spinner;

/**
 * Holds the users name and student number
 */

public class User implements Parcelable {

    private int id;
    private String fName;
    private String lName;
    private String userNum;

    /**
     * Default constructor passing generic values
     */
    public User() {
        User newUser = new User(-1, "", "", "");
    }

    /**
     * Overloaded constructor that creates a user object based on user input
     * @param id primary key id auto given
     * @param fName the first name of the user given
     * @param lName the last name of the user given
     * @param userNum the users student number
     */
    public User(int id, String fName, String lName, String userNum) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.userNum = userNum;
    }


    protected User(Parcel in) {
        id = in.readInt();
        fName = in.readString();
        lName = in.readString();
        userNum = in.readString();
    }

    /**
     * Generates a parceled user
     */
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flattens user to be parced
     * @param dest parcel destination
     * @param flags additional options
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fName);
        dest.writeString(lName);
        dest.writeString(userNum);
    }

    /**
     * called to retrieve the ID of the user
     * @return int for the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the users integer id
     * @param id int value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the users first name
     * @return a String value for the first name
     */
    public String getfName() {
        return fName;
    }

    /**
     * Sets a String value for the first name
     * @param fName a string value of the first name
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * Gets the last name of the user object
     * @return String value of the last name
     */
    public String getlName() {
        return lName;
    }

    /**
     * Sets the last name of the user object
     * @param lName a String value for the last name
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * Returns the student number
     * @return a String value of the user objects student number
     */
    public String getUserNum() {
        return userNum;
    }

    /**
     * Sets the user objects Student number
     * @param userNum a String value for the Student number
     */
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User otherUser = (User) o;
            return this.id == otherUser.id && this.fName.equals(otherUser.fName)
                    && this.lName.equals(otherUser.lName) && this.equals(otherUser.userNum);
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNum='" + userNum + '\'' +
                ", id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }
}
