package com.main.db;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DbTest {
	public Connection getConn() {
		  try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = null;
	            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root", "");
	        //    System.out.print("Database is connected !");
	           return conn;
	        }
	        catch(Exception e) {
	            System.out.print("Do not connect to DB - Error:"+e);
	        }
		return null;
	}
}
