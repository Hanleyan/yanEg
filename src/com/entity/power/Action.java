package com.entity.power;

import javax.persistence.*;
import java.util.Date;

/**
 * 权限动作表
 * @author hanley
 * @date 2018/12/13 15:10
 * 风萧萧兮易水寒
 */
@Entity
@Table(name="action")
public class Action {

    @Id
    @Basic
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;//ID

    @Column(name="action", columnDefinition="varchar (50)")
    private String action;//权限名称

    @Column(name="action_path", columnDefinition="varchar (50)")
    private String actionPath;//操作权限 jsp路径
    
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
    
    
}
