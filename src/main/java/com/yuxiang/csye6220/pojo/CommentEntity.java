package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String text;

    @OneToMany
    @JoinColumn(name = "fk_commentEntity_id")
    private List<String> url2media;

    @OneToOne
    @JoinColumn(name = "fk_orderItemEntity_id")
    private OrderItemEntity orderItemEntity;

    @ManyToOne
    @JoinColumn(name = "fk_itemEntity_id")
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name = "fk_userEntity_id")
    private UserEntity userEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getUrl2media() {
        return url2media;
    }

    public void setUrl2media(List<String> url2media) {
        this.url2media = url2media;
    }

    public OrderItemEntity getOrderItemEntity() {
        return orderItemEntity;
    }

    public void setOrderItemEntity(OrderItemEntity orderItemEntity) {
        this.orderItemEntity = orderItemEntity;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
