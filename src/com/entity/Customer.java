package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer implements Serializable{
	
	/**
	 * Customer
	 * @author Hanley
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Basic
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;//ID
	
	@Column(name="customerAddress", columnDefinition="varchar (200)")
	private String customerAddress;//
	
	@Column(name="customerMail", columnDefinition="varchar (200)")
	private String customerMail;//
	
	@Column(name="customerName", columnDefinition="varchar (200)")
	private String customerName;//
	
	@Column(name="customerPhone", columnDefinition="varchar (200)")
	private String customerPhone;//
	
	@Column(name="createDate", columnDefinition="datetime")
	private Date createDate;//创建日期
	
	@Column(name="updateTime", columnDefinition="datetime")
	private Date updateTime;
	
	@Column(name="delFlag")
	private Boolean delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Customer() {
		super();
	}

	public Customer(Integer id, String customerAddress, String customerMail,
			String customerName, String customerPhone, Date createDate,
			Date updateTime, Boolean delFlag) {
		super();
		this.id = id;
		this.customerAddress = customerAddress;
		this.customerMail = customerMail;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.createDate = createDate;
		this.updateTime = updateTime;
		this.delFlag = delFlag;
	}
	


}
