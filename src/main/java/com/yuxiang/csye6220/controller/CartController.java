package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.CartEntity;
import com.yuxiang.csye6220.pojo.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class CartController {

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public CartController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @GetMapping("/cart")
    public String handleGet_CartPage(@SessionAttribute(name = "user") UserEntity user, Model model){
        String hql = "From CartEntity cartEntity WHERE cartEntity.userEntity = :user";
        try(Session session = sessionFactory.openSession()){
            Query<CartEntity> query = session.createQuery(hql, CartEntity.class);
            query.setParameter("user", user);
            CartEntity cartEntity = query.getSingleResult();
            model.addAttribute("cartEntity", cartEntity);
        }
        return "cart-page";
    }

    @PostMapping("/cart/newItem/{itemId}")
    public String handlePost_AddingNewItem(
            @SessionAttribute(name = "user") UserEntity user,
            @RequestParam(name = "amount") int amount,
            Model model){
        String hql_getCart = "From CartEntity cartEntity WHERE cartEntity.userEntity = :user";
        //////////
        //////////
        //////////
        try(Session session = sessionFactory.openSession()){
            Query<CartEntity> query = session.createQuery(hql_getCart, CartEntity.class);
            query.setParameter("user", user);
            CartEntity cartEntity = query.getSingleResult();
            model.addAttribute("cartEntity", cartEntity);
        }
        return "cart-add-new-item";
    }
}
