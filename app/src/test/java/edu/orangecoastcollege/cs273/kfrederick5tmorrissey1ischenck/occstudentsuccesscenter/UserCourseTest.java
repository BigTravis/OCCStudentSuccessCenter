package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Link on 12/11/2016.
 */
public class UserCourseTest {
    @Test
    public void getIsSelected() throws Exception {
        UserCourse tCourse = new UserCourse(1, "" , "", 1);
        tCourse.setIsSelected(0);
        assertEquals(0, tCourse.getIsSelected());

    }

}