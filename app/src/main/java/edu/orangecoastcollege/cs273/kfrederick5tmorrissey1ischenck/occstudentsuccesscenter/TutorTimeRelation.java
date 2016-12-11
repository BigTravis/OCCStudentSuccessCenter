package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * this class represents the relationship between tutor, course, start time and end time
 */
public class TutorTimeRelation implements Parcelable{
    private Tutor mTutor;
    private Course mCourse;
    private DayTime mStartTime;
    private DayTime mEndTime;

    /**
     * this constructor creates a new default relation
     */
    public TutorTimeRelation()
    {
        mTutor = new Tutor();
        mCourse = new Course();
        mStartTime = new DayTime();
        mEndTime = new DayTime();
    }

    /**
     * this constructor creates a new relation
     * @param newTutor
     * @param newCourse
     * @param newStartTime
     * @param newEndTime
     */
    public TutorTimeRelation(Tutor newTutor, Course newCourse,
                             DayTime newStartTime, DayTime newEndTime)
    {
        mTutor = newTutor;
        mCourse = newCourse;
        mStartTime = newStartTime;
        mEndTime = newEndTime;
    }

    /**
     * this constructor is for parcelable tutorTimeRelations
     * @param in
     */
    protected TutorTimeRelation(Parcel in) {
        mTutor = in.readParcelable(Tutor.class.getClassLoader());
        mCourse = in.readParcelable(Course.class.getClassLoader());
        mStartTime = in.readParcelable(DayTime.class.getClassLoader());
        mEndTime = in.readParcelable(DayTime.class.getClassLoader());
    }


    public static final Creator<TutorTimeRelation> CREATOR = new Creator<TutorTimeRelation>() {
        @Override
        public TutorTimeRelation createFromParcel(Parcel in) {
            return new TutorTimeRelation(in);
        }

        @Override
        public TutorTimeRelation[] newArray(int size) {
            return new TutorTimeRelation[size];
        }
    };

    /**
     * returns relation's tutor
     * @return tutor
     */
    public Tutor getTutor() {
        return mTutor;
    }

    /**
     * sets  this relation's tutor
     * @param mTutor
     */
    public void setTutor(Tutor mTutor) {
        this.mTutor = mTutor;
    }

    /**
     * gets this relation's course
     * @return
     */
    public Course getCourse() {
        return mCourse;
    }

    /**
     * sets this relation's course
     * @param mCourse
     */
    public void setCourse(Course mCourse) {
        this.mCourse = mCourse;
    }

    /**
     * gets this relation's start time
     * @return
     */
    public DayTime getStartTime() {
        return mStartTime;
    }

    /**
     * sets this relation's start tune
     * @param mStartTime
     */
    public void setStartTime(DayTime mStartTime) {
        this.mStartTime = mStartTime;
    }

    /**
     * gets this relation's end time
     * @return
     */
    public DayTime getEndTime() {
        return mEndTime;
    }

    /**
     * sets this relation's end time
     * @param mEndTime
     */
    public void setEndTime(DayTime mEndTime) {
        this.mEndTime = mEndTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * overrides equals relation
     * @param obj other relation
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TutorTimeRelation)
        {
            TutorTimeRelation otherRelation = (TutorTimeRelation) obj;
            return otherRelation.getCourse().equals(this.getCourse()) &&
                    otherRelation.getTutor().equals(this.getTutor()) &&
                    otherRelation.getStartTime().equals(this.getStartTime()) &&
                    otherRelation.getEndTime().equals(this.getEndTime());
        }
        return false;
    }

    /**
     * overwrites the toString function
     * @return relation in string format
     */
    @Override
    public String toString() {
        return "TutorTimeRelation{" +
                "mTutor=" + mTutor +
                ", mCourse=" + mCourse +
                ", mStartTime=" + mStartTime +
                ", mEndTime=" + mEndTime +
                '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mTutor, i);
        parcel.writeParcelable(mCourse, i);
        parcel.writeParcelable(mStartTime, i);
        parcel.writeParcelable(mEndTime, i);
    }
}
