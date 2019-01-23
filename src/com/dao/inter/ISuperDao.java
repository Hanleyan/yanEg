package com.dao.inter;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ISuperDao {

	/**
	 * 登录
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public Object login(String userName, String pwd);
	
	/**
	 * 新增
	 * @param object
	 * @return
	 */
	public Serializable addObject(Object object);
	
	/**
	 * 新增或修改
	 * @param object
	 * @return
	 */
	public Boolean addOrUpdateObject(Object object);
	
	/**
	 * 修改
	 * @param object
	 * @return
	 */
	public Boolean updateObject(Object object);
	
	/**
	 * HQL修改
	 * @param  hql
	 * @return
	 */
	public Boolean updateObjectByHql(String hql);
	
	
	
	/**
	 * 获取对象
	 * @param c
	 * @param id
	 * @return
	 */
	public Object getObject(Class c, Serializable id);

	/**
	 * 根据Hql获取对象
	 * @param hql
	 * @return
	 */
	public Object getObjectByHql(String hql);
	
	/**
	 * 获取列表
	 * @param hql
	 * @return
	 */
	public List<?> getObjectList(String hql);

	/**
	 * Sql查询得到list集合（新增）
	 * @param sql
	 * @return
	 */
	public List<?> getObjectListBySql(String sql);
	
	/**
	 * Hql翻页
	 * @param hql
	 * @return
	 */
	//public Page getObjectPageByHql(String hql, Integer pageNo, Integer pageSize);

	/**
	 * Sql翻页
	 * @param c
	 * @param sql
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	//public Page getObjectPageBySql(Class c, String sql, Integer pageSize, Integer pageNo);

	/**
	 * 删除
	 * @param hql
	 * @return
	 */
	public int deleteByHql(String hql);
	
	/**
	 * sql获取list
	 * @param sql
	 * @return
	 */
	public List getListBySql(Class c, String sql);

	/**
	 * sql函数
	 * @param sql
	 * @return
	 */
	public Object[] functionQuery(String sql);

	/**
	 * Count函数
	 * @param sql
	 * @return
	 */
	public Object functionCount(String sql);

	//原生sql查询
	//public List<InforUserWithNo> getObjectSqlList(String hql);

	/*public Boolean updateObjectexcute(int iid,int userid);*/

	public int getCount(String sql);//查数量
	public Object getpra(String sql);//查单个属性

	public Boolean updateObjectBySql(String sql);

	public Integer getIntBySql(String sql);

	public Object getObjectBySql(String sql);
	

}
