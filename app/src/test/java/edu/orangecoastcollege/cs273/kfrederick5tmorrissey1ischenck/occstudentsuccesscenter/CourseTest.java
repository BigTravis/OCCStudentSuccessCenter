package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by kfrederick5 on 11/22/2016.
 */
public class CourseTest {

    private Course mCourse;

    @Before
    public void setUp() throws Exception {
        mCourse = new Course();
        mCourse.setId(2);
        mCourse.setDepartment("Test Department");
        mCourse.setNumber("A120");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(2, mCourse.getId());

    }

    @Test
    public void getDepartment() throws Exception {
        assertEquals("Test Department", mCourse.getDepartment());

    }

    @Test
    public void getNumber() throws Exception {
        assertEquals("A120", mCourse.getNumber());

    }

}