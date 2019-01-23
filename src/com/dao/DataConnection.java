package com.dao;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.util.luceneUtil.CreateLuceneUtil;
import com.util.luceneUtil.DBManager;
import com.util.luceneUtil.ConnectionPool.PooledConnection;

import org.apache.lucene.index.IndexWriter;
import org.springframework.orm.hibernate5.SessionFactoryUtils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataConnection extends BaseHibernateDao {

	/*static Connection conn = (Connection) getConnection();
	static Statement stmt = null;
	ResultSet rs = null ;*/
	
    public int getIndexCount(){
        try {
            String HQL = "select count(*)as count from hanziindex where delFlag='0' " ;

            System.out.println(HQL);
            
          //获取连接
            /*stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(HQL);*/
            
            PooledConnection conn =  DBManager.getConnection();
            ResultSet rs = conn.executeQuery(HQL);
			
			
            int totalCount = 0;
            while(rs.next()){
                totalCount = rs.getInt("count");
            }
            System.out.println("getIndexCount=="+totalCount);

            rs.close();
            conn.close();

            return totalCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void reportIndexBiz(IndexWriter indexWriter, PooledConnection conn, int beginIndex, int everyPage)
            throws Exception {

        String HQL = "select id,hanzi,createDate,updateTime,delFlag from hanziindex where delFlag='0' limit " + beginIndex + "," + everyPage;
        System.out.println("everyPage=="+everyPage+" 索引HQL===="+HQL);

        ResultSet rs = conn.executeQuery(HQL);
        System.out.println("查询结果");
        while(rs.next()){
            CreateLuceneUtil.createIndexMainDocument(indexWriter, rs);
        }
        rs.close();
    }

    public void reportIndexLegel(IndexWriter indexWriter, PooledConnection conn, int beginIndex, int everyPage)
            throws Exception {

        String HQL = "select id,legelnamecn,legelnameen,crefo_no,index_level,update_time from index_main  where del_flag=1 limit " + beginIndex + "," + everyPage;
        System.out.println("everyPage=="+everyPage+"法人名称索引HQL===="+HQL);

        ResultSet rs = conn.executeQuery(HQL);
        System.out.println("查询结果");
        while(rs.next()){
            CreateLuceneUtil.createIndexMainDocument(indexWriter, rs);
        }
        rs.close();
    }

    /**
	 * 获取Connction
	 */

	/*public Connection getConnection(){
		try {
			return SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/



}
