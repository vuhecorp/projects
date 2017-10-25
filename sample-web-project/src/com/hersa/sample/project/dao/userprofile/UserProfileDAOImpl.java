package com.hersa.sample.project.dao.userprofile;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hersa.sample.project.utils.Constants;

public class UserProfileDAOImpl implements UserProfileDAO {
	final static Logger logger = Logger.getLogger(UserProfileDAOImpl.class);
	private String tableName = Constants.USER_PROFILE;
	private Connection connection;
	private String prefix = Constants.USER_PROVIDER;
	private String sqlSelect = "SELECT * FROM " + prefix + "." + tableName + " WHERE userid = ?;";
	private String sqlUpdate = "UPDATE " + prefix + "." + tableName + " SET USERID = ?, PHONE = ?, ADDRESS = ?, ADDRESS2 = ?, "
								+ "CITY = ?, STATE = ?, ZIP = ? ";
	private String sqlSelectAll = "SELECT * FROM " + prefix + "." + tableName + ";";
	private String sqlDelete = "DELETE FROM " + prefix + "." + tableName + " WHERE id = ?;";
	private String sqlCreate = "INSERT INTO " + prefix + "." + tableName + " (USERID, PHONE, ADDRESS, ADDRESS2, CITY, STATE, ZIP)"
								+ " VALUES (?,?,?,?,?,?,?);";
	

	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void updateUserProfile(UserProfile userProfile) {
		String whereClause = "WHERE USERID = ?";
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
	}

	@Override
	public void deleteUserProfile(UserProfile userProfile) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createUserProfile(UserProfile userProfile) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserProfile[] listAllUserProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

}