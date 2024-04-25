package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Sellers")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private Date dateCreated;

    @Column
    private Date dateLastModified;

    @Column
    private boolean valid;

    @Column
    private String password;

    @OneToMany(mappedBy = "sellerEntity", fetch = FetchType.EAGER)
    private List<ItemEntity> items;

    @OneToMany
    @JoinColumn(name = "fk_sellerEntity_id")
    private List<OrderItemEntity> orderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public void updateCreatedDate(){
        dateCreated = new Date();
    }

    public void updateLastModifiedDate(){
        dateLastModified = new Date();
    }
}
