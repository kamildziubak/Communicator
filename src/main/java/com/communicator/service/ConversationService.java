package com.communicator.service;

import com.communicator.dao.ConversationDao;
import com.communicator.dao.MessageDao;
import com.communicator.module.Conversation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ConversationService {
    private final ConversationDao conversationDao;
    private final MessageDao messageDao;

    public ConversationService(@Qualifier("conversation") ConversationDao conversationDao, @Qualifier("message") MessageDao messageDao) {
        this.conversationDao = conversationDao;
        this.messageDao = messageDao;
    }

    public Conversation[] getAllConversationsOfTheUser(String login)
    {
        return conversationDao.getAllConversationsOfTheUser(login);
    }

    public Conversation getConversation(String[] users) throws SQLException {
        messageDao.markAllMessagesRead(users);
        return new Conversation(users);
    }
}
