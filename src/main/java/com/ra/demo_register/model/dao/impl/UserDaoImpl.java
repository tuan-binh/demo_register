package com.ra.demo_register.model.dao.impl;

import com.ra.demo_register.model.constants.RoleName;
import com.ra.demo_register.model.dao.IUserDao;
import com.ra.demo_register.model.entity.Role;
import com.ra.demo_register.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.relation.RoleStatus;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserDaoImpl implements IUserDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean register(User user)
    {
        Set<Role> roles = new HashSet<>();
        // chỉ tìm ra role thôi vì dữ liệu role đã fix cứng
        roles.add(findByRoleName(RoleName.USER));

        // user gửi từ html lên phải có dữ liệu của 3 trường { fullName, username, password }
        user.setStatus(true);
        user.setRoles(roles);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        Transaction tx = null;
        try (Session session = sessionFactory.openSession())
        {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
        catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        return false;
    }

    private Role findByRoleName(RoleName roleName)
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("from Role r where r.roleName = :_roleName", Role.class)
                    .setParameter("_roleName", roleName)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User login(User user)
    {
        try (Session session = sessionFactory.openSession())
        {
            User userLogin = session.createQuery("from User u where u.username = :_username", User.class)
                    .setParameter("_username", user.getUsername())
                    .getSingleResult();
            if (userLogin != null)
            {
                // kiem tra password
                if(BCrypt.checkpw(user.getPassword(), userLogin.getPassword())) {
                    return userLogin;
                }
            }
            return null;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
