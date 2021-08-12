package com.example.watermelon.controller;

import com.example.watermelon.mail.MailRequest;
import com.example.watermelon.model.Account;
import com.example.watermelon.service.AccountService;
import com.example.watermelon.service.EmailService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class emailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    AccountService accountService;
    @Autowired
    SessionService sessionService;


    @RequestMapping("/home/sendEmail")
    public String sendEmail(Model modal) {
        Account account = sessionService.get("newaccount");
        MailRequest mailRequest = new MailRequest();
        mailRequest.setFrom("lequynhanhtth@gmail.com");
        mailRequest.setTo(account.getEmail());
        mailRequest.setName(account.getFullname());
        mailRequest.setSubject("Kích hoạt tài khoản ");
        Map<String, Object> model = new HashMap<>();
        model.put("Name", mailRequest.getName());
        emailService.sendEmail(mailRequest, model);
        modal.addAttribute("messageCheck","Vui lòng vào gmail xác nhận tài khoản");
        return "home/homecontent/check";
    }

    @RequestMapping("/home/sendEmailGetUser")
    public String sendEmail(@RequestParam("accountGet") Account account,Model modal)  {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setFrom("lequynhanhtth@gmail.com");
        mailRequest.setTo(account.getEmail());
        mailRequest.setName(account.getFullname());
        mailRequest.setSubject("Lấy mật khẩu ");
        Map<String, Object> model = new HashMap<>();
        model.put("Name", mailRequest.getName());
        model.put("password",account.getPassword());
        emailService.sendEmailGetUser(mailRequest, model);
        modal.addAttribute("messageCheck","Vui lòng vào gmail để lấy mật khẩu");
        return "home/homecontent/check";
    }
}
