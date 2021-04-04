package com.communicator.api;

import com.communicator.module.User;
import com.communicator.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
public class LoginControler {
    private final LoginService loginService;

    public LoginControler(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public boolean verifyLoginData(@RequestBody User user)
    {
        return loginService.verifyLoginData(user.getLogin(), user.getPassword());
    }

    @PostMapping
    public int registerNewUser(@RequestBody User user)
    {
        return loginService.registerNewUser(user);
    }
}
