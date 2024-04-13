package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "OrderItems")
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemEntityId orderItemEntityId;

    @Column
    private Date dateCreated;

    @Column
    private Date dateLastModified;

    @Column
    private boolean valid;

    @Column
    private int amount;

    @Column
    private double unitPrice;

    @Column
    private double totalPrice;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_itemEntity_id")
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name = "fk_orderEntity_id")
    private OrderEntity orderEntity;

    public OrderItemEntityId getOrderItemEntityId() {
        return orderItemEntityId;
    }

    public void setOrderItemEntityId(OrderItemEntityId orderItemEntityId) {
        this.orderItemEntityId = orderItemEntityId;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
