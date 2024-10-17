package com.ra.demo_register.model.service.impl;

import com.ra.demo_register.model.dao.ICartDao;
import com.ra.demo_register.model.entity.CartItem;
import com.ra.demo_register.model.entity.User;
import com.ra.demo_register.model.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartServiceImpl implements ICartService
{
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ICartDao cartDao;

    @Override
    public void addToCart(CartItem cartItem)
    {
        User user = (User) httpSession.getAttribute("userLogin");
        cartItem.setUser(user);



        cartDao.addToCart(cartItem);
    }
}
