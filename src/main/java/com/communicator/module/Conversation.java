package com.communicator.module;

import com.communicator.dao.MessageDao;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conversation {
    String[] users;
    Message[] messages;

    public Conversation(@JsonProperty("users") String[] users) {
        this.users = users;
        MessageDao messageDao = null;
        try {
            messageDao = new MessageDao(DriverManager.getConnection("jdbc:mysql://localhost:3306/communicator", "root", "mjktm"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        messages=messageDao.getMessagesBetweenUsers(users);
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public Message[] getMessages() {
        return messages;
    }
}