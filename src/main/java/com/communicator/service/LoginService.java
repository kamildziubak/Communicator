package com.communicator.service;

import com.communicator.dao.UserDao;
import com.communicator.module.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserDao userDao;

    public LoginService(@Qualifier("user") UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean verifyLoginData(String login, String password)
    {
        User user = userDao.getUserByLogin(login);
        if(user.getPassword().equals(password))
            return true;
        return false;
    }
}