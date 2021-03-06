package com.wusc.pay.pojo;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wusichao
 * @since 2018-01-19
 */
public class UserOrder {

	private String id;
	private Long price;
	private Integer status;
	private Date creation;
	private Date lastModified;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	@Override
	public String toString() {
		return "UserOrder{" +
			"id=" + id +
			", price=" + price +
			", status=" + status +
			", creation=" + creation +
			", lastModified=" + lastModified +
			"}";
	}

	public UserOrder() {
	}

	public UserOrder(String id, Long price, Date creation) {
		this.id = id;
		this.price = price;
		this.creation = creation;
	}
}
