package com.yuxiang.csye6220.pojo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Addresses")
public class AddressEntity {

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
    private String state;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String aptNumber;

    @Column
    private String zipCode;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void updateDateLastModified(){
        this.dateLastModified = new Date();
    }
}
