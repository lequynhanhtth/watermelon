//package com.example.watermelon.config;
//
//import com.example.watermelon.domain.CartInterceptor;
//import com.example.watermelon.domain.adminInterceptor;
//import com.example.watermelon.domain.userInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Component
//public class InterConfig implements WebMvcConfigurer {
//
//    @Autowired
//    CartInterceptor cart;
//
//    @Autowired
//    userInterceptor user;
//
//    @Autowired
//    adminInterceptor admin;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(global).addPathPatterns("/**").excludePathPatterns("/assets/**");
//        registry.addInterceptor(cart).addPathPatterns("/user/cart/**","/user/profile/**","/admin/**").excludePathPatterns();
//        registry.addInterceptor(user).addPathPatterns("/**").excludePathPatterns();
//        registry.addInterceptor(admin).addPathPatterns("/admin/**").excludePathPatterns();
//
//    }
//}
