package com.ra.demo_register.model.service.impl;

import com.ra.demo_register.model.dao.IUserDao;
import com.ra.demo_register.model.entity.User;
import com.ra.demo_register.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean register(User user)
    {
        return userDao.register(user);
    }

    @Override
    public User login(User user)
    {
        User userLogin = userDao.login(user);

        httpSession.setAttribute("userLogin", userLogin);

        return userLogin;
    }
}
