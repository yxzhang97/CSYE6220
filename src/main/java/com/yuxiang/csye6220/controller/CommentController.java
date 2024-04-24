package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ApplicationContext applicationContext;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public CommentController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

//    @PostMapping("/new")
//    public String handlePost_newComment(
//            @SessionAttribute(name = "user")UserEntity userEntity,
//            @RequestParam(name = "orderItemId") int orderItemId,
//            @RequestParam(name = "itemId") int itemId,
//
//            )
}
