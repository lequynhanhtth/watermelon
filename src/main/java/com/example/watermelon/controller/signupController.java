package com.example.watermelon.controller;

import com.example.watermelon.model.Account;
import com.example.watermelon.service.AccountService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class signupController {
    @Autowired
    AccountService accountService;
    @Autowired
    SessionService sessionService;

    @RequestMapping("/home/signup")
    public String showSignup(Model model){
        model.addAttribute("account",new Account());
        return "home/homecontent/signup";
    }
    @RequestMapping("/home/createmember")
//    public String createMember(@ModelAttribute("account")Account account , BindingResult bindingResult){
//            sessionService.set("newaccount",account);
//        return "redirect:/home/sendEmail";
//    }
    public String createMember(@ModelAttribute @Valid Account account , BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()) {
            model.addAttribute("account",account);
            return "home/homecontent/signup";
        }
        if(accountService.findByUsername(account.getUsername()).orElse(null) != null){
            model.addAttribute("messageSignup","Tên tài khoản đã tồn tại");
            return  "home/homecontent/signup";
        }
        sessionService.set("newaccount",account);
        return "redirect:/home/sendEmail";
    }
    @RequestMapping("/home/confirm")
    public String confirm(Model model){
        Account account = sessionService.get("newaccount");
        account.setActivity(true);
        accountService.save(account);
        model.addAttribute("messageCheck","Xác nhận thành công");
        return "home/homecontent/check";
    }
}
