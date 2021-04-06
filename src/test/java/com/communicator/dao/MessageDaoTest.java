package com.communicator.dao;

import com.communicator.module.Message;
import com.communicator.module.User;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@Repository("message")
public class MessageDaoTest {
    Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/communicator", "root", "mjktm");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    MessageDao messageDao = new MessageDao(connection);

    @Test
    public void messageExistsTest()
    {
        assertTrue(messageDao.messageExists(1));
        assertFalse(messageDao.messageExists(100));
    }
    @Test
    public void getMessageByIdTest()
    {
        Message message = messageDao.getMessageById(4);
        assertEquals("Siema", message.getText());
        assertEquals(4, message.getMsg_id());
    }

    @Test
    public void insertMessageTest()
    {
        User send_by= new User("test1", "lks", "test1");
        User send_to= new User("test2", "lks", "test2");
        UserDao userDao = new UserDao(connection);
        try{
            userDao.registerNewUser(send_by);
            userDao.registerNewUser(send_to);
        }
        catch (IllegalArgumentException e){
            System.out.println("Test users already exist, adding step skipped");
        }
        Message message = new Message("test", send_by.getLogin(), send_to.getLogin());
        messageDao.InsertMessage(message);
        assertTrue(messageDao.messageExists(11));
        messageDao.removeMessage(11);
        assertFalse(messageDao.messageExists(11));

        userDao.deleteUser(send_by.getLogin());
        userDao.deleteUser(send_to.getLogin());
    }

    @Test
    public void getMessagesBetweenUsersTest()
    {
        Message[] messages = messageDao.getMessagesBetweenUsers(new String[] {"thekamil444pl", "agatabogusz16"});
        System.out.println(messages[0]);
        System.out.println(messages[1]);
        System.out.println(messages[2]);
    }
}
