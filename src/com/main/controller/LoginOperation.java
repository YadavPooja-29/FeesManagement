package com.main.controller;
import com.main.dao.User;
import com.main.db.DbTest;
import com.main.impl.Operations;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.main.dao.*;
public class LoginOperation implements Operations {

	public User getUserType(String user, String password) {
		DbTest dbData = new DbTest();
		Connection con = dbData.getConn();
		User userDao = new User();
		
		try{  
		Statement stmt=(Statement) con.createStatement();  
		ResultSet rs=(ResultSet) stmt.executeQuery("select * from user where name=\""+user+"\" and password=\""+password+"\"");  
		while(rs.next()) {
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		userDao.setName(rs.getString(2));
			userDao.setPassword(rs.getString(3));
			userDao.setEmail(rs.getString(4));
			userDao.setContact(rs.getString(5));
			userDao.setType(rs.getString(6));
		}
		return userDao;  
		}catch(Exception e){ System.out.println(e);}  
	
		return null;
	}

}
