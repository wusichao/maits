package com.wusc.campaign.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;

import java.beans.Transient;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wusichao
 * @since 2017-12-22
 */
public class Account{

	@TableId(value="id", type= IdType.AUTO)
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

	@Override
	public String toString() {
		return "AccountPOJO{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", type=" + type +
				", companyName='" + companyName + '\'' +
				", vertical='" + vertical + '\'' +
				", webSite='" + webSite + '\'' +
				", contact='" + contact + '\'' +
				", cellphone='" + cellphone + '\'' +
				", licensePath='" + licensePath + '\'' +
				", status='" + status + '\'' +
				", creation=" + creation +
				", lastModified=" + lastModified +
				", removed=" + removed +
				'}';
	}
}
