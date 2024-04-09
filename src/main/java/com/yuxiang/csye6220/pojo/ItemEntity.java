package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String sku;

    @Column
    private boolean valid;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private int inventory;

    @Column
    private String description;

    @Column
    private String manufacturer;

    @OneToMany
    private List<String> url2media;

    @OneToMany(mappedBy = "itemEntity")
    private List<CommentEntity> comments;

    @ManyToMany(mappedBy = "items")
    private List<OrderEntity> orders;

    @ManyToOne
    @JoinColumn(name = "fk_sellerEntity_id")
    private SellerEntity seller;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<String> getUrl2media() {
        return url2media;
    }

    public void setUrl2media(List<String> url2media) {
        this.url2media = url2media;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public SellerEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerEntity seller) {
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
