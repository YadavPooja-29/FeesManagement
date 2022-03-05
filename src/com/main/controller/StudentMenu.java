package com.main.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.main.dao.Student;
import com.main.db.DbTest;
import com.main.start.Start;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class StudentMenu {
	DbTest dbData = new DbTest();
	
	
	public void setSMenu() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println(" Accountant Section ");
		System.out.println("1. Add Student");
		System.out.println("2. View Student");
		System.out.println("3. Edit Student");
		System.out.println("4. Due Fee");
		System.out.println("5. LogOut");
		String item = sc.nextLine();
		ItemSelected(item);
		
	}

	private void ItemSelected(String item) throws SQLException 
	{
		Scanner sc = new Scanner(System.in);
		if(item.equals("1")) 
		{
			addStudent();
		}
		if(item.equals("2"))
		{
			Connection con = dbData.getConn();
			try
			{  
				Statement stmt=(Statement) con.createStatement();  
				ResultSet rs=(ResultSet) stmt.executeQuery("select * from student");  
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getInt(5)+"  "+rs.getInt(6)+"  "+rs.getInt(7)+"  "+rs.getString(8)+"  "+rs.getString(9)+"  "+rs.getString(10)+"  "+rs.getString(11)+"  "+rs.getString(12));  
				}
			}
			catch(Exception e)
			{ 
				System.out.println(e);
			}  
			setSMenu();
		}
		if(item.equals("3"))
		{
			System.out.println("Enter Student Id");
			int id =sc.nextInt();
			Connection con = dbData.getConn();
			int fees = null, paid = null,due = null;
			int updateId = 0;
			try{  
			Statement stmt=(Statement) con.createStatement();  
			ResultSet rs=(ResultSet) stmt.executeQuery("select * from student where sid ="+id+"");  
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8)+"  "+rs.getString(9)+"  "+rs.getString(10)+"  "+rs.getString(11)+"  "+rs.getString(12));  
				updateId=rs.getInt(1);
				fees=rs.getString(5);
				paid=rs.getString(6);
				due=rs.getString(7);
			}
			}catch(Exception e){ System.out.println(e);}  
			
			System.out.println("Update User Fees Details");
			System.out.println("Enter Fee Paied");
			int feePaid = sc.nextInt();
			int fee =Integer.parseInt(fees);
			int paidInt =Integer.parseInt(paid);
			int dueInt =Integer.parseInt(due);
			int updatedPaid =  paidInt+feePaid;
			int updatedDue = fee - updatedPaid;
			updatePaymentDetails(updateId,""+updatedPaid,""+updatedDue,fees);
			
		}
		if(item.equals("4")) {
			Connection con = dbData.getConn();
			try{  
			Statement stmt=(Statement) con.createStatement();  
			ResultSet rs=(ResultSet) stmt.executeQuery("select * from student where due !=''");  
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8)+"  "+rs.getString(9)+"  "+rs.getString(10)+"  "+rs.getString(11)+"  "+rs.getString(12));  
			}
			}catch(Exception e){ System.out.println(e);} 
			
			setSMenu();
		}
		if(item.equals("5")) {
			Start.startMenu();
		}

		
	}

	private void updatePaymentDetails(int updateId, String feePaid, String updatedDue,String total) throws SQLException {
			Connection con = dbData.getConn();
		String query = "update student set paid=?,due=? where sid=? ";
		 System.out.println(query);
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, feePaid);
        ps.setString(2, updatedDue);
        ps.setInt(3, updateId);
        ps.executeUpdate();
        System.out.println("Total fee: "+total+" | Paid: "+feePaid+" | Due: "+updatedDue);
		setSMenu();
      
		
	}

	private void addStudent() throws SQLException {
		Scanner sc = new Scanner(System.in);
		Student student = new Student();
		System.out.println("Enter Name");
		student.setName(sc.nextLine());
		System.out.println("Enter Email");
		student.setEmail(sc.nextLine());
		System.out.println("Enter Course");
		student.setCource(sc.nextLine());
		System.out.println("Enter Fee");
		student.setFee(sc.nextLine());
		System.out.println("Enter Paid");
		student.setPaid(sc.nextLine());
		System.out.println("Enter Due");
		student.setDue(sc.nextLine());
		System.out.println("Enter Address");
		student.setAddress(sc.nextLine());
		System.out.println("Enter City");
		student.setCity(sc.nextLine());
		System.out.println("Enter State");
		student.setState(sc.nextLine());
		System.out.println("Enter Country");
		student.setCountry(sc.nextLine());
		System.out.println("Enter Contact");
		student.setContact(sc.nextLine());
		
		saveStudent(student);
		
		
	}

	private void saveStudent(Student student) throws SQLException {
		Connection conn = dbData.getConn();
		Statement stmt = (Statement) conn.createStatement();
		String query1 = "INSERT INTO student(name,email,course,fee,paid,due,address,city,state,country,contactno) VALUES ('"+student.getName()+"','"+student.getEmail()+"','"+student.getCource()+"','"+student.getFee()+"','"+student.getPaid()+"','"+student.getDue()+"','"+student.getAddress()+"','"+student.getCity()+"','"+student.getState()+"','"+student.getCountry()+"','"+student.getContact()+"')";
	    System.out.println(query1); 
		stmt.executeUpdate(query1);
	      System.out.println("Student Added");
	     setSMenu();
	}
}
