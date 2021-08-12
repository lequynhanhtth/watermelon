package com.example.watermelon.controller;

import com.example.watermelon.repository.CartDAO;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class  cartController {
    @Autowired
    SessionService sessionService;

    @RequestMapping("/user/cart")
    public String showCart(Model model) {
        model.addAttribute("carts", CartDAO.Carts);
        model.addAttribute("total",CartDAO.gettoltal());
        return "home/homecontent/cart";
    }

    @RequestMapping("/user/cart/update/{id}")
    public String updateCart(@PathVariable("id") long id, @RequestParam("soluong") int soluong, @RequestParam("size") String size) {
        CartDAO.update(id, soluong, size);
        return "redirect:/user/cart";
    }

    @RequestMapping("/user/cart/delete/{id}/{size}")
    public String deleteCart(@PathVariable("id") long id, @PathVariable("size") String size) {
        CartDAO.delete(id, size);
        return "redirect:/user/cart";
    }
}
