package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date dateCreated;

    @Column
    private Date dateLastModified;

    @Column
    private boolean valid;

    @Column
    private double totalPrice;

    @Transient
    private int numOfItems;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_userEntity_id")
    private UserEntity owner;

    @ManyToMany
    @JoinTable(
            joinColumns = { @JoinColumn(name = "fk_orderEntity_id") },
            inverseJoinColumns = { @JoinColumn(name = "fk_itemEntity_id") }
    )
    private List<ItemEntity> items;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void updateTotalPrice(double price){
        this.totalPrice += price;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void updateNumOfItems() {
        this.numOfItems = this.items.size();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
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
}
