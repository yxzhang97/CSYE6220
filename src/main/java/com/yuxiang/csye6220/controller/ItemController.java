package com.yuxiang.csye6220.controller;

import com.yuxiang.csye6220.pojo.ItemDTO;
import com.yuxiang.csye6220.pojo.ItemEntity;
import com.yuxiang.csye6220.pojo.OrderItemEntity;
import com.yuxiang.csye6220.pojo.SellerEntity;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ApplicationContext applicationContext;

    private Configuration configuration;

    private SessionFactory sessionFactory;

    @Autowired
    public ItemController(Configuration configuration){
        this.configuration = configuration;
        this.sessionFactory = this.configuration.buildSessionFactory();
    }

    @GetMapping("/all")
    public String handleGet_itemAll(@SessionAttribute(name = "seller", required = false) SellerEntity sellerEntity, Model model){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        model.addAttribute("items", sellerEntity.getItems());
        return "item-all";
    }

    @GetMapping("/{itemId}")
    public String handleGet_item(@PathVariable(name = "itemId") int itemId,
                                 @SessionAttribute(name = "seller", required = false)SellerEntity sellerEntity,
                                 Model model
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        String hql = "FROM ItemEntity itemEntity WHERE itemEntity.id = :itemId";
        try(Session session = sessionFactory.openSession()){
            Query<ItemEntity> query = session.createQuery(hql, ItemEntity.class);
            query.setParameter("itemId", itemId);
            ItemEntity itemEntity = query.getSingleResultOrNull();
            model.addAttribute("itemEntity", itemEntity);
        }
        return "item-detail";
    }

    @GetMapping("/modify/{itemId}")
    public String handleGet_itemModify(@PathVariable(name = "itemId") int itemId,
                                       @SessionAttribute(name = "seller", required = false)SellerEntity sellerEntity,
                                       Model model
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        String hql = "FROM ItemEntity itemEntity WHERE itemEntity.id = :itemId";
        try(Session session = sessionFactory.openSession()){
            Query<ItemEntity> query = session.createQuery(hql, ItemEntity.class);
            query.setParameter("itemId", itemId);
            ItemEntity itemEntity = query.getSingleResultOrNull();
            model.addAttribute("itemEntity", itemEntity);
        }
        return "item-modify";
    }

    @PostMapping("/modify/{itemId}")
    public String handlePatch_itemModify(@PathVariable(name = "itemId") int itemId,
                                         @SessionAttribute(name = "seller", required = false)SellerEntity sellerEntity,
                                         @ModelAttribute(name = "itemDTO") ItemDTO itemDTO
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        for(ItemEntity itemEntity : sellerEntity.getItems()) {
            if(itemEntity.getId() == itemId) {
                try (Session session = sessionFactory.openSession()) {
                    itemDTO.updateInfoToItemEntity(itemEntity);
                    Transaction transaction = session.beginTransaction();
                    session.merge(itemEntity);
                    transaction.commit();
                }
            }
        }
        return "redirect:/item/" + itemId;
    }

    @GetMapping("/modify/{itemId}/media")
    public String handleGet_itemModifyMedia(
            @PathVariable(name = "itemId") int itemId,
            @SessionAttribute(name = "seller", required = false)SellerEntity sellerEntity,
            Model model
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        try(Session session = sessionFactory.openSession()){
            String hql = "FROM ItemEntity itemEntity WHERE itemEntity.id = :itemId";
            Query<ItemEntity> query = session.createQuery(hql, ItemEntity.class);
            query.setParameter("itemId", itemId);
            ItemEntity itemEntity = query.getSingleResultOrNull();
            List<String> url2medias = itemEntity.getUrl2media();
            model.addAttribute("itemEntity", itemEntity);
            model.addAttribute("url2medias", url2medias);
        }
        return "item-modify-media";
    }

    @GetMapping("/modify/{itemId}/media/delete")
    public String handleGet_itemModifyMediaDelete(
            @PathVariable(name = "itemId") int itemId,
            @SessionAttribute(name = "seller", required = false)SellerEntity sellerEntity,
            Model model
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        for(ItemEntity itemEntity : sellerEntity.getItems()){
            if(itemEntity.getId() == itemId){
                model.addAttribute("itemEntity", itemEntity);
                model.addAttribute("url2medias", itemEntity.getUrl2media());
                break;
            }
        }
        return "item-modify-media-delete";
    }

    @RequestMapping("/modify/{itemId}/media/*/{url2media}")
    public String handleDelete_itemModifyMediaDelete(
            @PathVariable(name = "itemId") int itemId,
            @SessionAttribute(name = "seller", required = false)SellerEntity sellerEntity,
            @PathVariable(name = "url2media") String url2media,
            Model model
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        url2media = "/img/" + url2media;
        try(Session session = sessionFactory.openSession()){

            for(ItemEntity itemEntity : sellerEntity.getItems()){
                if(itemEntity.getId() == itemId){
                    List<String> urls = itemEntity.getUrl2media();
                    urls.remove(url2media);
                    Transaction transaction = session.beginTransaction();
                    session.merge(itemEntity);
                    transaction.commit();
                    model.addAttribute("itemEntity", itemEntity);
                    model.addAttribute("url2medias", itemEntity.getUrl2media());
                }
            }
        }
        return "redirect:/item/modify/" + itemId + "/media/delete";
    }

    final String IMG = "./src/main/webapp/img/";

    @PostMapping("/modify/{itemId}/media")
    public String handlePost_itemModifyMedia(
            @PathVariable(name = "itemId") int itemId,
            @SessionAttribute(name = "seller", required = false)SellerEntity sellerEntity,
            @RequestParam(name = "file") MultipartFile file,
            Model model
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        if(file.isEmpty())
            return "redirect:/item/modify/" + itemId + "/media";

        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(IMG + file.getOriginalFilename());
            Files.write(path, bytes);

            try(Session session = sessionFactory.openSession()){

                for(ItemEntity itemEntity : sellerEntity.getItems()){
                    if(itemEntity.getId() == itemId){
                        if(itemEntity.getUrl2media() == null)
                            itemEntity.setUrl2media(new LinkedList<>());
                        List<String> urls = itemEntity.getUrl2media();
                        urls.add("/img/" + file.getOriginalFilename());
                        Transaction transaction = session.beginTransaction();
                        session.merge(itemEntity);
                        transaction.commit();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/item/modify/" + itemId + "/media";
    }

    @GetMapping("/newItem")
    public String handleGet_newItem(@SessionAttribute(name = "seller", required = false) SellerEntity sellerEntity){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        return "item-new";
    }

    @PostMapping("/newItem")
    public String handlePost_newItem(
            @SessionAttribute(name = "seller", required = false) SellerEntity sellerEntity,
            @ModelAttribute(name = "itemDTO") ItemDTO itemDTO,
            BindingResult bindingResult
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(bindingResult.hasErrors())
            return "item-error";

        ItemEntity itemEntity = applicationContext.getBean("itemEntity_prototype", ItemEntity.class);
        itemDTO.updateInfoToItemEntity(itemEntity);
        itemEntity.setValid(true);
        itemEntity.setSellerEntity(sellerEntity);
        sellerEntity.getItems().add(itemEntity);
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(itemEntity);
            transaction.commit();
        }
        return "redirect:/item/all";
    }

    @RequestMapping("/delete/{itemId}")
    public String handlePost_newItem(
            @SessionAttribute(name = "seller", required = false) SellerEntity sellerEntity,
            @PathVariable(name = "itemId") int itemId
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        for(ItemEntity itemEntity : sellerEntity.getItems()) {
            if(itemEntity.getId() == itemId) {
                try (Session session = sessionFactory.openSession()) {
                    itemEntity.setValid(false);
                    Transaction transaction = session.beginTransaction();
                    session.merge(itemEntity);
                    transaction.commit();
                }
            }
        }
        return "redirect:/item/all";
    }

    @GetMapping("/{itemId}/orders")
    public String handleGet_itemOrder(
            @SessionAttribute(name = "seller", required = false) SellerEntity sellerEntity,
            @PathVariable(name = "itemId") int itemId,
            Model model
    ){
        // check login state
        if(sellerEntity == null)
            return "redirect:/login/seller";

        if(!isAuthenticated(itemId, sellerEntity))
            return "unauthenticated";

        String hql = "FROM OrderItemEntity orderItemEntity WHERE orderItemEntity.itemEntity.id = :itemId";
        try(Session session = sessionFactory.openSession()){
            Query<OrderItemEntity> query = session.createQuery(hql, OrderItemEntity.class);
            query.setParameter("itemId", itemId);
            List<OrderItemEntity> orderItems = query.list();
            model.addAttribute("orderItems", orderItems);
        }
        return "item-orders";
    }

    private boolean isAuthenticated(int itemId, SellerEntity sellerEntity){
        for(ItemEntity itemEntity : sellerEntity.getItems())
            if(itemEntity.getId() == itemId)
                return true;
        return false;
    }
}
