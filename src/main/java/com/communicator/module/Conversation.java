package com.communicator.module;

import com.communicator.dao.MessageDao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conversation {
    User[] users = new User[2];
    Message[] messages;

    public Conversation(User[] users) {
        this.users = users;
        MessageDao messageDao = null;
        try {
            messageDao = new MessageDao(DriverManager.getConnection("jdbc:mysql://localhost/3306/communicator", "root", "mjktm"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        messages=messageDao.getMessagesBetweenUsers(users);
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}