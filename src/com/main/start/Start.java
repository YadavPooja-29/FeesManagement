package com.main.start;

import java.sql.SQLException;
import java.util.Scanner;
import com.main.controller.*;
import com.main.dao.User;
public class Start {

	public static void main(String[] args) throws SQLException {
		startMenu();
		
	}
	
	public static void startMenu() throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Login");
		System.out.println("-------------------------------");
		System.out.println("Enter Username");
		String user = sc.nextLine();
		System.out.println("Enter Password");
		String password = sc.nextLine();
		LoginOperation login = new LoginOperation();
		User userData = login.getUserType(user,password);
		
		if (userData.getType().equals("A")) {
			AdminMenu adminMenu = new AdminMenu();
			adminMenu.setMenu();
		}else {
			StudentMenu student = new StudentMenu();
			student.setSMenu();
		}
	}
}
