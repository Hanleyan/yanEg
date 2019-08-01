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

/**
 * 订单商品记录表
 * @author hanley
 * @date 2019/07/29 23:23
 * 风萧萧兮易水寒
 */

@Entity
@Table(name="orders_goods_record")
public class OrderGoodsRecords {
	
	@Id
    @Basic
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//订单商品记录表ID

    @Column(name="order_id", columnDefinition="int (11)")
    private Integer orderId;//订单id

    @Column(name="goods_id", columnDefinition="int (11)")
    private Integer goodsId;//商品id
    
    @Column(name="goods_name", columnDefinition="varchar (255)")
    private String goodsName;//商品名称
    
    @Column(name="goods_price", columnDefinition="decimal (10,2)")
    private BigDecimal goodsPrice;//商品单价(元)

    @Column(name="create_time", columnDefinition="datetime",updatable=false)
    private Date createTime;//创建日期（入库日期）

    @Column(name="update_time", columnDefinition="datetime",updatable=true)
    private Date updateTime;//修改时间

    @Column(name="del_flag", columnDefinition="Boolean")
    private Boolean delFlag;// 是否删除 true 删除  false 未删除  数据库  1 删除 0 未删除

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
    
    
	

}
