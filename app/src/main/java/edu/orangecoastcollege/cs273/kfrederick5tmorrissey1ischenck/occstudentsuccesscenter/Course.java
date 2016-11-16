package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

/**
 * Represents a Course offered at Orange Coast Community
 * Created by tmorrissey1 on 11/15/2016.
 */

public class Course {
    private int id;
    private String department;
    private String number;

    public Course(int id, String department, String number) {
        this.id = id;
        this.department = department;
        this.number = number;
    }

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
}
