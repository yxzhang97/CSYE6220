package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.CommentEntity;
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

    @GetMapping("/new/{orderItemId}")
    public String handleGet_newComment(
            @SessionAttribute(name = "user")UserEntity userEntity,
            @PathVariable(name = "orderItemId") int orderItemId,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        try(Session session = sessionFactory.openSession()) {
            String hql = "FROM OrderItemEntity orderItemEntity WHERE orderItemEntity.id = :orderItemId";
            Query<OrderItemEntity> query_getOrderItemEntity = session.createQuery(hql, OrderItemEntity.class);
            query_getOrderItemEntity.setParameter("orderItemId", orderItemId);
            OrderItemEntity orderItemEntity = query_getOrderItemEntity.getSingleResultOrNull();
            if (orderItemEntity == null || orderItemEntity.getOrderEntity().getOwner().getId() != userEntity.getId())
                return "comment-invalid";

            model.addAttribute("orderItemEntity", orderItemEntity);
        }
        return "comment-new";
    }

    @PostMapping("/new/{orderItemId}")
    public String handlePost_newComment(
            @SessionAttribute(name = "user")UserEntity userEntity,
            @PathVariable(name = "orderItemId") int orderItemId,
            @RequestParam(name = "text") String text
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        int itemId = -1;

        try(Session session = sessionFactory.openSession()){
            String hql = "FROM OrderItemEntity orderItemEntity WHERE orderItemEntity.id = :orderItemId";
            Query<OrderItemEntity> query_getOrderItemEntity = session.createQuery(hql, OrderItemEntity.class);
            query_getOrderItemEntity.setParameter("orderItemId", orderItemId);
            OrderItemEntity orderItemEntity = query_getOrderItemEntity.getSingleResultOrNull();
            if(orderItemEntity == null || orderItemEntity.getOrderEntity().getOwner().getId() != userEntity.getId())
                return "comment-invalid";

            CommentEntity commentEntity = applicationContext.getBean("commentEntity_prototype", CommentEntity.class);
            commentEntity.setDateCreated(new Date());
            commentEntity.setValid(true);
            commentEntity.setOrderItemEntity(orderItemEntity);
            commentEntity.setItemEntity(orderItemEntity.getItemEntity());
            commentEntity.setUserEntity(userEntity);
            commentEntity.setText(text);

            itemId = orderItemEntity.getItemEntity().getId();

            Transaction transaction = session.beginTransaction();
            session.persist(commentEntity);
            transaction.commit();
        }
        if(itemId != -1)
            return "redirect:/store-page/item-detail/" + itemId;
        else
            return "redirect:/order/all";
    }
}
