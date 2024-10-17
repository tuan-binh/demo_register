package com.ra.demo_register.model.dao.impl;

import com.ra.demo_register.model.dao.ICartDao;
import com.ra.demo_register.model.entity.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl implements ICartDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addToCart(CartItem cartItem)
    {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession())
        {
            // dưa vào userId và productId có tồn tại cart hay không
            CartItem oldCartItem = findByUserIdAndProductId(cartItem.getUser().getId(), cartItem.getProduct().getId());
            if (oldCartItem != null)
            {
                // chức năng cập nhật nếu mua trùng sản phẩm
                int totalQuantity = oldCartItem.getQuantity() + cartItem.getQuantity();
                if (totalQuantity < oldCartItem.getProduct().getStock())
                {
                    oldCartItem.setQuantity(totalQuantity);
                }
            }
            else
            {
                // chức năng thêm nếu không tồn tại
                oldCartItem = new CartItem();
                oldCartItem.setQuantity(cartItem.getQuantity());
                oldCartItem.setProduct(cartItem.getProduct());
                oldCartItem.setUser(cartItem.getUser());
            }

            tx = session.beginTransaction();
            session.saveOrUpdate(oldCartItem);
            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }


    private CartItem findByUserIdAndProductId(Long userId, Long productId)
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("from CartItem c where c.user.id = :_userId and c.product.id = :_productId", CartItem.class)
                    .setParameter("_userId", userId)
                    .setParameter("_productId", productId)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
