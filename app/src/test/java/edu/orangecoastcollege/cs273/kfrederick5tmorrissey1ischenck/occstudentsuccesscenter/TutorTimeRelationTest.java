package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by ischenck on 11/22/2016.
 */
public class TutorTimeRelationTest {
    private TutorTimeRelation mRelation;

    @Before
    public void setUp() throws Exception {
        mRelation = new TutorTimeRelation();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getTutor() throws Exception {
        assertEquals(new Tutor(), mRelation.getTutor());
    }

    @Test
    public void setTutor() throws Exception {

    }

    @Test
    public void getCourse() throws Exception {
        assertEquals(new Course(), mRelation.getCourse());
    }

    @Test
    public void setCourse() throws Exception {

    }

    @Test
    public void getStartTime() throws Exception {
        assertEquals(new DayTime(), mRelation.getStartTime());
    }

    @Test
    public void setStartTime() throws Exception {

    }

    @Test
    public void getEndTime() throws Exception {
        assertEquals(new DayTime(), mRelation.getEndTime());
    }

    @Test
    public void setEndTime() throws Exception {

    }

    @Test
    public void equals() throws Exception {
        assertEquals(new TutorTimeRelation(), mRelation);
    }

}