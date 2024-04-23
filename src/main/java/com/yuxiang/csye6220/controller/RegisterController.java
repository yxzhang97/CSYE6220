package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.SellerEntity;
import com.yuxiang.csye6220.pojo.SellerRegisterDTO;
import com.yuxiang.csye6220.pojo.UserEntity;
import com.yuxiang.csye6220.pojo.UserRegisterDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("register-user");
        return "register-user.html";
    }

    @PostMapping("/user")
    public String handlePost_userRegister(
            @ModelAttribute UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult
    ){
        UserEntity userEntity = applicationContext.getBean("userEntity_prototype", UserEntity.class);
        userRegisterDTO.updateBasicInfoToUserEntity(userEntity);
        userEntity.setValid(true);
        userEntity.updateCreatedDate();
        userEntity.updateLastModifiedDate();

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(userEntity);
            transaction.commit();
        }
        return "redirect:/login/user";
    }

    @GetMapping("/seller")
    public String handleGet_sellerRegister(){
        return "register-seller.html";
    }

    @PostMapping("/seller")
    public String handlePost_sellerRegister(
            @ModelAttribute SellerRegisterDTO sellerRegisterDTO,
            BindingResult bindingResult
    ){
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
