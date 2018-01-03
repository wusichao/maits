package com.wusc.entrancebase.cache;

import com.wusc.campaign.model.Account;
import com.wusc.campaign.model.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * create by wusc on 2017/12/25
 */
@Component
public class LocalCache {
    private final static Logger log= LoggerFactory.getLogger(LocalCache.class);
    private LocalCache(){

        }
    private volatile HashMap<Long, Account> accountMap = new HashMap<>();
    private volatile HashMap<Long, Campaign> campaignMap = new HashMap<>();

    public boolean addAccount(Account account){
        accountMap.put(account.getId(),account);
        return true;
    }
    public boolean deleteAccount(Long key){
        if(accountExist(key)){
            accountMap.remove(key);
            return true;
        }
        return false;
    }
    public boolean updateAccount(Account account){
        if(accountExist(account.getId())){
            accountMap.put(account.getId(),account);
            return true;
        }
        return false;
    }

    public boolean addCampaign(Campaign campaign){
        campaignMap.put(campaign.getId(),campaign);
        return true;
    }
    public boolean deleteCampaign(Long key){
        if(campaignExist(key)){
            campaignMap.remove(key);
            return true;
        }
        return false;
    }
    public boolean updateCampaign(Campaign campaign){
        if(campaignExist(campaign.getId())){
            campaignMap.put(campaign.getId(),campaign);
            return true;
        }
        return false;
    }

    public boolean accountExist(Long key){
        if(accountMap.get(key)==null){
            return false;
        }
        return true;
    }
    public boolean campaignExist(Long key){
        if(campaignMap.get(key)==null){
        return false;
        }
        return true;
    }
}
