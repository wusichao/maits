package com.wusc.campaign.pojo;

import com.wusc.campaign.model.Account;

/**
 * create by wusc on 2018/1/9
 */
public class AccountToken extends Account{
    private long tokenTTL;
    private long refreshTTL;

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
