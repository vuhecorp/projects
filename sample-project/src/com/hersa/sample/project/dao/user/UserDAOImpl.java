package com.hersa.sample.project.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hersa.sample.project.bom.DefaultConnectionProvider;
import com.hersa.sample.project.utils.Constants;


public class UserDAOImpl implements UserDAO {
	
	private String tableName = "users";
	private Connection connection;
	private String prefix = Constants.USER_PROVIDER;
	private String sqlSelect = "SELECT * FROM " + prefix + "." + tableName + " WHERE email = ?;";
	private String sqlUpdate = "UPDATE " + prefix + "." + tableName + " SET fname = ?, lname = ?, password = ?, email = ?, isActive = ?, "
			+ "profileImage = ?, role = ?, recentunlock = ?, failedattempts = ?, lastfailed = ?, locked = ?, lockedon = ?, firstfailed = ? ";
	private String sqlSelectAll = "SELECT * FROM " + prefix + "." + tableName + ";";
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public void updateUser(User user) {
		String whereClause = "WHERE id = ?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sqlUpdate + whereClause);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setInt(5, user.getActive());
			statement.setString(6, user.getImagePath());
			statement.setString(7, user.getRole());
			statement.setInt(8, user.getRecentUnlock());
			statement.setInt(9, user.getFailedAttempts());
			statement.setTimestamp(10, user.getLastFailed());
			statement.setInt(11, user.getLocked());
			statement.setTimestamp(12, user.getLockedOn());
			statement.setTimestamp(13, user.getFirstFailed());
			/***SET WHERE PARAM**/
			
			statement.setInt(14, user.getId());
			statement.execute();
		} catch (SQLException e) {
			System.err.println("<<<<<<---- " + e.getMessage());
			
			
		}finally{
			DefaultConnectionProvider.closeConnection(connection);
		}
		
		
	}
	@Override
	public List<User> retrieveUserByEmail(String email) {
		
		List<User> userList = new ArrayList<User>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlSelect);
			
			statement.setString(1, email);
			ResultSet results = statement.executeQuery();
			
			while(results.next()){

				User user = new User();
				user.setId(results.getInt("id"));
				user.setFirstName(results.getString("fname"));
				user.setLastName(results.getString("lname"));
				user.setPassword(results.getString("password"));
				user.setEmail(results.getString("email"));
				user.setActive(results.getInt("isActive"));
				user.setImagePath(results.getString("profileImage"));
				user.setRole(results.getString("role"));
				user.setRecentUnlock(results.getInt("recentunlock"));
				user.setFailedAttempts(results.getInt("failedattempts"));
				user.setLastFailed(results.getTimestamp("lastFailed"));
				user.setLocked(results.getInt("locked"));
				user.setLockedOn(results.getTimestamp("lockedon"));
				user.setFirstFailed(results.getTimestamp("firstfailed"));
				userList.add(user);
			}
			
   		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			 DefaultConnectionProvider.closeConnection(connection);
		}
		return userList;
	}
	
	@Override
	public User[] listAllUsers() {
		User[] users = null;
		List<User> userList = new ArrayList<User>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlSelectAll);
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				User user = new User();
				user.setId(results.getInt("id"));
				user.setFirstName(results.getString("fname"));
				user.setLastName(results.getString("lname"));
				user.setPassword(results.getString("password"));
				user.setEmail(results.getString("email"));
				user.setActive(results.getInt("isActive"));
				user.setImagePath(results.getString("profileImage"));
				user.setRole(results.getString("role"));
				user.setRecentUnlock(results.getInt("recentunlock"));
				user.setFailedAttempts(results.getInt("failedattempts"));
				user.setLastFailed(results.getTimestamp("lastFailed"));
				user.setLocked(results.getInt("locked"));
				user.setLockedOn(results.getTimestamp("lockedon"));
				user.setFirstFailed(results.getTimestamp("firstfailed"));
				userList.add(user);
			}
			if (userList.size() > 0) {
				users = new User[userList.size()];
				users = userList.toArray(users);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			 DefaultConnectionProvider.closeConnection(connection);
		}
		return users;
	}

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		this.connection = connection;
		
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	

}
