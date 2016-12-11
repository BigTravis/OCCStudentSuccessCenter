package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ian on 12/11/2016.
 */
public class StudyGroupTest {

    private StudyGroup mGroup;

    @Before
    public void setUp() throws Exception {
        mGroup = new StudyGroup(0, "Schenck", new Course(), new DayTime(), "123");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(0, mGroup.getId());
    }

    @Test
    public void getInstructor() throws Exception {
        mGroup.setInstructor("Jones");
        assertEquals("Jones", mGroup.getInstructor());
    }

    @Test
    public void getCourse() throws Exception {
        Course newCourse = new Course(1, "MATH", "A101");
        mGroup.setCourse(newCourse);
        assertEquals(newCourse, mGroup.getCourse());
    }

    @Test
    public void getDayTime() throws Exception {
        DayTime newTime = new DayTime(1, "Saturday", 10.5f);
        mGroup.setDayTime(newTime);
        assertEquals(newTime, mGroup.getDayTime());
    }

    @Test
    public void getRoom() throws Exception {
        mGroup.setRoom("1234");
        assertEquals("1234", mGroup.getRoom());
    }

    @Test
    public void equals() throws Exception {
        assertEquals
                (new StudyGroup(0, "Schenck", new Course(), new DayTime(), "123"), mGroup);
    }


}