package com.ra.demo_register.controller.user;

import com.ra.demo_register.model.entity.CartItem;
import com.ra.demo_register.model.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/cart")
public class CartController
{
    @Autowired
    private ICartService cartService;

    @PostMapping
    public String addToCart(@ModelAttribute CartItem cartItem)
    {
        cartService.addToCart(cartItem);
        return "redirect:/user/product";
    }

}
