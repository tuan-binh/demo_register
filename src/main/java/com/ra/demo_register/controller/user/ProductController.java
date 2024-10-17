package com.ra.demo_register.controller.user;

import com.ra.demo_register.model.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/product")
public class ProductController
{
    @Autowired
    private IProductService productService;

    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("products", productService.findAll());
        return "user/index";
    }

}
