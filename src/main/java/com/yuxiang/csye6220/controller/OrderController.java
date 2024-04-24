package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.OrderEntity;
import com.yuxiang.csye6220.pojo.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ApplicationContext applicationContext;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public OrderController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @GetMapping("/all")
    public String handleGet_orderAll(@SessionAttribute(name = "user", required = false)UserEntity userEntity, Model model){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        model.addAttribute("orders", userEntity.getOrders());
        return "order-all";
    }

    @GetMapping("/{orderId}")
    public String handleGet_order(
            @SessionAttribute(name = "user", required = false)UserEntity userEntity,
            @PathVariable(name = "orderId") int orderId,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        try(Session session = sessionFactory.openSession()){
            String hql = "FROM OrderEntity orderEntity WHERE orderEntity.id = :orderId";
            Query<OrderEntity> query = session.createQuery(hql, OrderEntity.class);
            query.setParameter("orderId", orderId);
            OrderEntity orderEntity = query.getSingleResultOrNull();
            model.addAttribute("orderEntity", orderEntity);
        }
        return "order-page";
    }

    @PostMapping("/newOrder")
    public String handlePost_orderNew(
            @SessionAttribute(name = "user", required = false)UserEntity userEntity,

    )
}
