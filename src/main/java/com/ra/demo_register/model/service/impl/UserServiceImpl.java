package com.ra.demo_register.model.service.impl;

import com.ra.demo_register.model.dao.IUserDao;
import com.ra.demo_register.model.entity.User;
import com.ra.demo_register.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private IUserDao userDao;

    @Override
    public boolean register(User user)
    {
        return userDao.register(user);
    }
}
