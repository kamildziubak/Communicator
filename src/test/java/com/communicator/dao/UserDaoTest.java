package com.communicator.dao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoTest {
    @Test
    public void userExistsTest()
    {
        UserDao userDao = new UserDao("jdbc:mysql://localhost:3306/user", "root", "mjktm");
        assertTrue(userDao.userExists("thekamil444pl"));
    }
}