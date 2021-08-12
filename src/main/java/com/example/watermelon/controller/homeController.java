package com.example.watermelon.controller;

import com.example.watermelon.model.Product;
import com.example.watermelon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @Autowired
    ProductService productService;

    @RequestMapping("/home/index")
    public String showHome(Model model) {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Product> products = productService.findAllProductOrderByCreatedate(pageable);
        model.addAttribute("products", products);
        return "home/homecontent/home";
    }

}
 