package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

/**
 * Created by Link on 12/5/2016.
 */

public class Questions {

    private int mId;
    private String mQuestion;
    private int mIsAnswered;

    public Questions(String question, int answered) {
        this(-1, question, answered);
    }

    public Questions(int id, String question, int isAnswered) {

        mId = id;
        mQuestion = question;
        mIsAnswered = isAnswered;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "mId=" + mId +
                ", mQuestion='" + mQuestion + '\'' +
                ", mIsAnswered=" + mIsAnswered +
                '}';
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public int getIsAnswered() {
        return mIsAnswered;
    }

    public void setIsAnswered(int isAnswered) {
        mIsAnswered = isAnswered;
    }
}
