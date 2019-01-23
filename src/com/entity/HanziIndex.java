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
@Table(name="hanziindex")
public class HanziIndex implements Serializable{
	
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
	
	@Column(name="hanzi", columnDefinition="varchar (200)")
	private String hanzi;//
	
	@Column(name="createDate")
	private String createDate;//创建日期
	
	@Column(name="updateTime")
	private String updateTime;
	
	@Column(name="delFlag")
	private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHanzi() {
		return hanzi;
	}

	public void setHanzi(String hanzi) {
		this.hanzi = hanzi;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HanziIndex(Integer id, String hanzi, String createDate,
			String updateTime, String delFlag) {
		super();
		this.id = id;
		this.hanzi = hanzi;
		this.createDate = createDate;
		this.updateTime = updateTime;
		this.delFlag = delFlag;
	}

	public HanziIndex() {
		super();
	}


}
