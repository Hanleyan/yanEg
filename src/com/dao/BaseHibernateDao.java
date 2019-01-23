package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@SuppressWarnings("unused")
public class BaseHibernateDao extends DaoSupport{
	
	public String HQL = "";
    private static SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate;
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
       this.hibernateTemplate=createHibernateTemplate(sessionFactory);
    }

    public Session getSession() {
        if (this.sessionFactory == null) {
            throw new HibernateException("Session Create Fail,SessionFactory is null!");
        }
       // hibernate4不支持geiCurrentSession()
       return this.sessionFactory.getCurrentSession();
       //事物不在同一个线程，切不自动提交,关闭
        //return this.sessionFactory.openSession();
    }

    protected HibernateTemplate createHibernateTemplate(
            SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @Override
    protected void checkDaoConfig() throws IllegalArgumentException {
        if (this.hibernateTemplate == null) {
            throw new IllegalArgumentException("'sessionFactory' or 'hibernateTemplate' is required");
        }
    }
    
    

    public final void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public final HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }
    
    
   
	
	
	/**
	 * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 调用方式如下：
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values 可变参数.
	 */

	
	
	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */

	
	
	/**
	 * 获取Connction
	 */

	public static Connection getConnection(){
		try {
			return SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	
}
