package com.wusc.loganalyzes.analyzes;

public class LogField {

	private String actionId;
	
	private String actionPreId;
	
	private String ip;
	
	private String url;
	
	private ActionType actionType;
	
	private java.util.Date requestTime;
	
	private String userId;
	
	private long accountId;
	
	private int geoId;

	private long campaignId;
	
	private long channelId;
	
	private long creativeId;
	
	private long mediaId;
	
	private String referer;

	private String sessionId;
	
	/**
	 * 其他参数
	 */
	private String other;
	
	/**
	 * 转化日志，转化点Id
	 */
	private long cvtId;
	
	//转化日志，点击请求时间
	private java.util.Date clickTime;
	

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}

	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	/**
	 * @return the actionPreId
	 */
	public String getActionPreId() {
		return actionPreId;
	}

	/**
	 * @param actionPreId the actionPreId to set
	 */
	public void setActionPreId(String actionPreId) {
		this.actionPreId = actionPreId;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the actionType
	 */
	public ActionType getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the requestTime
	 */
	public java.util.Date getRequestTime() {
		return requestTime;
	}

	/**
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(java.util.Date requestTime) {
		this.requestTime = requestTime;
	}
	
	public java.util.Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(java.util.Date clickTime) {
		this.clickTime = clickTime;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the campaignId
	 */
	public long getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId the campaignId to set
	 */
	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * @return the channelId
	 */
	public long getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the creativeId
	 */
	public long getCreativeId() {
		return creativeId;
	}

	/**
	 * @param creativeId the creativeId to set
	 */
	public void setCreativeId(long creativeId) {
		this.creativeId = creativeId;
	}

	/**
	 * @return the cvtId
	 */
	public long getCvtId() {
		return cvtId;
	}

	/**
	 * @param cvtId the cvtId to set
	 */
	public void setCvtId(long cvtId) {
		this.cvtId = cvtId;
	}
	
	/**
	 * @return the geoId
	 */
	public int getGeoId() {
		return geoId;
	}
	
	/**
	 * @param geoId the geoId to set
	 */
	public void setGeoId(int geoId) {
		this.geoId = geoId;
	}

	/**
	 * @return the mediaId
	 */
	public long getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LogField logField = (LogField) o;

		if (accountId != logField.accountId) return false;
		if (campaignId != logField.campaignId) return false;
		if (channelId != logField.channelId) return false;
		if (creativeId != logField.creativeId) return false;
		if (mediaId != logField.mediaId) return false;
		return actionType == logField.actionType;
	}

	@Override
	public int hashCode() {
		int result = actionType != null ? actionType.hashCode() : 0;
		result = 31 * result + (int) (accountId ^ (accountId >>> 32));
		result = 31 * result + (int) (campaignId ^ (campaignId >>> 32));
		result = 31 * result + (int) (channelId ^ (channelId >>> 32));
		result = 31 * result + (int) (creativeId ^ (creativeId >>> 32));
		result = 31 * result + (int) (mediaId ^ (mediaId >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "LogField{" +
				"actionId='" + actionId + '\'' +
				", accountId=" + accountId +
				", campaignId=" + campaignId +
				", channelId=" + channelId +
				", creativeId=" + creativeId +
				", mediaId=" + mediaId +
				'}';
	}
}
