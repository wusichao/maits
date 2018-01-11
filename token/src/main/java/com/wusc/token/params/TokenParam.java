package com.wusc.token.params;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * create by wusc on 2018/1/8
 */
public class TokenParam {
    @Email
    private String account;
    @Length(min = 6,max = 12)
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
