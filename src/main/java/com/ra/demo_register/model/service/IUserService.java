package com.ra.demo_register.model.service;

import com.ra.demo_register.model.entity.User;

public interface IUserService
{
    boolean register(User user);

    User login(User user);
}
