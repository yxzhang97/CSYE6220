package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CartItems")
public class CartItemEntity {

    @EmbeddedId
    private CartItemEntityId cartItemEntityId;

    @Transient
    private int cartId;

    @Transient
    private int itemId;

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
    @JoinColumn(name = "fk_cartEntity_id")
    private CartEntity cartEntity;

    public CartItemEntityId getCartItemEntityId() {
        return cartItemEntityId;
    }

    public void setCartItemEntityId(CartItemEntityId cartItemEntityId) {
        this.cartItemEntityId = cartItemEntityId;
        cartId = cartItemEntityId.getCartId();
        itemId = cartItemEntityId.getItemId();
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
        itemId = itemEntity.getId();
        cartItemEntityId.setItemId(itemId);
    }

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
        cartId = cartEntity.getId();
        cartItemEntityId.setCartId(cartId);
    }

    public double updateTotalPrice(){
        totalPrice = unitPrice * amount;
        return totalPrice;
    }

    public double updateTotalPrice(double t){
        setTotalPrice(t);
        return totalPrice;
    }

    public Date updateDateCreate(){
        this.dateCreated = new Date();
        return this.dateCreated;
    }

    public Date updateDateLastModified(){
        this.dateLastModified = new Date();
        return this.dateLastModified;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
        cartItemEntityId.setCartId(cartId);
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
        cartItemEntityId.setItemId(itemId);
    }
}
