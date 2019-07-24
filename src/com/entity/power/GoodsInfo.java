package com.entity.power;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息表
 * @author hanley
 * @date 2019/07/24 21:15
 * 风萧萧兮易水寒
 */
@Entity
@Table(name="goods_info")
public class GoodsInfo {

    @Id
    @Basic
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//商品编号ID

    @Column(name="goods_type_id", columnDefinition="int (5)")
    private Integer goodsTypeId;//商品类型编号

    @Column(name="goods_name", columnDefinition="varchar (255)")
    private String goodsName;//商品名称

    @Column(name="storage", columnDefinition="int (15)")
    private Integer storage;//商品库存量

    @Column(name="goods_amount", columnDefinition="int (15)")
    private Integer goodsAmout;//商品供货量

    @Column(name="goods_price", columnDefinition="decimal (10,2)")
    private BigDecimal goodsPrice;//商品单价(元)

    @Column(name="goods_vip_price", columnDefinition="decimal (10,2)")
    private BigDecimal goodsVipPrice;//商品会员单价(元)

    @Column(name="goods_discount", columnDefinition="decimal (10,2)")
    private BigDecimal goodsDiscount;//折扣(元)

    @Column(name="goods_sale_num", columnDefinition="int (15)")
    private Integer goodsSaleNum;//成交量

    @Column(name="goods_description", columnDefinition="varchar (255)")
    private String goodsDescription;//商品简介

    @Column(name="goods_look_amount", columnDefinition="int (15)")
    private Integer goodsLookAmount;//商品浏览次数

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

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getGoodsAmout() {
        return goodsAmout;
    }

    public void setGoodsAmout(Integer goodsAmout) {
        this.goodsAmout = goodsAmout;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public BigDecimal getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(BigDecimal goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public Integer getGoodsSaleNum() {
        return goodsSaleNum;
    }

    public void setGoodsSaleNum(Integer goodsSaleNum) {
        this.goodsSaleNum = goodsSaleNum;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public Integer getGoodsLookAmount() {
        return goodsLookAmount;
    }

    public void setGoodsLookAmount(Integer goodsLookAmount) {
        this.goodsLookAmount = goodsLookAmount;
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
}
