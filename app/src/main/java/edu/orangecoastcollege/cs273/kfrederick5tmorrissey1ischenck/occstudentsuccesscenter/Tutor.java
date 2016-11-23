package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a Tutor at Orange Coast Community College
 * Created by tmorrissey1 on 11/15/2016.
 */

public class Tutor implements Parcelable{
    private int id;
    private String firstName;
    private String lastName;

    /**
     * Default Constructor
     */
    public Tutor() {
        id = -1;
        firstName = "";
        lastName = "";
    }

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
    }

    public Tutor(String firstName, String lastName) {
        id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Tutor(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
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
     * gets the id of this tutor
     * @return id - int of id
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tutor) {
            Tutor otherTutor = (Tutor) obj;
            return this.id == otherTutor.id && this.firstName.equals(otherTutor.firstName)
                    && this.lastName.equals(otherTutor.lastName);
        }
        return false;
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
    }
}
