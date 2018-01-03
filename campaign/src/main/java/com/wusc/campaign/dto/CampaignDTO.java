package com.wusc.campaign.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * create by wusc on 2017/12/21
 */
public class CampaignDTO {
    @NotNull
    private Long accountId;
    @Length(min = 1,max=20)
    private String name;
    @NotNull
    private Date beginDate;
    @NotNull
    private Date endDate;
    @Range(min=1,max=999999999)
    private Long cost;
    @NotEmpty
    private String costType;
    @Range(min=1,max=999999999)
    private Long unitPrice;
    @NotEmpty
    private String targetUrl;
    @NotEmpty
    private String channelIds;
    @NotEmpty
    private String creativeIds;
    private String mediaIds;

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds;
    }

    public String getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(String mediaIds) {
        this.mediaIds = mediaIds;
    }

    public String getCreativeIds() {
        return creativeIds;
    }

    public void setCreativeIds(String creativeIds) {
        this.creativeIds = creativeIds;
    }
}
