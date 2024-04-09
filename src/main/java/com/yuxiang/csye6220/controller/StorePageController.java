package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.ItemEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/store-page")
public class StorePageController {

    private static final int PAGE_LENGTH = 20;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public StorePageController(Configuration conf){
        this.configuration = conf;
        sessionFactory = configuration.configure().buildSessionFactory();
    }

    private Map<String, List<ItemEntity>> homePageItems;   // key: category  value: list of items

    @Autowired
    public void setHomePageItems(Map<String, List<ItemEntity>> items){
        this.homePageItems = items;
    }

    private List<String> homePageCategories;

    @Autowired
    public void setHomePageCategories(List<String> categories){
        this.homePageCategories = categories;
    }

    @GetMapping
    public String handleGet_HomePage(Model model) {
        model.addAttribute("homePageCategories", homePageCategories);
        model.addAttribute("homePageItems", homePageItems);
        return "home-page";
    }

    @GetMapping("/{category}")
    public String handleGet_CategoryPage(
            @PathVariable("category") String category,
            @RequestParam(name = "pid", defaultValue = "0") int pid,
            @RequestParam(name = "pageLength", defaultValue = "0") int pageLength,
            Model model){

        // use default page length
        if(pageLength == 0) pageLength = PAGE_LENGTH;

        // calculate offset
        int offset = pid * pageLength;

        try(Session session = sessionFactory.openSession()){

            // hql query items
            String hql = "From ItemEntity itemEntity WHERE itemEntity.category = :category";
            Query<ItemEntity> query = session.createQuery(hql, ItemEntity.class);
            query.setParameter("category", category);
            query.setFirstResult(offset);
            query.setMaxResults(pageLength);
            List<ItemEntity> items = query.list();
            model.addAttribute("items", items);
        }
        return "category-page";
    }

    @GetMapping("/search")
    public String handleGet_SearchPage(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "pid", defaultValue = "0") int pid,
            @RequestParam(name = "pageLength", defaultValue = "0") int pageLength,
            Model model){

        // use default page length
        if(pageLength == 0) pageLength = PAGE_LENGTH;

        // calculate offset
        int offset = pid * pageLength;

        try(Session session = sessionFactory.openSession()){

            // hql query items
            String hql = "From ItemEntity itemEntity WHERE itemEntity.name = :keyword";
            Query<ItemEntity> query = session.createQuery(hql, ItemEntity.class);
            query.setParameter("keyword", keyword);
            query.setFirstResult(offset);
            query.setMaxResults(pageLength);
            List<ItemEntity> items = query.list();
            model.addAttribute("items", items);
        }
        return "search-page";
    }

}
