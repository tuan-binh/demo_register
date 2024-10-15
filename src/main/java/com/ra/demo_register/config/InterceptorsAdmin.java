package com.ra.demo_register.config;

import com.ra.demo_register.model.constants.RoleName;
import com.ra.demo_register.model.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorsAdmin implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        User userLogin = (User) request.getSession().getAttribute("userLogin");
        if (userLogin != null)
        {
            if (userLogin.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ADMIN)))
            {
                return true;
            }
            else
            {
                response.sendRedirect("/403");
                return false;
            }
        }
        else
        {
            response.sendRedirect("/login");
            return false;
        }
    }
}
