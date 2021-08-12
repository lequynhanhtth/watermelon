package com.example.watermelon.controller;

import com.example.watermelon.model.Account;
import com.example.watermelon.model.Cart;
import com.example.watermelon.model.Order;
import com.example.watermelon.model.OrderDetail;
import com.example.watermelon.repository.CartDAO;
import com.example.watermelon.service.AccountService;
import com.example.watermelon.service.OrderDetailService;
import com.example.watermelon.service.OrderService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
public class orderController {
    @Autowired
    SessionService sessionService;

    @Autowired
    OrderService orderService;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderDetailService orderDetailService;

    @RequestMapping("/user/cart/order")
    public String showOrder(Model model) {
        model.addAttribute("orders", CartDAO.Carts);
        model.addAttribute("total", CartDAO.gettoltal());
        model.addAttribute("order", new Order());
        return "home/homecontent/order";
    }

    @RequestMapping("/user/cart/order/bill")
    public String billOrder(@ModelAttribute @Valid Order order, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("orders", CartDAO.Carts);
            model.addAttribute("total", CartDAO.gettoltal());
            model.addAttribute("order",order);
            return "home/homecontent/order";
        }
        order.setCreatedate(new Date());
        Account account = sessionService.get("user");
        order.setAccount(account);
        order.setStatus("Chờ xác nhận");
        orderService.save(order);
        for (Cart x : CartDAO.Carts) {
            OrderDetail orderDetail = new OrderDetail(null, x.getQuantity(), x.getQuantity() * x.getProduct().getPrice(), x.getSize(), order, x.getProduct());
            orderDetailService.save(orderDetail);
        }
        CartDAO.clearAll();
        model.addAttribute("messageCheck","Đặt hàng thành công");
        return "/home/homecontent/check";
    }

    @RequestMapping("/user/profile/showorder")
    public String showprofileOrder(Model model, @RequestParam("p") Optional<Integer> field) {
        Account account = sessionService.get("user");
        Pageable pageable = PageRequest.of(field.orElse(1) - 1, 8);
        Page<Order> orders = orderService.findOrderByAccountId(account.getId(), pageable);
        model.addAttribute("orders", orders);
        return "home/homecontent/profileOrder";
    }

    @RequestMapping("/user/profile/showorderdetail")
    public String showOrderdetail(Model model, @RequestParam("id") long id, @RequestParam("p") Optional<Integer> field) {
        Account account = sessionService.get("user");
        Pageable pageable = PageRequest.of(field.orElse(1) - 1, 8);
        Page<Order> orders = orderService.findOrderByAccountId(account.getId(), pageable);
        model.addAttribute("orders", orders);
        Order order = orderService.findById(id).get();
        if (order.getStatus().equalsIgnoreCase("Chờ xác nhận")) {
            model.addAttribute("showremove", true);
        }
        model.addAttribute("orderDetails", order.getOrderDetails());
        model.addAttribute("orderId", id);
        model.addAttribute("show", true);
        return "home/homecontent/profileOrder";
    }

    @RequestMapping("/user/profile/removeOrder")
    public String removeOrder(@RequestParam("id") long id) {
        Order order = orderService.findById(id).get();
        order.setStatus("Hủy đơn");
        orderService.save(order);
        return "redirect:/user/profile/showorder";
    }

    @RequestMapping("/admin/showorder")
    public String showOrderAdmin(Model model, @RequestParam("p") Optional<Integer> field) {
        Pageable pageable = PageRequest.of(field.orElse(1) - 1, 8);
        Page<Order> orders = orderService.findAll(pageable);
        model.addAttribute("orders", orders);
        return "home/admin/orderAdmin";
    }

    @RequestMapping("/admin/showOrderDetail")
    public String showOrderDetailadmin(Model model, @RequestParam("id") long id, @RequestParam("p") Optional<Integer> field) {
        Pageable pageable = PageRequest.of(field.orElse(1) - 1, 8);
        Page<Order> orders = orderService.findAll(pageable);
        model.addAttribute("orders", orders);
        Order order = orderService.findById(id).get();
        if (order.getStatus().equalsIgnoreCase("Chờ xác nhận")) {
            model.addAttribute("showremove", true);
        }
        if (order.getStatus().equalsIgnoreCase("Hủy đơn")) {
            model.addAttribute("showdelete", true);
        }
        model.addAttribute("orderDetails", order.getOrderDetails());
        model.addAttribute("orderId", id);
        model.addAttribute("show", true);
        return "home/admin/orderAdmin";
    }

    @RequestMapping("/admin/updateStatus/{id}")
    public String updateStatusOrder(Model model, @PathVariable("id") long id) {
        Order order = orderService.findById(id).get();
        order.setStatus("Vận chuyển");
        orderService.save(order);
        return "redirect:/admin/showOrderDetail?id=" + id;
    }

    @RequestMapping("/user/deleteStatus/{id}")
    public String removeOrderAdmin(@PathVariable("id") long id) {
        Order order = orderService.findById(id).get();
        order.setStatus("Hủy đơn");
        orderService.save(order);
        return "redirect:/admin/showOrderDetail?id=" + id;
    }

}
