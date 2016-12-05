package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Link on 12/4/2016.
 */

public class User implements Parcelable {

    private int id;
    private String fName;
    private String lName;
    private String userNum;
    private String subject;
    private String uClass;
    private int isSelected;

    public User() {
        User newUser = new User(-1, "", "", "", "", "", 0);
    }

    public User(int id, String fName, String lName, String userNum,
                String subject, String uClass, int isSelected) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.userNum = userNum;
        this.subject = subject;
        this.uClass = uClass;
        this.isSelected = isSelected;
    }

    protected User(Parcel in) {
        id = in.readInt();
        fName = in.readString();
        lName = in.readString();
        userNum = in.readString();
        subject = in.readString();
        uClass = in.readString();
        isSelected = in.readInt();
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

    public User (int id, String fName, String lName, String userNum) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.userNum = userNum;
    }

    public User(int id, String subject, String uClass, int isSelected) {
        this.id = id;
        this.subject = subject;
        this.uClass = uClass;
        this.isSelected = isSelected;
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
        dest.writeString(subject);
        dest.writeString(uClass);
        dest.writeInt(isSelected);
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getuClass() {
        return uClass;
    }

    public void setuClass(String uClass) {
        this.uClass = uClass;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
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
            return this.id == otherUser.id && this.subject.equals(otherUser.subject)
                    && this.uClass.equals(otherUser.uClass);
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", subject='" + subject + '\'' +
                ", uClass='" + uClass + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
