package com.ra.demo_register.model.dao;

import com.ra.demo_register.model.entity.Product;

import java.util.List;

public interface IProductDao
{

    List<Product> findAll();

}
