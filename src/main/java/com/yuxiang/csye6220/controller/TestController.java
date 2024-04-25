package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.ItemEntity;
import com.yuxiang.csye6220.pojo.TestEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    ApplicationContext applicationContext;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public TestController(Configuration configuration){
        this.configuration = configuration;
        configuration.addAnnotatedClass(TestEntity.class);
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @PostMapping("/test")
    public ModelAndView handle(@RequestParam(name = "file") MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get("./src/main/webapp/img/" + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/test")
    public String handleGet(){
        return "test";
    }
}
