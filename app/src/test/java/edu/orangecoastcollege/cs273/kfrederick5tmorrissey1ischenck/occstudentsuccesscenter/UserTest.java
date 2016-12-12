package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Link on 12/11/2016.
 */
public class UserTest {
    private User tUser = new User();

    @Test
    public void getId() throws Exception {
        tUser.setId(1);
        assertEquals(1, tUser.getId());

    }

    @Test
    public void getfName() throws Exception {
        tUser.setfName("Test");
        assertEquals("Test", tUser.getfName());

    }

    @Test
    public void getlName() throws Exception {
        tUser.setlName("Case");
        assertEquals("Case", tUser.getlName());

    }

    @Test
    public void getUserNum() throws Exception {
        tUser.setUserNum("C555");
        assertEquals("C555", tUser.getUserNum());

    }
}