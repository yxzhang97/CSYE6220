package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.SellerEntity;
import com.yuxiang.csye6220.pojo.SellerLoginDTO;
import com.yuxiang.csye6220.pojo.UserEntity;
import com.yuxiang.csye6220.pojo.UserLoginDTO;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"user", "seller"})
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ApplicationContext applicationContext;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public LoginController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @GetMapping("/user")
    public String handleGet_userLogin(){
        return "login-user";
    }

    @PostMapping("/user")
    public String handlePost_userLogin(@ModelAttribute UserLoginDTO userLoginDTO, BindingResult bindingResult, HttpSession httpSession){
        try(Session session = sessionFactory.openSession()){
            String hql = "FROM UserEntity userEntity WHERE userEntity.emailAddress = :emailAddress AND userEntity.password = :password";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            query.setParameter("emailAddress", userLoginDTO.getEmailAddress());
            query.setParameter("password", userLoginDTO.getPassword());
            UserEntity userEntity = query.getSingleResultOrNull();

            if(userEntity == null)
                return "login-user-error";

            httpSession.setAttribute("user", userEntity);
        }
        return "login-user-successful";
    }

    @GetMapping("/user/logout")
    public String handleGet_userLogout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/login/user";
    }

    @GetMapping("/seller")
    public String handleGet_sellerLogin(){
        return "login-seller";
    }

    @PostMapping("/seller")
    public String handlePost_sellerLogin(@ModelAttribute SellerLoginDTO sellerLoginDTO, BindingResult bindingResult, HttpSession httpSession){
        try(Session session = sessionFactory.openSession()){
            String hql = "FROM SellerEntity sellerEntity WHERE sellerEntity.name = :name AND sellerEntity.password = :password";
            Query<SellerEntity> query = session.createQuery(hql, SellerEntity.class);
            query.setParameter("name", sellerLoginDTO.getName());
            query.setParameter("password", sellerLoginDTO.getPassword());
            SellerEntity sellerEntity = query.getSingleResultOrNull();

            if(sellerEntity == null)
                return "login-seller-error";

            httpSession.setAttribute("seller", sellerEntity);
        }
        return "login-seller-successful";
    }

    @GetMapping("/seller/logout")
    public String handleGet_sellerLogout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/login/seller";
    }
}
