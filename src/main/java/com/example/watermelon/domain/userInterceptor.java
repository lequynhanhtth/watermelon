//package com.example.watermelon.domain;
//
//import com.example.watermelon.model.Account;
//import com.example.watermelon.service.SessionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class userInterceptor implements HandlerInterceptor {
//    @Autowired
//    SessionService sessionService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        Account account = sessionService.get("user");
//        if (account != null) {
//            if (account.getAdmin()) {
//                request.setAttribute("admin", true);
//            }
//            request.setAttribute("accountlogin", account);
//        } else {
//            request.setAttribute("admin", null);
//            request.setAttribute("accountlogin", null);
//        }
//    }
//}
