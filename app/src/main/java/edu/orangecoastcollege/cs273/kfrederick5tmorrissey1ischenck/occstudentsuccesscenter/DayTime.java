package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

/**
 * Represents a paired day and time. The day is in written format (Ex. Tuesday)
 * and the time is in 24 hour format with a colon separating hours and minutes (Ex. 13:45)
 * Created by Travis on 11/17/2016.
 */

public class DayTime {
    private int id;
    private String day;
    private String time;

    /**
     * Constructor.
     * @param day String the day in written format.
     * @param time String the time in 24 hour format.
     */
    public DayTime(String day, String time) {
        this.day = day;
        this.time = time;
    }

    /**
     * Gets id.
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     * @param id int id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the day.
     * @return String the day.
     */
    public String getDay() {
        return day;
    }

    /**
     * Sets the new day.
     * @param day String the new day.
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Gets the time.
     * @return String the time in 24 hour format.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the new time.
     * @param time String the new time.
     */
    public void setTime(String time) {
        this.time = time;
    }
}
