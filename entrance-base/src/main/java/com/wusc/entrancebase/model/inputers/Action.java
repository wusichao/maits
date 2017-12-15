package com.wusc.entrancebase.model.inputers;

import java.io.Serializable;

public class Action implements Serializable{
    private String actionId;
    private String accountId;
    private String campaignId;
    private String channelId;
    private String creativeId;
    private String mediaId;
    private String ip;
    private String sessionId;
    private long requestTime;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionId='" + actionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", campaignId='" + campaignId + '\'' +
                ", channelId='" + channelId + '\'' +
                ", creativeId='" + creativeId + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", ip='" + ip + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", requestTime=" + requestTime +
                '}';
    }
}
