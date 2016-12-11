package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

/**
 * this class is for individual study groups
 */
public class StudyGroup {
    private int mId;
    private String mInstructor;
    private Course mCourse;
    private DayTime mDayTime;
    private String mRoom;

    /**
     * constructor for study group
     * @param id id of study group
     * @param instructor instructor of study group's class
     * @param course study group's course
     * @param dayTime time of study group
     * @param room room study group meets in
     */

    public StudyGroup(int id, String instructor, Course course, DayTime dayTime, String room) {
        this.mId = id;
        this.mInstructor = instructor;
        this.mCourse = course;
        this.mDayTime = dayTime;
        this.mRoom = room;
    }

    /**
     * gets this study group's id
     * @return id
     */
    public int getId() {
        return mId;
    }

    /**
     * gets this study group's instructor
     * @return instructor
     */
    public String getInstructor() {
        return mInstructor;
    }

    /**
     * sets this study group's instructor
     * @param mInstructor
     */
    public void setInstructor(String mInstructor) {
        this.mInstructor = mInstructor;
    }

    /**
     * gets this study group's course
     * @return
     */
    public Course getCourse() {
        return mCourse;
    }

    /**
     * sets this study group's course
     * @param course
     */
    public void setCourse(Course course) {
        this.mCourse = course;
    }

    /**
     * gets this study group's start time
     * @return
     */
    public DayTime getDayTime() {
        return mDayTime;
    }

    /**
     * sets this study group's time
     * @param dayTime
     */
    public void setDayTime(DayTime dayTime) {
        this.mDayTime = dayTime;
    }

    /**
     * gets this study group's room
     * @return
     */
    public String getRoom() {
        return mRoom;
    }

    /**
     * sets this study group's room
     * @param room
     */
    public void setRoom(String room) {
        this.mRoom = room;
    }

    /**
     * overrides equals function
     * @param o other study group
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyGroup that = (StudyGroup) o;

        if (mId != that.mId) return false;
        if (mInstructor != null ? !mInstructor.equals(that.mInstructor) : that.mInstructor != null)
            return false;
        if (mCourse != null ? !mCourse.equals(that.mCourse) : that.mCourse != null) return false;
        if (mDayTime != null ? !mDayTime.equals(that.mDayTime) : that.mDayTime != null)
            return false;
        return mRoom != null ? mRoom.equals(that.mRoom) : that.mRoom == null;

    }

    /**
     * overrides Study Group's toString function
     * @return study group in string format
     */
    @Override
    public String toString() {
        return "StudyGroup{" +
                "mId=" + mId +
                ", mInstructor='" + mInstructor + '\'' +
                ", mCourse=" + mCourse +
                ", mDayTime=" + mDayTime +
                ", mRoom='" + mRoom + '\'' +
                '}';
    }
}
