package com.wusc.campaign.params;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * create by wusc on 2017/12/21
 */
public class ChannelParam {
    @Length(min =1 ,max =20)
    private String name;
    @NotNull
    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
