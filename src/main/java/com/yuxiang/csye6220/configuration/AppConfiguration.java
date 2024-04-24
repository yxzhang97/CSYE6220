package com.yuxiang.csye6220.configuration;

import com.yuxiang.csye6220.pojo.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserEntity userEntity_prototype(){
        return new UserEntity();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AddressEntity addressEntity_prototype(){
        return new AddressEntity();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CommentEntity commentEntity_prototype(){
        return new CommentEntity();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ItemEntity itemEntity_prototype(){
        return new ItemEntity();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SellerEntity sellerEntity_prototype(){
        return new SellerEntity();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public OrderEntity orderEntity_prototype(){
        return new OrderEntity();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public OrderItemEntity orderItemEntity_prototype(){
        //OrderItemEntityId id = new OrderItemEntityId();
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        //orderItemEntity.setOrderItemEntityId(id);
        return orderItemEntity;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CartEntity cartEntity_prototype(){
        return new CartEntity();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CartItemEntity cartItemEntity_prototype(){
        CartItemEntityId id = new CartItemEntityId();
        CartItemEntity cartItemEntity = new CartItemEntity();
        //cartItemEntity.setCartItemEntityId(id);
        return cartItemEntity;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public org.hibernate.cfg.Configuration configuration(){
        org.hibernate.cfg.Configuration configuration =  new org.hibernate.cfg.Configuration();
        configuration
                .configure()
                .addAnnotatedClass(AddressEntity.class)
                .addAnnotatedClass(CommentEntity.class)
                .addAnnotatedClass(ItemEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(OrderItemEntity.class)
                .addAnnotatedClass(OrderItemEntityId.class)
                .addAnnotatedClass(SellerEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(CartEntity.class)
                .addAnnotatedClass(CartItemEntity.class)
                .addAnnotatedClass(CartItemEntityId.class);

        return configuration;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Map<String, List<ItemEntity>> homePageItems(){
        return new HashMap<>();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public List<String> homePageCategories(){
        return new LinkedList<>();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserInfoBasic userInfoBasic_prototype(){
        return new UserInfoBasic();
    }
}
