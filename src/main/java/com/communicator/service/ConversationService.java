package com.communicator.service;

import com.communicator.dao.ConversationDao;
import com.communicator.dao.MessageDao;
import com.communicator.module.Conversation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationDao conversationDao;

    public ConversationService(@Qualifier("conversation") ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    public Conversation[] getAllConversationsOfTheUser(String login)
    {
        return conversationDao.getAllConversationsOfTheUser(login);
    }

    public Conversation getConversation(String[] users)
    {
        return new Conversation(users);
    }
}
