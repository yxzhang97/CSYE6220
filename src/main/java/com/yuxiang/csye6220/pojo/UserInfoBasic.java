package com.yuxiang.csye6220.pojo;

public class UserInfoBasic {

    private String nickName;

    private String firstName;

    private String middleName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;

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
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void getBasicInfoFromUserEntity(UserEntity userEntity){
        nickName = userEntity.getNickName();
        firstName = userEntity.getFirstName();
        middleName = userEntity.getMiddleName();
        lastName = userEntity.getLastName();
        emailAddress = userEntity.getEmailAddress();
        phoneNumber = userEntity.getPhoneNumber();
    }

    public void updateBasicInfoToUserEntity(UserEntity userEntity){
        if(nickName != null) userEntity.setNickName(nickName);
        if(firstName != null) userEntity.setFirstName(firstName);
        if(middleName != null) userEntity.setMiddleName(middleName);
        if(lastName != null) userEntity.setLastName(lastName);
        if(emailAddress != null) userEntity.setEmailAddress(emailAddress);
        if(phoneNumber != null) userEntity.setPhoneNumber(phoneNumber);
    }
}
