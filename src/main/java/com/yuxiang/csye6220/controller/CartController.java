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
@RequestMapping("/cart")
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

    @GetMapping("/all")
    public String handleGet_CartPage(@SessionAttribute(name = "user", required = false) UserEntity userEntity, Model model){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        String hql = "FROM CartEntity cartEntity WHERE cartEntity.userEntity.id = :userId";
        try(Session session = sessionFactory.openSession()){
            Query<CartEntity> query = session.createQuery(hql, CartEntity.class);
            query.setParameter("userId", userEntity.getId());
            CartEntity cartEntity = query.getSingleResult();
            model.addAttribute("cartEntity", cartEntity);
        }
        return "cart-all";
    }

    @RequestMapping("/delete/{cartItemId}")
    public String handleDelete_CartPage(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            @PathVariable(name = "cartItemId") int cartItemId,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        CartEntity cartEntity = userEntity.getCartEntity();
        for(CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            if(cartItemEntity.getId() == cartItemId) {
                try (Session session = sessionFactory.openSession()) {
                    cartItemEntity.setValid(false);

                    cartEntity.updateTotalPrice();
                    cartEntity.updateNumOfItems();
                    cartEntity.updateLastModifiedDate();

                    Transaction transaction = session.beginTransaction();
                    session.merge(cartItemEntity);
                    session.merge(cartEntity);
                    transaction.commit();

                    model.addAttribute("cartEntity", cartEntity);
                }
            }
        }
        return "cart-all";
    }

    @PostMapping("/newItem/{itemId}")
    public String handlePost_AddingNewItem(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            @PathVariable(name = "itemId") int itemId,
            @RequestParam(name = "amount") int amount,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        String hql_getCart = "FROM CartEntity cartEntity WHERE cartEntity.userEntity.id = :userId";
        String hql_getItem = "FROM ItemEntity itemEntity WHERE itemEntity.id = :itemId";
        try(Session session = sessionFactory.openSession()){
            Query<CartEntity> query_getCart = session.createQuery(hql_getCart, CartEntity.class);
            query_getCart.setParameter("userId", userEntity.getId());
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

            cartEntity.getCartItems().add(cartItemEntity);
            cartEntity.updateNumOfItems();
            cartEntity.updateTotalPrice();
            cartEntity.updateLastModifiedDate();

            userEntity.setCartEntity(cartEntity);

            Transaction transaction = session.beginTransaction();
            session.persist(cartItemEntity);
            session.persist(cartEntity);
            transaction.commit();

            model.addAttribute("cartItemEntity", cartItemEntity);
        }
        return "cart-add-new-item";
    }
}
