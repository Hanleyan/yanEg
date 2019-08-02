package com.entity.power.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 订单表
 * @author hanley
 * @date 2019/07/29 23:15
 * 风萧萧兮易水寒
 */

@Entity
@Table(name="orders")
public class Orders {
	
	@Id
    @Basic
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//order_ID

    @Column(name="order_no", columnDefinition="varchar (50)")
    private String orderNo;//订单编号

    @Column(name="user_id", columnDefinition="int (11)")
    private Integer userId;//用户id
    
    @Column(name="user_name", columnDefinition="varchar (50)")
    private String userName;//用户昵称
    
    @Column(name="user_address", columnDefinition="varchar (500)")
    private String userAddress;//配送地址
    
    @Column(name="orders_price", columnDefinition="decimal (10,2)")
    private BigDecimal ordersPrice;//此订单实付总金额(元)
    
    @Column(name="order_source", columnDefinition="int (2)")
    private Integer orderSource;//订单来源 (EnumOrderSource 来源：1 立即下单 、2 购物车下单) 
    
    @Column(name="order_pay_type", columnDefinition="int (2)")
    private Integer orderPayType;//订单支付方式 (EnumOrderPay ：1 未支付 、2 微信支付、3支付宝支付、4银联支付、5其他) 
    
    @Column(name="order_pay_time", columnDefinition="datetime")
    private Date orderPayTime;//支付时间
    
    @Column(name="create_time", columnDefinition="datetime",updatable=false)
    private Date createTime;//创建日期

    @Column(name="update_time", columnDefinition="datetime",updatable=true)
    private Date updateTime;//修改时间

    @Column(name="del_flag", columnDefinition="Boolean")
    private Boolean delFlag;// 是否删除 true 删除  false 未删除  数据库  1 删除 0 未删除
    
    @Transient
    private String orderSourceDesc; //订单来源描述
    
    @Transient
    private String orderPayTypeDesc; //订单支付描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getOrdersPrice() {
		return ordersPrice;
	}

	public void setOrdersPrice(BigDecimal ordersPrice) {
		this.ordersPrice = ordersPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	public Integer getOrderPayType() {
		return orderPayType;
	}

	public void setOrderPayType(Integer orderPayType) {
		this.orderPayType = orderPayType;
	}

	public String getOrderSourceDesc() {
		return orderSourceDesc;
	}

	public void setOrderSourceDesc(String orderSourceDesc) {
		this.orderSourceDesc = orderSourceDesc;
	}

	public String getOrderPayTypeDesc() {
		return orderPayTypeDesc;
	}

	public void setOrderPayTypeDesc(String orderPayTypeDesc) {
		this.orderPayTypeDesc = orderPayTypeDesc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getOrderPayTime() {
		return orderPayTime;
	}

	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	
    
    

}
