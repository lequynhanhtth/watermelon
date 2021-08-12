package com.example.watermelon.controller;

import com.example.watermelon.model.Account;
import com.example.watermelon.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class userManagerController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/admin/showusers")
    public String showUsers(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(1) - 1, 8);
        Page<Account> accounts = accountService.findByAdmin(false, pageable);
        model.addAttribute("accounts", accounts);
        return "home/admin/usermanager";
    }
    @RequestMapping("/admin/showuser/{id}")
    public String showUser(Model model , @PathVariable("id") long id){
        Pageable pageable = PageRequest.of(0, 8);
        Page<Account> accounts = accountService.findByAdmin(false, pageable);
        model.addAttribute("accounts", accounts);
        Account account = accountService.findById(id).get();
        model.addAttribute("accountpage",account);
        model.addAttribute("show",true);
        return "home/admin/usermanager";
    }
    @RequestMapping("/admin/account/delete/{id}")
    public String banUser(@PathVariable("id") long id){
        Account account = accountService.findById(id).get();
        account.setActivity(false);
        accountService.save(account);
        return "redirect:/admin/showuser/"+id;
    }
    @RequestMapping("/admin/account/openban/{id}")
    public String openbanUser(@PathVariable("id") long id){
        Account account = accountService.findById(id).get();
        account.setActivity(true);
        accountService.save(account);
        return "redirect:/admin/showuser/"+id;
    }
}
