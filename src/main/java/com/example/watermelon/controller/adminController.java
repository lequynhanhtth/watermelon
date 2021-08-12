package com.example.watermelon.controller;

import com.example.watermelon.model.Account;
import com.example.watermelon.model.DashBoard;
import com.example.watermelon.model.productDashboard;
import com.example.watermelon.service.AccountService;
import com.example.watermelon.service.OrderService;
import com.example.watermelon.service.ParamService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class adminController {
    @Autowired
    OrderService orderService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;
    @Autowired
    AccountService accountService;

    @RequestMapping("/admin/showAdmin")
    public String showAdmin(Model model) {
//        List<DashBoard> list = orderService.getDashBoard(new Date().getYear() + 1900);
//        model.addAttribute("dashboard", list);
//        int month = new Date().getMonth()+1;
//        model.addAttribute("year", new Date().getYear() + 1900);
//        model.addAttribute("month", month);
//        double totalAll = 0;
//        int count = 0;
//        for (DashBoard x : list) {
//            totalAll += x.getTotal();
//            count += x.getCount();
//        }
//        model.addAttribute("count", count);
//        model.addAttribute("totalAll", totalAll);
//        List<productDashboard> listP = orderService.getproductDB(2021);
//        model.addAttribute("listP", listP);
        return "home/admin/layoutAdmin";
    }

    @RequestMapping("/admin/profileAdmin")
    public String showProfileAdmin(Model model) {
        return "home/admin/profileadmin";
    }

    @RequestMapping("/admin/profile/save")
    public String saveAdminProfile(@ModelAttribute("account") Account account, @RequestParam("getbirthday") String date, @RequestParam("photo") MultipartFile photo) {
        if (!photo.isEmpty()) {
            paramService.save(photo, "/src/main/resources/static/images/account");
            account.setImage(photo.getOriginalFilename());
        }
        Date birthday;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            birthday = simpleDateFormat.parse(date);
        } catch (Exception e) {
            birthday = null;
            throw new RuntimeException();
        }
        account.setBirthday(birthday);
        sessionService.set("user", account);
        accountService.save(account);
        return "redirect:/admin/profileAdmin";
    }

    @RequestMapping("admin/showchangepassword")
    public String ShowchangePassword() {
        return "home/admin/profileAdminchange";
    }

    @RequestMapping("admin/changepassword")
    public String changePassword(@RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword) {
        Account account = sessionService.get("user");
        if (oldpassword.equals(account.getPassword())) {
            if (newpassword.equals(confirmpassword)) {
                account.setPassword(newpassword);
                sessionService.set("user", account);
                accountService.save(account);
            }
        }
        return "home/admin/profileAdminchange";
    }

}
