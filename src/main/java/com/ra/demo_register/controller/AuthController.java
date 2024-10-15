package com.ra.demo_register.controller;

import com.ra.demo_register.model.constants.RoleName;
import com.ra.demo_register.model.entity.User;
import com.ra.demo_register.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.NoResultException;

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

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user, Model model)
    {
        try
        {
            User userLogin = userService.login(user);
            if (userLogin != null)
            {
                // check role (quyền)
                if (userLogin.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ADMIN)))
                {
                    return "redirect:/admin";
                }
                else
                {
                    return "redirect:/user";
                }
            }
            return "redirect:/login";
        }
        catch (Exception e)
        {
            model.addAttribute("user", user);
            model.addAttribute("error", "Username or password is incorrect");
            return "login";
        }
    }

    @GetMapping("/403")
    public String accessDenied()
    {
        return "accessDeniedPage";
    }

}
