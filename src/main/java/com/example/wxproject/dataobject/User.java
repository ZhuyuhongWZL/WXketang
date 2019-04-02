package com.example.wxproject.dataobject;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
public class User {

    @Id
    private Integer usrId;

    private String usrName;

    private String openId;

    private Integer roleId;

    private String phone;

    public User(){}

    public User(Integer usrId, String usrName, String openId, Integer roleId) {
        this.usrId = usrId;
        this.usrName = usrName;
        this.openId = openId;
        this.roleId = roleId;
    }

    public String getOpenId() {
        return openId;
    }

    public Integer getUserId() {
        return usrId;
    }

    public void setUserId(Integer userId) {
        this.usrId = userId;
    }

    public String getUserName() {
        return usrName;
    }

    public void setUserName(String userName) {
        this.usrName = userName;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
