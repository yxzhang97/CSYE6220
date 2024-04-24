package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;


@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    ApplicationContext applicationContext;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public RegisterController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @GetMapping("/user")
    public String handleGet_userRegister(){
        return "register-user";
    }

    @PostMapping("/user")
    public String handlePost_userRegister(
            @ModelAttribute UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            Model model
    ){
        try(Session session = sessionFactory.openSession()){
            String hql = "SELECT emailAddress FROM UserEntity userEntity WHERE userEntity.emailAddress = :emailAddress";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("emailAddress", userRegisterDTO.getEmailAddress());
            if(query.getSingleResultOrNull() != null) {
                model.addAttribute("message", "email exists");
                return "register-user-error";
            }
        }

        UserEntity userEntity = applicationContext.getBean("userEntity_prototype", UserEntity.class);
        userRegisterDTO.updateBasicInfoToUserEntity(userEntity);
        userEntity.setValid(true);
        userEntity.updateCreatedDate();
        userEntity.updateLastModifiedDate();

        CartEntity cartEntity = applicationContext.getBean("cartEntity_prototype", CartEntity.class);
        cartEntity.setUserEntity(userEntity);
        cartEntity.setCartItems(new LinkedList<>());
        cartEntity.updateNumOfItems();
        cartEntity.updateTotalPrice();
        cartEntity.updateLastModifiedDate();

        userEntity.setCartEntity(cartEntity);

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(userEntity);
            session.persist(cartEntity);
            transaction.commit();
        }
        return "redirect:/login/user";
    }

    @GetMapping("/seller")
    public String handleGet_sellerRegister(){
        return "register-seller";
    }

    @PostMapping("/seller")
    public String handlePost_sellerRegister(
            @ModelAttribute SellerRegisterDTO sellerRegisterDTO,
            BindingResult bindingResult,
            Model model
    ){
        try(Session session = sessionFactory.openSession()) {
            String hql = "SELECT name FROM SellerEntity sellerEntity WHERE sellerEntity.name = :name";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("name", sellerRegisterDTO.getName());
            if (query.getSingleResultOrNull() != null) {
                model.addAttribute("message", "name exists");
                return "register-seller-error";
            }
        }

        SellerEntity sellerEntity = applicationContext.getBean("sellerEntity_prototype", SellerEntity.class);
        sellerRegisterDTO.updateBasicInfoToSellerEntity(sellerEntity);
        sellerEntity.setValid(true);
        sellerEntity.updateCreatedDate();
        sellerEntity.updateLastModifiedDate();

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(sellerEntity);
            transaction.commit();
        }
        return "redirect:/login/seller";
    }
}
