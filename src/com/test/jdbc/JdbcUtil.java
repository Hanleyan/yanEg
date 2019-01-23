package com.test.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class JdbcUtil {
	
	private static  Connection conn = null;     
    private static final String URL = "jdbc:mysql://127.0.0.1/aaa?autoReconnect=true&characterEncoding=utf8";     
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";     
    private static final String USER_NAME = "root";     
    private static final String PASSWORD = "root";  
      
    public static Connection getConnection() {     
        try {     
            Class.forName(JDBC_DRIVER);     
            conn = (Connection) DriverManager.getConnection(URL, USER_NAME, PASSWORD);     
        } catch (ClassNotFoundException e) {     
            e.printStackTrace();     
        } catch (SQLException e) {     
            e.printStackTrace();     
        }     
        return conn;     
    }  

    public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
