package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nickName;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String LastName;

    @Column
    private String emailAddress;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column
    private Date dateCreated;

    @Column
    private Date dateLastModified;

    @Column
    private boolean valid;

    @OneToOne
    @JoinColumn(name = "fk_addressEntity_id")
    private AddressEntity defaultDeliveryAddress;

    @OneToMany
    @JoinColumn(name = "fk_userEntity_id")
    @OrderColumn(name="followupNumber")
    private List<AddressEntity> deliveryAddresses;

    @OneToMany(mappedBy = "owner")
    private List<OrderEntity> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public AddressEntity getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(AddressEntity defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

    public List<AddressEntity> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(List<AddressEntity> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
