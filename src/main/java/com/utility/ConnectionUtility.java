package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Static block has failed me");
		}
	}

	
	private static String url = "jdbc:postgresql://" + System.getenv("REVATURE_DB_URL") + "/" + "ERS";
	private static String username = System.getenv("REVATURE_DB_USERNAME");
	private static String password = System.getenv("REVATURE_DB_PASSWORD");

	Connection conn;

	public ConnectionUtility() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public ConnectionUtility(String url, String username, String password) {
		
		this.url = url;
		this.username = username;
		this.password = password;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	public Connection getConn() {
		return conn;
	}

}