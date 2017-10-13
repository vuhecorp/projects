package com.hersa.sample.project.bom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DefaultConnectionProvider {
	final static Logger logger = Logger.getLogger(DefaultConnectionProvider.class);

	private static Connection connection;
	
	public static Connection setConnectionProvider(String schema){
		String connectionUrl = "jdbc:mysql://localhost:3306/" + schema;
		connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl, "root", "A1128857795!");
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
		return connection;
	}
	
}
