package com.yuxiang.csye6220.configuration;

import com.yuxiang.csye6220.pojo.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
        OrderItemEntityId id = new OrderItemEntityId();
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderItemEntityId(id);
        return orderItemEntity;
    }

}
