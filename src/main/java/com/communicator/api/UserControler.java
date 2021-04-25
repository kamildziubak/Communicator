package com.communicator.api;

import com.communicator.module.User;
import com.communicator.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserControler {
    private final UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="{login}")
    public String getNameOfUser(@PathVariable("login") String login)
    {
        return userService.getNameOfUser(login);
    }

    @GetMapping
    public User[] getAllUsers()
    {
        return userService.getAllUsers();
    }
}
