package com.yuxiang.csye6220.pojo;

public class SellerRegisterDTO {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateBasicInfoToSellerEntity(SellerEntity sellerEntity){
        if(name != null) sellerEntity.setName(name);
        if(password != null) sellerEntity.setPassword(password);
    }
}
