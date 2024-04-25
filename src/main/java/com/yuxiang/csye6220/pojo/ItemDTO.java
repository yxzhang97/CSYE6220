package com.yuxiang.csye6220.pojo;

import jakarta.persistence.Column;

public class ItemDTO {

    private String sku;

    private boolean valid;

    private String name;

    private double price;

    private int inventory;

    private String description;

    private String manufacturer;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void updateInfoToItemEntity(ItemEntity itemEntity){
        itemEntity.setSku(sku);
        //itemEntity.setValid(valid);
        itemEntity.setName(name);
        itemEntity.setPrice(price);
        itemEntity.setInventory(inventory);
        itemEntity.setDescription(description);
        itemEntity.setManufacturer(manufacturer);
    }
}
