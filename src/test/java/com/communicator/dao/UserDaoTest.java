package com.communicator.dao;
import com.communicator.module.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    UserDao userDao = new UserDao("jdbc:mysql://localhost:3306/communicator", "root", "mjktm");

    @Test
    public void userExistsTest()
    {
        assertTrue(userDao.userExists("debug1"));
        assertTrue(userDao.userExists("debug2"));
    }

    @Test
    public void getUserByLoginTest()
    {
        User user = userDao.getUserByLogin("debug1");
        assertEquals("debug1", user.getLogin());
        assertEquals("", user.getPassword());
    }

//    @Test
//    public void getAllUsersTest()
//    {
//        User[] user = userDao.getAllUsers();
//
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new File("C:\\Users\\kamil\\Documents\\Communicator\\Backend\\src\\test\\resources\\userTableLength.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Integer length = Integer.parseInt(scanner.nextLine());
//
//        assertEquals(length, user.length);
//    }

    @Test
    public void registerNewUserAndDeleteUserTest()
    {
        User user = new User("testUser", "testPassword", "testUser");
        userDao.registerNewUser(user);
        assertTrue(userDao.userExists(user.getLogin()));

        userDao.deleteUser(user.getLogin());
        assertFalse(userDao.userExists(user.getLogin()));
    }
}