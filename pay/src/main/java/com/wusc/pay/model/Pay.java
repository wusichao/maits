package com.wusc.pay.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wusichao
 * @since 2018-01-19
 */
public class Pay {

	@TableId(value="id", type= IdType.UUID)
	private String id;
	private String orderId;
	private String payId;
    /**
     * 0:no pay,1:payed
     */
	private Integer status;
	private Date creation;
	private Date lastModified;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	@Override
	public String toString() {
		return "Pay{" +
			"id=" + id +
			", orderId=" + orderId +
			", status=" + status +
			", creation=" + creation +
			", lastModified=" + lastModified +
			"}";
	}

	public Pay(String orderId, String payId, Integer status) {
		this.orderId = orderId;
		this.payId = payId;
		this.status = status;
	}

	public Pay() {
	}
}
