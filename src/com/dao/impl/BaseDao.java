package com.dao.impl;



import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.BaseHibernateDao;
import com.dao.inter.ISuperDao;



@Repository("BaseDao")
public class BaseDao extends BaseHibernateDao implements ISuperDao {


	public Object login(String userName, String pwd) {
		/*//"from Dealers u where u.userName=:userName and u.userPwd=:userPwd";
		String hql = "from Dealers u where 1=1 and delFlag = false and u.userName=:userName and u.userPwd=:userPwd ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("userName", userName);
		query.setParameter("userPwd", pwd);
		List<Object> pojolist = query.list();
		if(pojolist.size()>0){
			return pojolist.get(0);
		}*/
		return null;
	}

	public Serializable addObject(Object object) {
	//	object = null;
		Session session = getSession();
		Serializable id = session.save(object);
		return id;
	}
	
	
	public Boolean addOrUpdateObject(Object object) {
		Session session = getSession();
		session.saveOrUpdate(object);
		return true;

	}

	public Boolean updateObject(Object object) {
		Session session = getSession();
		session.update(object);
		return true;
	}

	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getObject(Class c, Serializable id) {
		Object entity = (Object) getSession().get(c, id);
		return entity;
	}

	
	@SuppressWarnings("rawtypes")
	public List<?> getObjectList(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<?> list = query.list();
		return list;
	}
//新写
	@SuppressWarnings("rawtypes")
	public List<?> getObjectListBySql(String sql){
		Session session = getSession();
		Query query = session.createNativeQuery(sql);
		List<?> list = query.list();
		return list;

	}

	

    @SuppressWarnings("unchecked")
	public Object[] functionQuery(String sql){
        Session session = getSession();
        Query<Object[]> query = session.createNativeQuery(sql);
        Object[] obj = query.getSingleResult();
        return obj;
    }

	public Object functionCount(String sql){
		Session session = getSession();
		Query query = session.createNativeQuery(sql);
		Object obj = query.uniqueResult();
		return obj;
	}
	public Boolean updateObjectByHql(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		int count = query.executeUpdate();
		if( count>0){
			return true;
		}
		return false;
	}


	
	public Object getObjectByHql(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		Object object = null;
		List list = query.list();
		if(null != list && list.size()>0){
			object = list.get(0);
		}

		session.clear();
		return object;
	}




	
	public List getListBySql(Class c, String sql){
		
		Session session = getSession();


		Query query = session.createNativeQuery(sql, c);
		List list = query.getResultList();
		return list;
	}

	public int deleteByHql(String hql) {
		Session session = getSession();
		Query queryupdate=session.createQuery(hql); 
		int ret=queryupdate.executeUpdate(); 
		
		return ret;
	}

	//特意为通过userid查询消息通知写的jdbc
	/*public List<InforUserWithNo> getObjectSqlList(String hql) {
		Session session = getSession();
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null ;
		List<InforUserWithNo> list = new ArrayList<InforUserWithNo>();
		try {
			HQL = hql ;
			System.out.println(HQL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(HQL);
			int totalCount = 0;
			while(rs.next()){
				InforUserWithNo inforUserWithNo = new InforUserWithNo();
				inforUserWithNo.setUserid(rs.getInt("userid"));
				inforUserWithNo.setIId(rs.getInt("iId"));
				inforUserWithNo.setTitle(rs.getString("title"));
				inforUserWithNo.setSubtitle(rs.getString("subtitle"));
				inforUserWithNo.setContent(rs.getString("content"));
				inforUserWithNo.setMusicUrl(rs.getString("musicUrl"));
				inforUserWithNo.setPicUrl(rs.getString("picUrl"));
				inforUserWithNo.setVideoUrl(rs.getString("videoUrl"));
				inforUserWithNo.setBadge(rs.getString("badge"));
				inforUserWithNo.setCategoryId(rs.getString("categoryId"));
				inforUserWithNo.setReleasetime(rs.getString("releasetime"));
				inforUserWithNo.setCreatetime(rs.getDate("createtime"));
				inforUserWithNo.setType(rs.getString("type"));
				inforUserWithNo.setSource(rs.getString("source"));
				inforUserWithNo.setIsread(rs.getString("isread"));
				inforUserWithNo.setNotifyResult(rs.getString("notifyResult"));
				list.add(inforUserWithNo);
			}
			System.out.println("getIndex2Count=="+totalCount);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != rs)
					rs.close();
				if(null != stmt)
					stmt.close();
				if(null != conn)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		NativeQuery<?> query = session.createNativeQuery(hql);
		List<?> list = query.getResultList();
		return list;
	}*/

	//为了修改通知的已读未读  u.userName=:userName and u.passWord=:passWord
	/*public Boolean updateObjectexcute(int iid,int userid) {
		String rt = new Date()+"" ;
		String hql="UPDATE DB_Userwithinfor u SET u.isread='1' where u.iid=:iid and u.userid=:userid";
		Session session = getSession();
		Query query = session.createQuery(hql);
		//query.setParameter("readtime",rt);
		query.setParameter("iid", iid);
		query.setParameter("userid", userid);
		int issuccess = query.executeUpdate();
		return true;
	}*/

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public int getCount(String sql) {
		Session session = getSession();
		//Query<Object[]> query = session.createNativeQuery(sql);
		Query query = session.createSQLQuery(sql);
		List<BigInteger> list = query.list();
		int count = list.get(0).intValue();
		//Object[] obj = query.getSingleResult();
		return count;
	}
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public Object getpra(String sql) {
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		Object object = null;
		List list = query.list();
		if(null != list && list.size()>0){
			object = list.get(0);
		}
		return object;
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	public  Boolean updateObjectBySql(String sql){
		Session session = getSession();
		Query query = session.createQuery(sql);
		int row = query.executeUpdate();
		return true;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  Integer getIntBySql(String sql){
		Session session = getSession();
		Query query = session.createQuery(sql);
		List<Integer> list = query.list();
		int row = list.get(0).intValue();
		return row;
	}
	/*public  Object getObjectSql(String sql){
			Session session = getSession();
			//Query query = session.createNativeQuery(sql);
			Query query = session.createSQLQuery(sql);
			Object object = null;
			List list = query.list();
			if(null != list && list.size()>0){
				object = list.get(0);
			}
			session.clear();
			return object;
	}*/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getObjectBySql(String sql) {
		Object object=null;
		List<Object> objects=new ArrayList<Object>();
		Session session=getSession();
		Query query=session.createNativeQuery(sql);
		objects= query.list();
		if(objects.size()>0){
			object=objects.get(0);
		}else{
			object=null;
		}
		return object;
	}

	

}
