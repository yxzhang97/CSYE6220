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

@Controller
@SessionAttributes("user")
public class CartController {

    @Autowired
    ApplicationContext applicationContext;

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

    @PutMapping("/cart")
    public String handlePut_CartPage(
            @SessionAttribute(name = "user") UserEntity user,
            @RequestParam(name = "itemId") int itemId,
            @RequestParam(name = "amount") int amount,
            Model model
    ){
        String hql_getCart = "From CartEntity cartEntity WHERE cartEntity.user.id =: userId";
        String hql_getCartItem = "From CartItemEntity cartItemEntity WHERE cartItemEntity.id.cartId = :cartId AND cartItemEntity.id.itemId = :itemId";
        try(Session session = sessionFactory.openSession()){
            Query<CartEntity> query_getCart = session.createQuery(hql_getCart, CartEntity.class);
            query_getCart.setParameter("userId", user.getId());
            CartEntity cartEntity = query_getCart.getSingleResult();

            Query<CartItemEntity> query_getCartItem = session.createQuery(hql_getCartItem, CartItemEntity.class);
            query_getCartItem.setParameter("cartId", cartEntity.getId());
            query_getCartItem.setParameter("itemId", itemId);
            CartItemEntity cartItemEntity = query_getCartItem.getSingleResult();
            cartItemEntity.setAmount(amount);
            cartItemEntity.updateTotalPrice();
            cartItemEntity.updateDateLastModified();

            cartEntity.updateTotalPrice();
            cartEntity.updateNumOfItems();
            cartEntity.updateLastModifiedDate();

            Transaction transaction = session.beginTransaction();
            session.persist(cartItemEntity);
            session.persist(cartEntity);
            transaction.commit();

            model.addAttribute("cartEntity", cartEntity);
        }
        return "cart-page";
    }

    @DeleteMapping("/cart")
    public String handleDelete_CartPage(
            @SessionAttribute(name = "user") UserEntity user,
            @RequestParam(name = "itemId") int itemId,
            Model model
    ){
        String hql_getCart = "From CartEntity cartEntity WHERE cartEntity.user.id =: userId";
        String hql_getCartItem = "From CartItemEntity cartItemEntity WHERE cartItemEntity.id.cartId = :cartId AND cartItemEntity.id.itemId = :itemId";
        try(Session session = sessionFactory.openSession()){
            Query<CartEntity> query_getCart = session.createQuery(hql_getCart, CartEntity.class);
            query_getCart.setParameter("userId", user.getId());
            CartEntity cartEntity = query_getCart.getSingleResult();

            Query<CartItemEntity> query_getCartItem = session.createQuery(hql_getCartItem, CartItemEntity.class);
            query_getCartItem.setParameter("cartId", cartEntity.getId());
            query_getCartItem.setParameter("itemId", itemId);
            CartItemEntity cartItemEntity = query_getCartItem.getSingleResult();
            cartItemEntity.setValid(false);
            cartItemEntity.updateDateLastModified();

            cartEntity.updateTotalPrice();
            cartEntity.updateNumOfItems();
            cartEntity.updateLastModifiedDate();

            Transaction transaction = session.beginTransaction();
            session.persist(cartItemEntity);
            session.persist(cartEntity);
            transaction.commit();

            model.addAttribute("cartEntity", cartEntity);
        }
        return "cart-page";
    }

    @PostMapping("/cart/newItem/{itemId}")
    public String handlePost_AddingNewItem(
            @SessionAttribute(name = "user") UserEntity user,
            @PathVariable(name = "itemId") int itemId,
            @RequestParam(name = "amount") int amount,
            Model model
    ){
        String hql_getCart = "From CartEntity cartEntity WHERE cartEntity.userEntity.id = :userId";
        String hql_getItem = "From ItemEntity itemEntity WHERE itemEntity.id = :itemId";
        try(Session session = sessionFactory.openSession()){
            Query<CartEntity> query_getCart = session.createQuery(hql_getCart, CartEntity.class);
            query_getCart.setParameter("userId", user.getId());
            CartEntity cartEntity = query_getCart.getSingleResult();

            Query<ItemEntity> query_getItem = session.createQuery(hql_getItem, ItemEntity.class);
            query_getItem.setParameter("itemId", itemId);
            ItemEntity itemEntity = query_getItem.getSingleResult();

            CartItemEntity cartItemEntity = applicationContext.getBean("cartItemEntity_prototype", CartItemEntity.class);
            cartItemEntity.setCartEntity(cartEntity);
            cartItemEntity.setItemEntity(itemEntity);
            cartItemEntity.setAmount(amount);
            cartItemEntity.setUnitPrice(itemEntity.getPrice());
            cartItemEntity.updateTotalPrice();
            cartItemEntity.setValid(true);
            cartItemEntity.updateDateCreate();

            Transaction transaction = session.beginTransaction();
            session.persist(cartItemEntity);
            transaction.commit();

            model.addAttribute("cartItemEntity", cartItemEntity);
        }
        return "cart-add-new-item";
    }
}
