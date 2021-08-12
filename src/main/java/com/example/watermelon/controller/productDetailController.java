package com.example.watermelon.controller;

import com.example.watermelon.model.Product;
import com.example.watermelon.repository.CartDAO;
import com.example.watermelon.service.ProductService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class productDetailController {
    @Autowired
    ProductService productService;
    @Autowired
    SessionService sessionService;

    @RequestMapping("/home/productdetail/{id}")
    public String getProductDetail(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id).get();
        model.addAttribute("productDetail", product);
        Pageable pageable = PageRequest.of(0, 4);
        List<Product> products = productService.findByCategoryOrderByRate(product.getCategory().getId(), pageable).getContent();
        model.addAttribute("productdetails", products);
        sessionService.set("uriProductDetail","/home/productdetail/"+id);
        return "home/homecontent/productdetail";
    }

    @RequestMapping("/user/cart/add")
    public String addCart(@RequestParam("id") long id, @RequestParam("soluong") int quantity,@RequestParam("size") String size) {
        Product product = productService.findById(id).get();
        CartDAO.addCart(id,quantity,product,size);
        return "redirect:/home/productdetail/" + id;
    }
}
