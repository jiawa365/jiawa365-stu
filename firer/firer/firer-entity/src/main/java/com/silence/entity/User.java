package com.silence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by silence on 2018/2/27.
 */
@Entity(name = "t_user")
public class User {

    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_pass")
    private String userPass;

    private String email;
    private String phone;
    private Integer status;
    @Column(name = "valicode")
    private String valiCode;
    @Column(name = "create_time")
    private String createTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getValiCode() {
        return valiCode;
    }

    public void setValiCode(String valiCode) {
        this.valiCode = valiCode;
    }

    public User() {
    }

    public User(String userName, String userPass, String email, String phone, Integer status, String valiCode) {
        this.userName = userName;
        this.userPass = userPass;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.valiCode = valiCode;
    }

    public interface Status {
        public static final Integer DISABLED = -1;//已禁用
        public static final Integer CREATED = 0;//已注册，待激活
        public static final Integer ENABLED = 1;//可用
    }

}
