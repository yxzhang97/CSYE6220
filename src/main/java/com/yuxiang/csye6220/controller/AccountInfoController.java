package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.AddressEntity;
import com.yuxiang.csye6220.pojo.UserEntity;
import com.yuxiang.csye6220.pojo.UserInfoBasic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account-info")
public class AccountInfoController {

    @Autowired
    ApplicationContext applicationContext;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public AccountInfoController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @GetMapping("/basic")
    public String handleGet_basic(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        UserInfoBasic userInfoBasic = applicationContext.getBean("userInfoBasic_prototype", UserInfoBasic.class);
        userInfoBasic.getBasicInfoFromUserEntity(userEntity);
        model.addAttribute("userInfoBasic", userInfoBasic);
        return "account-info-basic";
    }

    @GetMapping("/basic/modify")
    public String handleGet_basicModify(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        UserInfoBasic userInfoBasic = applicationContext.getBean("userInfoBasic_prototype", UserInfoBasic.class);
        userInfoBasic.getBasicInfoFromUserEntity(userEntity);
        model.addAttribute("userInfoBasic", userInfoBasic);
        return "account-info-basic-modify";
    }

    @PatchMapping("/basic/modify")
    public String handlePut_basic(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            @ModelAttribute(name = "userInfoBasic", binding = true) UserInfoBasic userInfoBasic,
            BindingResult bindingResult,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        userInfoBasic.updateBasicInfoToUserEntity(userEntity);
        userEntity.updateLastModifiedDate();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(userEntity);
            transaction.commit();
        }

        // redirect to url /account-info/basic
        return "redirect:/account-info/basic";
    }

    @GetMapping("/orders")
    public String handleGet_orders(@SessionAttribute(name = "user", required = false) UserEntity userEntity, Model model){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        model.addAttribute("orders", userEntity.getOrders());
        return "account-info-orders";
    }

    @GetMapping("/delivery-addresses")
    public String handleGet_addresses(@SessionAttribute(name = "user", required = false) UserEntity userEntity, Model model){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        model.addAttribute("deliveryAddresses", userEntity.getDeliveryAddresses());
        return "account-info-delivery-addresses";
    }

    @GetMapping("/delivery-addresses/modify/{addressId}")
    public String handleGet_addressesModify(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            @PathVariable(name = "addressId") int addressId,
            Model model
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        String hql = "FROM AddressEntity addressEntity WHERE addressEntity.id = :addressId";
        try(Session session = sessionFactory.openSession()){
            Query<AddressEntity> query = session.createQuery(hql, AddressEntity.class);
            query.setParameter("addressId", addressId);
            AddressEntity addressEntity = query.getSingleResult();
            model.addAttribute("addressEntity", addressEntity);
        }
        return "account-info-delivery-addresses-modify";
    }

    @PatchMapping("/delivery-addresses/modify/{addressId}")
    public String handlePatch_addressesModify(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            @PathVariable(name = "addressId") int addressId,
            @RequestParam(name = "valid") boolean valid,
            @RequestParam(name = "state") String state,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "street") String street,
            @RequestParam(name = "aptNumber") String aptNumber,
            @RequestParam(name = "zipCode") String zipCode
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        String hql = "FROM AddressEntity addressEntity WHERE addressEntity.id = :addressId";
        try(Session session = sessionFactory.openSession()){
            Query<AddressEntity> query = session.createQuery(hql, AddressEntity.class);
            query.setParameter("addressId", addressId);
            AddressEntity addressEntity = query.getSingleResult();
            addressEntity.setValid(valid);
            addressEntity.setState(state);
            addressEntity.setCity(city);
            addressEntity.setStreet(street);
            addressEntity.setAptNumber(aptNumber);
            addressEntity.setZipCode(zipCode);
            addressEntity.updateDateLastModified();

            Transaction transaction = session.beginTransaction();
            session.persist(addressEntity);
            transaction.commit();
        }
        return "redirect:/account-info/delivery-addresses";
    }

    @PatchMapping("/delivery-addresses/modify/default/{addressId}")
    public String handlePatch_addressesModifyDefault(
            @SessionAttribute(name = "user", required = false) UserEntity userEntity,
            @PathVariable(name = "addressId") int addressId
    ){
        // check login state
        if(userEntity == null)
            return "redirect:/login/user";

        String hql = "FROM AddressEntity addressEntity WHERE addressEntity.id = :addressId";
        try(Session session = sessionFactory.openSession()){
            Query<AddressEntity> query = session.createQuery(hql, AddressEntity.class);
            query.setParameter("addressId", addressId);
            AddressEntity addressEntity = query.getSingleResult();

            userEntity.setDefaultDeliveryAddress(addressEntity);
            userEntity.updateLastModifiedDate();

            Transaction transaction = session.beginTransaction();
            session.persist(userEntity);
            transaction.commit();
        }
        return "redirect:/account-info/delivery-addresses";
    }
}
