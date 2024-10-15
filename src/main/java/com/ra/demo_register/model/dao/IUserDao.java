package com.ra.demo_register.model.dao;

import com.ra.demo_register.model.entity.User;

public interface IUserDao
{

    boolean register(User user);
    // tham số user { username , password } vi tu html còn trả về là User đầy đủ thông tin
    User login(User user);

}
