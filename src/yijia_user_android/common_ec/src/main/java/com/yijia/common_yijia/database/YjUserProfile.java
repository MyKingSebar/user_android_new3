package com.yijia.common_yijia.database;

import com.alibaba.fastjson.JSONObject;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity(nameInDb = "yj_user_profile")
public class YjUserProfile {


    @Id
    Long id = null;
    String yjtk = null;
    String nickname = null;
    String realName = null;
    String phone = null;
    String cardNo = null;
    String cardImage = null;
    String gender = null;
    String birthday = null;
    int userStatus = 0;
    int isComplete = 0;
    int isCertification = 0;
    int inviterId = 0;
    String createdTime = null;
    String modifiedTime = null;
    @Generated(hash = 1218374160)
    public YjUserProfile(Long id, String yjtk, String nickname, String realName,
            String phone, String cardNo, String cardImage, String gender,
            String birthday, int userStatus, int isComplete, int isCertification,
            int inviterId, String createdTime, String modifiedTime) {
        this.id = id;
        this.yjtk = yjtk;
        this.nickname = nickname;
        this.realName = realName;
        this.phone = phone;
        this.cardNo = cardNo;
        this.cardImage = cardImage;
        this.gender = gender;
        this.birthday = birthday;
        this.userStatus = userStatus;
        this.isComplete = isComplete;
        this.isCertification = isCertification;
        this.inviterId = inviterId;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }
    @Generated(hash = 900997910)
    public YjUserProfile() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getYjtk() {
        return this.yjtk;
    }
    public void setYjtk(String yjtk) {
        this.yjtk = yjtk;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCardNo() {
        return this.cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getCardImage() {
        return this.cardImage;
    }
    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public int getUserStatus() {
        return this.userStatus;
    }
    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
    public int getIsComplete() {
        return this.isComplete;
    }
    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
    public int getIsCertification() {
        return this.isCertification;
    }
    public void setIsCertification(int isCertification) {
        this.isCertification = isCertification;
    }
    public int getInviterId() {
        return this.inviterId;
    }
    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
    }
    public String getCreatedTime() {
        return this.createdTime;
    }
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
    public String getModifiedTime() {
        return this.modifiedTime;
    }
    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }


    
}
