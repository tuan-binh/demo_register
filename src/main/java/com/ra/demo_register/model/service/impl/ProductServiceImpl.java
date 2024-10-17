package com.ra.demo_register.model.service.impl;

import com.ra.demo_register.model.dao.IProductDao;
import com.ra.demo_register.model.entity.Product;
import com.ra.demo_register.model.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll()
    {
        return productDao.findAll();
    }
}
