package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by kfrederick5 on 11/22/2016.
 */
public class DayTimeTest {

    private DayTime dayTime;
    @Before
    public void setUp() throws Exception {
        dayTime = new DayTime();
        dayTime.setDay("Test Day");
        dayTime.setId(2);
        dayTime.setTime("3:00 PM");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(2, dayTime.getId());

    }

    @Test
    public void getDay() throws Exception {
        assertEquals("Test Day", dayTime.getDay());
    }

    @Test
    public void getTime() throws Exception {
        assertEquals("3:00 PM", dayTime.getTime());
    }

}