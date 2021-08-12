package com.example.watermelon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class errorController {
    @RequestMapping("home/errorPage")
    public String showErrorPage(@RequestParam("errorMessage") String error, Model model) {
        model.addAttribute("errorMessage", error);
        return "/home/homecontent/errorPage";
    }
}
