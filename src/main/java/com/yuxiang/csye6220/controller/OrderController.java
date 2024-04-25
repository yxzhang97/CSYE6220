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
            model.addAttribute("orderItems", orderEntity.getOrderItems());
        }
        return "order-page";
    }

    @RequestMapping("/newOrder/pre")
    public String handle_orderNewPre(
            @SessionAttribute(name = "user", required = false)UserEntity userEntity,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        if(userEntity.getCartEntity().getNumOfItems() == 0)
            return "redirect:/store-page";

        model.addAttribute("cartEntity", userEntity.getCartEntity());
        model.addAttribute("cartItems", userEntity.getCartEntity().getCartItems());
        model.addAttribute("defaultAddress", userEntity.getDefaultDeliveryAddress());
        model.addAttribute("deliveryAddresses", userEntity.getDeliveryAddresses());

        return "order-new-delivery-address";
    }

    @PostMapping("/newOrder")
    public String handlePost_orderNew(
            @SessionAttribute(name = "user", required = false)UserEntity userEntity,
            @RequestParam(name = "addressId") int addressId,
            Model model
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
            for(AddressEntity addressEntity : userEntity.getDeliveryAddresses())
                if(addressEntity.getId() == addressId){
                    orderEntity.setAddressEntity(addressEntity);
                    break;
                }
            session.persist(orderEntity);

            userEntity.getOrders().add(orderEntity);

            CartEntity cartEntity = userEntity.getCartEntity();
            List<CartItemEntity> cartItems = cartEntity.getCartItems();
            for (CartItemEntity c : cartItems) {
                if(!c.isValid()) continue;
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

                c.getItemEntity().setInventory(c.getItemEntity().getInventory() - c.getAmount());
                c.setValid(false);
                session.merge(c);
                session.merge(c.getItemEntity());

                orderEntity.getOrderItems().add(orderItemEntity);
            }
            orderEntity.updateNumOfItems();
            orderEntity.updateTotalPrice();
            orderEntity.setDateCreated(new Date());
            orderEntity.updateDateLastModified();
            session.persist(orderEntity);

            cartEntity.updateNumOfItems();
            cartEntity.updateTotalPrice();
            cartEntity.updateLastModifiedDate();
            session.merge(cartEntity);

            transaction.commit();

            model.addAttribute("orderEntity", orderEntity);
            model.addAttribute("orderItems", orderEntity.getOrderItems());
            model.addAttribute("deliveryAddress", orderEntity.getAddressEntity());
        }
        return "order-new-successful";
    }
}
