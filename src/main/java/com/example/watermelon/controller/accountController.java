package com.example.watermelon.controller;

import com.example.watermelon.mail.MailRequest;
import com.example.watermelon.model.Account;
import com.example.watermelon.service.AccountService;
import com.example.watermelon.service.EmailService;
import com.example.watermelon.service.ParamService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class accountController {
    @Autowired
    AccountService accountService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ParamService paramService;
    @Autowired
    EmailService emailService;

    @RequestMapping("/user/login")
    public String showLogin() {
        return "home/homecontent/login";
    }

    @RequestMapping("/user/dologin")
    public String doLogin(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (username == null) {
            model.addAttribute("messageLogin", "Vui lòng điền tài khoản");
        } else {
            Account account = accountService.findByUsername(username).orElse(null);
            if (account != null) {
                if (account.getPassword().equals(password)) {
                    if (account.getAdmin()) {
                        sessionService.set("user", account);
                        return "redirect:/admin/showAdmin";
                    } else {
                        if (account.getActivity() == false) {
                            model.addAttribute("messageLogin", "Tài khoản của bạn bị khóa");
                            return "home/homecontent/login";
                        }
                        String uri = sessionService.get("uriback");
                        if (uri != null) {
                            sessionService.remove("uriback");
                            sessionService.set("user", account);
                            return "redirect:" + uri;
                        } else {
                            sessionService.set("user", account);
                            return "redirect:/home/index";
                        }
                    }
                } else {
                    model.addAttribute("messageLogin", "Sai mật khẩu");
                }
            } else {
                model.addAttribute("messageLogin", "Tài khoảng không tồn tại");
            }
        }
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "home/homecontent/login";
    }

    @RequestMapping("/user/loginmodal")
    public ModelAndView doLoginModal(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password) {
        String uri = sessionService.get("uri");
        if (username == null) {
            model.addAttribute("messageLogin", "Vui lòng điền tài khoảng");
        } else {
            Account account = accountService.findByUsername(username).orElse(null);
            if (account != null) {
                if (account.getPassword().equals(password)) {
                    if (account.getAdmin()) {
                        sessionService.set("user", account);
                        return new ModelAndView("redirect:/admin/showAdmin", model);
                    } else {
                        if (account.getActivity() == false) {
                            return new ModelAndView("home/homecontent/errorPage");
                        }
                        if (uri != null) {
                            sessionService.remove("uriback");
                            sessionService.set("user", account);
                            return new ModelAndView("redirect:" + uri, model);
                        } else {
                            sessionService.set("user", account);
                            return new ModelAndView("redirect:" + uri);
                        }
                    }
                } else {
                    model.addAttribute("messageLogin", "Sai mật khẩu");
                }
            } else {
                model.addAttribute("messageLogin", "Tài khoảng không tồn tại");
            }
        }

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return new ModelAndView("forward:" + uri, model);
    }

    @RequestMapping("/user/profile")
    public String showProfile(Model model) {
        model.addAttribute("account", sessionService.get("user"));
        return "home/homecontent/profile";
    }

    @RequestMapping("/user/profile/save")
    public String saveProfile(@ModelAttribute @Valid Account account, BindingResult bindingResult, @RequestParam("photo") MultipartFile photo) {
        if (bindingResult.hasErrors()) {
            return "home/homecontent/profile";
        }
        if (!photo.isEmpty()) {
            paramService.save(photo, "/src/main/resources/static/images/account");
            account.setImage(photo.getOriginalFilename());
        }
//        Date birthday;
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            birthday = simpleDateFormat.parse(date);
//        } catch (Exception e) {
//            birthday = null;
//            throw new RuntimeException();
//        }
//        account.setBirthday(birthday);
        sessionService.set("user", account);
        accountService.save(account);
        return "redirect:/user/profile";
    }

    @RequestMapping("/user/profile/showchangepassword")
    public String showchangePassword() {
        return "home/homecontent/profileChangePass";
    }

    @RequestMapping("/user/profile/changepassword")
    public String changePassword(Model model, @RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword) {
        Account account = sessionService.get("user");
        if (oldpassword.equals(account.getPassword())) {
            if (newpassword.equals(confirmpassword)) {
                account.setPassword(newpassword);
                sessionService.set("user", account);
                accountService.save(account);
            } else {
                model.addAttribute("messagechange", "Mật khẩu xác nhận không đúng");
            }
        } else {
            model.addAttribute("messagechange", "Mật khẩu không đúng");
        }
        return "home/homecontent/profileChangePass";
    }

    @RequestMapping("/home/getUser")
    public String getUser(@RequestParam("username") String username, @RequestParam("email") String email) {
        Account account = accountService.findByUsername(username).orElse(null);

        if (account != null) {
            MailRequest mailRequest = new MailRequest();
            mailRequest.setFrom("lequynhanhtth@gmail.com");
            mailRequest.setTo(account.getEmail());
            mailRequest.setName(account.getFullname());
            mailRequest.setSubject("Lấy mật khẩu ");
            Map<String, Object> model = new HashMap<>();
            model.put("Name", mailRequest.getName());
            model.put("password", account.getPassword());
            emailService.sendEmailGetUser(mailRequest, model);
            return "home/homecontent/check";
        } else {
            return "home/homecontent/errorPage";
        }
    }

}
