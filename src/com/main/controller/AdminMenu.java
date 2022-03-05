package com.main.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.main.dao.User;
import com.main.db.DbTest;
import com.main.start.Start;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class AdminMenu
{
	DbTest dbData = new DbTest();
	Scanner sc = new Scanner(System.in);
	public void setMenu() throws SQLException 
	{
		System.out.println("Select Menu:");
		System.out.println("1. Add Accountant");
		System.out.println("2. View Accountant");
		System.out.println("3. LogOut");
		String item = sc.nextLine();
		ItemSelected(item);
	}

	private void ItemSelected(String item) throws SQLException
	 {
		if(item.equals("1"))
		{
			User userData = new User();
			System.out.println("Enter Name : ");
			userData.setName(sc.nextLine());
			System.out.println("Enter Password : ");
			userData.setPassword(sc.nextLine());
			System.out.println("Enter Email : ");
			userData.setEmail(sc.nextLine());
			System.out.println("Enter Contact : ");
			userData.setContact(sc.nextLine());
			
			saveUser(userData);
			
		}
		if(item.equals("2"))
		{
			Connection con = dbData.getConn();
			try
			{  
				Statement stmt=(Statement) con.createStatement();  
				ResultSet rs=(ResultSet) stmt.executeQuery("select * from user where type!='A'");  
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));  
				}
			}
			catch(Exception e)
			{ 
				System.out.println(e);
			}  
			setMenu();			
		}
		if(item.equals("3"))
		{
			Start start = new Start();
			start.startMenu();
		}
		
	}

	private void saveUser(User userData) throws SQLException {
		Connection conn = dbData.getConn();
		Statement stmt = (Statement) conn.createStatement();
		String query1 = "INSERT INTO user(name,password,email,contactno,type) VALUES ('"+userData.getName()+"','"+userData.getPassword()+"','"+userData.getEmail()+"','"+userData.getContact()+"','AC')";
	      stmt.executeUpdate(query1);
	      System.out.println("User Created");
	     setMenu();
	}

}
