package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Spinner;

/**
 * Created by Link on 12/4/2016.
 */

public class User implements Parcelable {

    private int id;
    private String fName;
    private String lName;
    private String userNum;

    public User() {
        User newUser = new User(-1, "", "", "");
    }

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

    public User(String first, String last, String num) {
        this.fName = first;
        this.lName = last;
        this.userNum = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fName);
        dest.writeString(lName);
        dest.writeString(userNum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUserNum() {
        return userNum;
    }

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
