package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "cartEntity")
    private UserEntity userEntity;

    @Column
    private Date dateLastModified;

    @Column
    private double totalPrice;

    @Column
    private int numOfItems;

    @OneToMany(mappedBy = "cartEntity", fetch = FetchType.EAGER)
    private List<CartItemEntity> cartItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public void updateTotalPrice(){
        totalPrice = 0;
        for(CartItemEntity c : cartItems){
            if(c.isValid())
                totalPrice += c.getTotalPrice();
        }
    }

    public void updateNumOfItems(){
        numOfItems = 0;
        for(CartItemEntity c : cartItems){
            if(c.isValid())
                numOfItems += c.getAmount();
        }
    }

    public void updateLastModifiedDate(){
        dateLastModified = new Date();
    }
}
