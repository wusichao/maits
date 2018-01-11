package com.wusc.apigateway.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * create by wusc on 2018/1/9
 */
public class AccountToken {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private Integer type;
    private String companyName;
    private String vertical;
    private String webSite;
    private String contact;
    private String cellphone;
    private String licensePath;
    private String status;
    private Date creation;
    private Date lastModified;
    private Boolean removed;
    private long tokenTTL;
    private long refreshTTL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public long getTokenTTL() {
        return tokenTTL;
    }

    public void setTokenTTL(long tokenTTL) {
        this.tokenTTL = tokenTTL;
    }

    public long getRefreshTTL() {
        return refreshTTL;
    }

    public void setRefreshTTL(long refreshTTL) {
        this.refreshTTL = refreshTTL;
    }
}
