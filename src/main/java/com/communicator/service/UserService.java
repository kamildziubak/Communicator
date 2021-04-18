package com.communicator.service;

import com.communicator.dao.UserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(@Qualifier("user") UserDao userDao) {
        this.userDao = userDao;
    }

    public String getNameOfUser(String login)
    {
        try {
            return userDao.getUserByLogin(login).getName();
        }
        catch(IllegalArgumentException e)
        {
            return null;
        }
    }
}
