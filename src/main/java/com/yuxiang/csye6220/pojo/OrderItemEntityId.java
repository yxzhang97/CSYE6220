package com.yuxiang.csye6220.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItemEntityId {

    @Column(name = "fk_orderEntity_id")
    private int orderId;

    @Column(name = "fk_itemEntity_id")
    private int itemId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
