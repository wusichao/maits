package com.wusc.loganalyzes.analyzes;

import java.util.Date;

/**
 * create by wusc on 2017/12/27
 */
public class BaseReport {
    private long accountId;
    private long campaignId;
    private long channelId;
    private long creativeId;
    private long mediaId;
    private int imp;
    private int click;
    private Date creation;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(long creativeId) {
        this.creativeId = creativeId;
    }

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public int getImp() {
        return imp;
    }

    public void setImp(int imp) {
        this.imp = imp;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public void impIncr(){
        imp++;
    }

    public void clickIncr(){click++;}

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Override
    public String toString() {
        return "BaseReport{" +
                "accountId=" + accountId +
                ", campaignId=" + campaignId +
                ", channelId=" + channelId +
                ", creativeId=" + creativeId +
                ", mediaId=" + mediaId +
                ", imp=" + imp +
                ", click=" + click +
                '}';
    }
}
