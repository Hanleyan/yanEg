package com.test.jdbc;

import java.util.Date;

public class SearchBean {
	
	private int id;//	int	11	0	0	-1	0	0	0		0					-1	0
	private String customerAddress	;//varchar	200	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
	private String customerMail;//	varchar	200	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
	private String customerName;//	varchar	200	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
	private String customerPhone;//	varchar	200	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
	private Date createDate;//	datetime	0	0	-1	0	0	0	0		0					0	0
	private Boolean delFlag;//	tinyint	1	0	-1	0	0	0	0		0					0	0
	private Date pdateTime;//	datetime	0	0	-1	0	0	0	0		0					0	0
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Boolean getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	public Date getPdateTime() {
		return pdateTime;
	}
	public void setPdateTime(Date pdateTime) {
		this.pdateTime = pdateTime;
	}

	
	

}
