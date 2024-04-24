package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.TestEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/test")
    public ModelAndView handle(){
        TestEntity testEntity = new TestEntity();
        testEntity.setName("quindex");
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(testEntity);
            transaction.commit();
        }
        return null;
    }
}
