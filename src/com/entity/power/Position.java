package com.entity.power;

import javax.persistence.*;

import java.util.Date;

/**
 * 职位表
 * @author hanley
 * @date 2018/12/13 15:09
 * 风萧萧兮易水寒
 */
@Entity
@Table(name="position")
public class Position {

    @Id
    @Basic
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//ID

    @Column(name="position", columnDefinition="varchar (50)")
    private String position;//职位名称

    @Column(name="create_time", columnDefinition="datetime",updatable=false)
    private Date createTime;//创建日期

    @Column(name="update_time", columnDefinition="datetime",updatable=true)
    private Date updateTime;//修改时间

    @Column(name="del_flag", columnDefinition="Boolean")
    private Boolean delFlag;// 是否删除 true 删除  false 未删除  数据库  1 删除 0 未删除
    
    @Transient
    private int actionNum;//拥有的权限数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
    
    
}
