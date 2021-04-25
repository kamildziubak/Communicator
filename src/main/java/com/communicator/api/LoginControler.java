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

    @GetMapping()
    public String verifyLoginData(@RequestParam String login, @RequestParam String password)
    {
        if(loginService.verifyLoginData(login, password))
            return "true";
        else
            return "false";
    }

    @PostMapping
    public int registerNewUser(@RequestBody User user)
    {
        System.out.println(user.getLogin());
        return loginService.registerNewUser(user);
    }
}
