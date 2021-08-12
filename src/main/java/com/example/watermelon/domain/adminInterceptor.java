package com.example.watermelon.domain;

import com.example.watermelon.model.Account;
import com.example.watermelon.model.Order;
import com.example.watermelon.service.OrderService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class adminInterceptor implements HandlerInterceptor {
    @Autowired
    SessionService sessionService;
    @Autowired
    OrderService orderService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Account account = sessionService.get("user");
        if (account != null) {
            if (account.getAdmin()) {
                List<Order> orders = orderService.findAll();
                List<Order> orders1 = new ArrayList<>();
                for(Order x : orders){
                    if(x.getStatus().equalsIgnoreCase("Chờ xác nhận")){
                        orders1.add(x);
                    }
                }
                request.setAttribute("orderCXN",orders1);
                request.setAttribute("size",orders1.size());

            }
        }
    }
}
