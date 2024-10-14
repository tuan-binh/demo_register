package com.ra.demo_register.controller;

import com.ra.demo_register.model.entity.User;
import com.ra.demo_register.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Authentication - Xác thực // Authorization - Phân quyền
@Controller
@RequestMapping
public class AuthController
{

    @Autowired
    private IUserService userService;

    @GetMapping
    public String index()
    {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("user") User user)
    {
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

}
