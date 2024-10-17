package com.ra.demo_register.model.service;

import com.ra.demo_register.model.entity.Product;

import java.util.List;

public interface IProductService
{
    List<Product> findAll();
}
