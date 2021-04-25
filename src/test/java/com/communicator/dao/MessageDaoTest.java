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
        assertFalse(messageDao.messageExists(0));
    }
    @Test
    public void getMessageByIdTest()
    {
        Message message = messageDao.getMessageById(1);
        assertEquals("DEBUG", message.getText());
        assertEquals(1, message.getMsg_id());
    }

    @Test
    public void insertRemoveMessageTest()
    {
        Message message = new Message("test", "debug1", "debug2");
        messageDao.InsertMessage(message);
        int msg_id = messageDao.getLastId();
        assertTrue(messageDao.messageExists(msg_id));
        assertEquals("test", messageDao.getMessageById(msg_id).getText());
        messageDao.removeMessage(msg_id);
        assertFalse(messageDao.messageExists(msg_id));
    }

    @Test
    public void getMessagesBetweenUsersTest()
    {
        Message[] messages = messageDao.getMessagesBetweenUsers(new String[] {"debug1", "debug2"});
        assertEquals("DEBUG", messages[0].getText());
    }
}
