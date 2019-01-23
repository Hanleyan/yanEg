package com.entity.power;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 * @author hanley
 * @date 2018/12/13 15:04
 * 风萧萧兮易水寒
 */
@Entity
@Table(name="users")
public class Users {

    @Id
    @Basic
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//ID

    @Column(name="username", columnDefinition="varchar (50)")
    private String username;//姓名

    @Column(name="password", columnDefinition="varchar (500)")
    private String password;//密码

    @Column(name="sex", columnDefinition="varchar (2)")
    private String sex;//

    @Column(name="age", columnDefinition="varchar (3)")
    private String age;//

    @Column(name="create_time", columnDefinition="datetime",updatable=false)
    private Date createTime;//创建日期

    @Column(name="update_time", columnDefinition="datetime",updatable=true)
    private Date updateTime;//修改时间

    @Column(name="del_flag", columnDefinition="Boolean")
    private Boolean delFlag;// 是否删除 true 删除  false 未删除  数据库  1 删除 0 未删除
    
    @Transient
    private int actionNum;//拥有的权限数量
    
    @Transient
    private String position;//职位
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

	public int getActionNum() {
		return actionNum;
	}

	public void setActionNum(int actionNum) {
		this.actionNum = actionNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
    
    
}
