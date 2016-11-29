package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ischenck on 11/22/2016.
 */

public class TutorTimeRelation implements Parcelable{
    private Tutor mTutor;
    private Course mCourse;
    private DayTime mStartTime;
    private DayTime mEndTime;

    public TutorTimeRelation()
    {
        mTutor = new Tutor();
        mCourse = new Course();
        mStartTime = new DayTime();
        mEndTime = new DayTime();
    }

    public TutorTimeRelation(Tutor newTutor, Course newCourse,
                             DayTime newStartTime, DayTime newEndTime)
    {
        mTutor = newTutor;
        mCourse = newCourse;
        mStartTime = newStartTime;
        mEndTime = newEndTime;
    }

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

    public Tutor getTutor() {
        return mTutor;
    }

    public void setTutor(Tutor mTutor) {
        this.mTutor = mTutor;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course mCourse) {
        this.mCourse = mCourse;
    }

    public DayTime getStartTime() {
        return mStartTime;
    }

    public void setStartTime(DayTime mStartTime) {
        this.mStartTime = mStartTime;
    }

    public DayTime getEndTime() {
        return mEndTime;
    }

    public void setEndTime(DayTime mEndTime) {
        this.mEndTime = mEndTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
