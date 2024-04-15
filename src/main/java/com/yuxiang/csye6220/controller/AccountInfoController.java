package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
@RequestMapping("/account-info")
public class AccountInfoController {

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public AccountInfoController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @GetMapping("/basic")
    public String handleGet_basic(@SessionAttribute(name = "user") UserEntity userEntity, Model model){
        model.addAttribute("user", userEntity);
        return "account-info-basic";
    }

    @GetMapping("/orders")
    public String handleGet_orders(@SessionAttribute(name = "user") UserEntity userEntity, Model model){
        model.addAttribute("orders", userEntity.getOrders());
        return "account-info-orders";
    }

    @GetMapping("/delivery-addresses")
    public String handleGet_addresses(@SessionAttribute(name = "user") UserEntity userEntity, Model model){
        model.addAttribute("deliveryAddresses", userEntity.getDeliveryAddresses());
        return "account-info-delivery-addresses";
    }
}
