package com.example.watermelon.domain;

import com.example.watermelon.model.Account;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CartInterceptor implements HandlerInterceptor {
    @Autowired
    SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Account account = session.get("user");
//        String uri = session.get("uriProductDetail");|
        String uri = request.getRequestURI();
        String error = "";
        if (account == null) {
            error = "errorLogin";

        } else if (request.getRequestURI().startsWith("/admin") && !account.getAdmin()) {
            response.sendRedirect("/home/errorPage?errorMessage=Only Admin");
            return false;
        }
        if (error.length() > 0) {
//            session.set("uriback", uri);
//            response.sendRedirect("/home/errorPage?errorMessage=Please Login");
//            return false;

            session.set("uri", uri);
            request.setAttribute("errorMessage", "Please Login");
            return true;
        } else {
            request.setAttribute("errorMessage", null);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
