package com.yuxiang.csye6220.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class CartItemEntityId {

    @Column(name = "fk_cartEntity_id")
    private int cartId;

    @Column(name = "fk_itemEntity_id")
    private int itemId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
