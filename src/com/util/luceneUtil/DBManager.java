package com.util.luceneUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.luceneUtil.ConnectionPool.PooledConnection;

public class DBManager{

	private static PooledConnection conn;
	private static ConnectionPool connectionPool;
	private static DBManager inst;

	public void close() {
		try {
			connectionPool.closeConnectionPool();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DBManager() {
		if (inst != null)
			return;

		// TODO Auto-generated constructor stub

		
		
		connectionPool = new ConnectionPool("jdbc.properties");
		
		try {
			connectionPool.createPool();
			inst = this;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static PooledConnection getConnection() {
		if (inst == null)
			System.out.println("new DBManager");
			new DBManager();

		try {

			conn = connectionPool.getConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public static void main(String[] args) {
		
			//for(int i=0;i<100000;i++){
			 String sql = "select * from credit where id=1";
				ResultSet rs;
				PooledConnection conn = null;
				try {
					conn = DBManager.getConnection();
					rs = conn.executeQuery(sql);
	
					if (rs.next()){
						//System.out.println("i=="+i);
	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(conn!=null){
						conn.close();
					}
					
				}
				//return 0;
	
		//}
	}

}

             