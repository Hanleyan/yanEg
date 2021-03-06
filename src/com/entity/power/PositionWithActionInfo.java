package com.entity.power;

import javax.persistence.*;
import java.util.Date;

/**
 * 职位与权限动作关系表
 * @author hanley
 * @date 2018/12/13 15:14
 * 风萧萧兮易水寒
 */
@Entity
@Table(name="position_with_action_info")
public class PositionWithActionInfo {

    @Id
    @Basic
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//ID

    @Column(name="position_id", columnDefinition="int (5)")
    private Integer positionId;//职位id

    @Column(name="action_id", columnDefinition="int (5)")
    private Integer actionId;//权限动作id

    @Column(name="create_time", columnDefinition="datetime",updatable=false)
    private Date createTime;//创建日期

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

   

    public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
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
