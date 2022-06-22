package com.communicator.dao;

import com.communicator.module.Conversation;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversationDaoTest {
    private final ConversationDao conversationDao = new ConversationDao(DriverManager.getConnection("jdbc:mysql://localhost:3306/communicator", "root", "mjktm"));

    public ConversationDaoTest() throws SQLException {
    }

    @Test
    public void getAllConversationsOfTheUserTest()
    {
        Conversation[] converations = conversationDao.getAllConversationsOfTheUser("debug1");

        /*for(int i=0; i<2; i++)
            System.out.println(converations[0].getUsers()[i]);*/

        assertEquals("debug1", converations[0].getUsers()[0]);
        assertEquals("debug2", converations[0].getUsers()[1]);

    }
}
