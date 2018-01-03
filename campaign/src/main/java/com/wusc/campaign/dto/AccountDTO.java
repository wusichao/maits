package com.wusc.campaign.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * create by wusc on 2017/12/19
 */
public class AccountDTO {
    @Email
    private String email;
    @Length(min = 6,max = 12)
    private String password;
    @Length(min = 1,max = 100)
    private String companyName;
    @Length(min = 1,max = 20)
    private String vertical;
    @Length(min =5 ,max = 1024)
    private String webSite;
    @Length(min =1 ,max =20 )
    private String contact;
    @Length(min =11 ,max = 11)
    private String cellphone;
    @Length(min =5 ,max = 1024)
    private String licensePath;

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
}
