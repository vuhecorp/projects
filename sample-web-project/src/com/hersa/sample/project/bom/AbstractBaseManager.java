package com.hersa.sample.project.bom;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.hersa.sample.project.dao.user.UserDAO;
import com.hersa.sample.project.dao.user.UserDAOImpl;
import com.hersa.sample.project.dao.usersignon.UserSignOnDAO;
import com.hersa.sample.project.dao.usersignon.UserSignOnDAOImpl;

public class AbstractBaseManager {
	final static Logger logger = Logger.getLogger(AbstractBaseManager.class);
	
	private Connection connection;
	public AbstractBaseManager() {
//		this.connection = getConnectionProvider();
	}
	
	public Connection getConnectionProvider() {
		return DefaultConnectionProvider.setConnectionProvider(null);
	}
	
	public UserDAO getUserDAO(){
		UserDAO dao = new UserDAOImpl(); 
		dao.setConnection(getConnectionProvider());
		if (connection != null) {
			dao.setConnection(connection);
		}
		return dao;		
	}
	
	public UserSignOnDAO getUserSignOnDAO() {
		UserSignOnDAO dao = new UserSignOnDAOImpl(); 
		dao.setConnection(getConnectionProvider());
		if (connection != null) {
			dao.setConnection(connection);
		}
		return dao;
	}

	public Connection getConnection() {
		if (connection != null) {
			return connection;
		}
		return getConnectionProvider();
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
 