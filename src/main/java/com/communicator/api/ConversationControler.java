package com.communicator.api;

import com.communicator.module.Conversation;
import com.communicator.module.Message;
import com.communicator.service.ConversationService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("api/conversation")
public class ConversationControler {
    private final ConversationService conversationService;

    public ConversationControler(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping(path="{login}")
    public Conversation[] getAllConversationsOfTheUser(@PathVariable("login") String login)
    {
        return conversationService.getAllConversationsOfTheUser(login);
    }

    @GetMapping
    public Message[] getConversation(@RequestParam String user1, @RequestParam String user2) throws SQLException {
        String[] users = {user1, user2};
        return conversationService.getConversation(users).getMessages();
    }
}
