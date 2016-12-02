package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

/**
 * Created by ischenck on 11/29/2016.
 */

public class StudyGroup {
    private int mId;
    private String mInstructor;
    private Course mCourse;
    private DayTime mDayTime;
    private String mRoom;

    public StudyGroup(int id, String instructor, Course course, DayTime dayTime, String room) {
        this.mId = id;
        this.mInstructor = instructor;
        this.mCourse = course;
        this.mDayTime = dayTime;
        this.mRoom = room;
    }

    public int getId() {
        return mId;
    }

    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String mInstructor) {
        this.mInstructor = mInstructor;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        this.mCourse = course;
    }

    public DayTime getDayTime() {
        return mDayTime;
    }

    public void setDayTime(DayTime dayTime) {
        this.mDayTime = dayTime;
    }

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        this.mRoom = room;
    }

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
