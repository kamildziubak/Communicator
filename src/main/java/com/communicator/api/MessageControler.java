package com.communicator.api;

import com.communicator.module.Message;
import com.communicator.service.LoginService;
import com.communicator.service.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/message")
public class MessageControler {
    private final MessageService messageService;
    private final LoginService loginService;

    public MessageControler(LoginService loginService, MessageService messageService) {
        this.messageService = messageService;
        this.loginService = loginService;
    }

    @GetMapping(path="{id}")
    public Message readMessageById(@PathVariable("id") Integer id)
    {
        return messageService.readMessageById(id);
    }

    @PostMapping
    public int sendMessage(@RequestBody Message message)
    {
        return messageService.sendMessage(message);
    }
}
