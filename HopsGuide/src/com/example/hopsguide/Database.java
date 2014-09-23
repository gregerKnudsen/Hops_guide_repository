package com.example.hopsguide;

import java.sql.Connection;
import java.sql.DriverManager;

import android.database.SQLException;

public class Database {
	Connection conn;
	private String selectAllHopsNames;
	private String selectHopsByName;
	private String selectHopsByCountry;
	private String selectHopsByAlpha;

	public static Connection getConnection() throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException, java.sql.SQLException{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://172.31.3.90:3306/sql352981";
		String username = "sql352981";
		String password = "zT9*fY1*";
		Class.forName(driver);
		
		return DriverManager.getConnection(url,username,password);
	}
	
//	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, java.sql.SQLException{
//		getConnection();
//	}
}
