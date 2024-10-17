package com.ra.demo_register.model.dao.impl;

import com.ra.demo_register.model.dao.IProductDao;
import com.ra.demo_register.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll()
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("from Product", Product.class).list();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
