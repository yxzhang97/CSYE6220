package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.CartItemEntity;
import com.yuxiang.csye6220.pojo.OrderEntity;
import com.yuxiang.csye6220.pojo.OrderItemEntity;
import com.yuxiang.csye6220.pojo.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
            @SessionAttribute(name = "user", required = false)UserEntity userEntity
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            OrderEntity orderEntity = applicationContext.getBean("orderEntity_prototype", OrderEntity.class);
            orderEntity.setOrderItems(new LinkedList<>());
            orderEntity.setValid(true);
            orderEntity.setOwner(userEntity);
            userEntity.getOrders().add(orderEntity);
            session.persist(orderEntity);

            List<CartItemEntity> cartItems = userEntity.getCartEntity().getCartItems();
            List<OrderItemEntity> orderItems = new LinkedList<>();
            for (CartItemEntity c : cartItems) {
                OrderItemEntity orderItemEntity = applicationContext.getBean("orderItemEntity_prototype", OrderItemEntity.class);
                orderItemEntity.setDateCreated(new Date());
                orderItemEntity.setDateLastModified(orderItemEntity.getDateCreated());
                orderItemEntity.setValid(true);
                orderItemEntity.setAmount(c.getAmount());
                orderItemEntity.setUnitPrice(c.getUnitPrice());
                orderItemEntity.setTotalPrice(c.getTotalPrice());
                orderItemEntity.setDescription(c.getDescription());
                orderItemEntity.setItemEntity(c.getItemEntity());
                orderItemEntity.setOrderEntity(orderEntity);
                session.persist(orderItemEntity);

                c.getItemEntity().setInventory(c.getItemEntity().getInventory() - 1);

                orderEntity.getOrderItems().add(orderItemEntity);
                orderEntity.setTotalPrice(orderEntity.getTotalPrice() + orderItemEntity.getTotalPrice());
            }
            transaction.commit();
        }
        return "order-new-successful";
    }
}
