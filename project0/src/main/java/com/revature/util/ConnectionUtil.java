package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	
	private static Connection con;
	
	public static Connection getConnectionfromProperyFile() throws SQLException, IOException {
		Properties prop = new Properties();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		prop.load(loader.getResourceAsStream("dbConnection.properties"));
		
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String pass = prop.getProperty("password");
		
		
		//System.out.println(url);
		if(con == null || con.isClosed()) {
			con = DriverManager.getConnection(url,username,pass);
		}
		
		return con;
	}
	
}
