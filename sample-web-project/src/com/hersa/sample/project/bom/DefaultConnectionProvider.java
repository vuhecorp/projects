package com.hersa.sample.project.bom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hersa.sample.project.utils.Constants;

public class DefaultConnectionProvider {
	final static Logger logger = Logger.getLogger(DefaultConnectionProvider.class);

	private static Connection connection;
	
	public static Connection setConnectionProvider(String schema){
		if (schema == null) {
			schema = Constants.USER_PROVIDER;
		}
	//	String connectionUrl = "jdbc:mysql://localhost:3306/" + schema + "?autoReconnect=true&useSSL=false";
		String connectionUrl = "jdbc:mysql://localhost:3306/" + schema + "?autoReconnect=true&useSSL=false";
		connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl, "user", "password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return connection;
	}
	
	public static void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}
	
	public static Connection getConnection() {
		return getConnection();
	}
	
}
