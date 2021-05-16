/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhdbd.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author donguyen
 */
public class AccountDTO implements Serializable {

    private String email;
    private String password;
    private String name;
    private int roleId;
    private String verifyCode;
    private String accountStatusId;
    private Date insDate;

    public AccountDTO(String email, String password, String name, int roleId, String verifyCode, String accountStatusId, Date insDate, boolean isDeleted) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
        this.verifyCode = verifyCode;
        this.accountStatusId = accountStatusId;
        this.insDate = insDate;
        this.isDeleted = isDeleted;
    }

    public AccountDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(String accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    private boolean isDeleted;
}
