package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ian on 12/11/2016.
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
        Tutor newTutor = new Tutor(1, "Ian", "Schenck");
        mRelation.setTutor(newTutor);
        assertEquals(newTutor, mRelation.getTutor());
    }

    @Test
    public void getCourse() throws Exception {
        Course newCourse = new Course(1, "MATH", "101");
        mRelation.setCourse(newCourse);
        assertEquals(newCourse, mRelation.getCourse());
    }

    @Test
    public void getStartTime() throws Exception {
        DayTime newTime = new DayTime(1, "Saturday", 1.5f);
        mRelation.setStartTime(newTime);
        assertEquals(newTime, mRelation.getStartTime());
    }

    @Test
    public void getEndTime() throws Exception {
        DayTime newTime = new DayTime(1, "Friday", 3.0f);
        mRelation.setEndTime(newTime);
        assertEquals(newTime, mRelation.getEndTime());
    }

    @Test
    public void equals() throws Exception {
        mRelation = new TutorTimeRelation();
        assertEquals(new TutorTimeRelation(), mRelation);
    }
}