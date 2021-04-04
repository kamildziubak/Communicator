package com.communicator.service;

import com.communicator.dao.UserDao;
import com.communicator.module.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class LoginService {
    private final UserDao userDao;

    public LoginService(@Qualifier("user") UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean verifyLoginData(String login, String password)
    {
        try{
            User user = userDao.getUserByLogin(login);
            if(user.getPassword().equals(password))
                return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
        return false;
    }

    public int registerNewUser(User user)
    {
        try {
            userDao.registerNewUser(user);
            return 1;
        }
        catch (IllegalArgumentException e)
        {
            return 0;
        }
    }
}