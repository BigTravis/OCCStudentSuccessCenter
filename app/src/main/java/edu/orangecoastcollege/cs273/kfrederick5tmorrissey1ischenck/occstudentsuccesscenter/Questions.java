package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

/**
 * A model for user questions that will be added to the ToAskActivity
 */

public class Questions {

    private int mId;
    private String mQuestion;
    private int mIsAnswered;

    /**
     * Default constructor
     * @param question question provided by the user
     * @param answered an int value to indicate whether the question has been answered
     */
    public Questions(String question, int answered) {
        this(-1, question, answered);
    }

    /**
     * Overloaded constructor
     * @param id The primary key id for the database
     * @param question question provided by the user
     * @param isAnswered an int value to indicate whether the question has been answered
     */
    public Questions(int id, String question, int isAnswered) {

        mId = id;
        mQuestion = question;
        mIsAnswered = isAnswered;
    }

    /**
     * Overridden toString to print out values
     * @return The string value of the information
     */
    @Override
    public String toString() {
        return "Questions{" +
                "mId=" + mId +
                ", mQuestion='" + mQuestion + '\'' +
                ", mIsAnswered=" + mIsAnswered +
                '}';
    }

    /**
     * Used to get the id value
     * @return an int for the ID
     */
    public int getId() {
        return mId;
    }

    /**
     * Used to set the ID value
     * @param id int for the ID
     */
    public void setId(int id) {
        mId = id;
    }

    /**
     * Used to call for the string value of the question
     * @return a String value of the question
     */
    public String getQuestion() {
        return mQuestion;
    }

    /**
     * Sets the question value to the variable
     * @param question user generated question
     */
    public void setQuestion(String question) {
        mQuestion = question;
    }

    /**
     * Returns the int value for if an item is selected or not
     * @return an int value of 1 or 0
     */
    public int getIsAnswered() {
        return mIsAnswered;
    }

    /**
     * Sets the value if an item has been selected or not
     * @param isAnswered an int value of 1 or 0
     */
    public void setIsAnswered(int isAnswered) {
        mIsAnswered = isAnswered;
    }
}
