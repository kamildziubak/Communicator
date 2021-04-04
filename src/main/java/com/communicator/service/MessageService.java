package com.communicator.service;

import com.communicator.dao.MessageDao;
import com.communicator.dao.UserDao;
import com.communicator.module.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final UserDao userDao;
    private final MessageDao messageDao;

    public MessageService(@Qualifier("user") UserDao userDao, @Qualifier("message") MessageDao messageDao) {
        this.userDao = userDao;
        this.messageDao = messageDao;
    }

    public Message readMessageById(Integer id)
    {
        return messageDao.getMessageById(id);
    }

    public int sendMessage(Message message)
    {
        return messageDao.InsertMessage(message);
    }
}
