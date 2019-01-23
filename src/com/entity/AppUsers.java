package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="app_users")
public class AppUsers {
    /**
     * 用户表
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Basic
    @Column(name="app_users_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//用户ID编号

    @Column(name="user_name", unique=true, columnDefinition="varchar(50)")
    private String userName;//用户名

    @Column(name="pwd",  columnDefinition="varchar(50)")
    private String pwd;//密码

    @Column(name="user_phone", unique=true,  columnDefinition="varchar(20)")
    private String userPhone;//用户手机号

    @Column(name="v_cfp", columnDefinition="Boolean")
    private Boolean vCFP;//实名认证

    @Column(name="v_cq", columnDefinition="Boolean")
    private Boolean vCQ;//信用问卷

    @Column(name="reg_lng", columnDefinition="varchar(50)")
    private String regLng;//注册经度

    @Column(name="reg_lat",  columnDefinition="varchar(50)")
    private String regLat;//注册纬度

    @Column(name="reg_address",  columnDefinition="varchar(500)")
    private String regAddress;//注册地址

    @Column(name="del_flag", columnDefinition="Boolean")
    private Boolean delFlag;// 是否删除 true 删除  false 未删除  数据库  1 删除 0 未删除

    @Column(name="create_time", columnDefinition="datetime",updatable=false)
    private Date createTime;//创建日期

    @Column(name="update_time", columnDefinition="datetime",updatable=true)
    private Date updateTime;//修改时间

    @Transient
    private Integer activeCount;//活跃数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
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

    public Boolean getvCFP() {
        return vCFP;
    }

    public void setvCFP(Boolean vCFP) {
        this.vCFP = vCFP;
    }


    public Boolean getvCQ() {
        return vCQ;
    }

    public void setvCQ(Boolean vCQ) {
        this.vCQ = vCQ;
    }


    public String getRegLng() {
        return regLng;
    }

    public void setRegLng(String regLng) {
        this.regLng = regLng;
    }

    public String getRegLat() {
        return regLat;
    }

    public void setRegLat(String regLat) {
        this.regLat = regLat;
    }


    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }
}
