package com.example.watermelon.controller;

import com.example.watermelon.model.Product;
import com.example.watermelon.service.ProductService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    @Autowired
    ProductService productService;
    @Autowired
    SessionService sessionService;


    @ModelAttribute("pageProduct")
    public Page<Product> getListProduct() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Product> products = productService.findAllProductOrderByCreatedate(pageable);
        return products;
    }

    @RequestMapping("/home/out")
    public String outAccount() {
        sessionService.remove("user");
        return "redirect:/home/index";
    }
}
