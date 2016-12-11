package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Link on 12/11/2016.
 */
public class QuestionsTest {
    private Questions mQuestions = new Questions(3, "No", 0);


    @Test
    public void getId() throws Exception {
        mQuestions.setId(1);
        assertEquals(1, mQuestions.getId());

    }


    @Test
    public void getQuestion() throws Exception {
        mQuestions.setQuestion("Test");
        assertEquals("Test", mQuestions.getQuestion());
    }

    @Test
    public void getIsAnswered() throws Exception {
        mQuestions.setIsAnswered(1);
        assertEquals(1, mQuestions.getIsAnswered());

    }
}