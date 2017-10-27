package com.hersa.sample.project.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hersa.sample.project.DuplicateUserException;
import com.hersa.sample.project.bom.DefaultConnectionProvider;
import com.hersa.sample.project.utils.Constants;


public class UserDAOImpl implements UserDAO {
	final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	private String tableName = Constants.USERS;
	private Connection connection;
	private String prefix = Constants.USER_PROVIDER;
	private String sqlSelect = "SELECT * FROM " + prefix + "." + tableName + " WHERE email = ?;";
	private String sqlUpdate = "UPDATE " + prefix + "." + tableName + " SET fname = ?, lname = ?, password = ?, email = ?, isActive = ?, "
								+ "profileImage = ?, role = ?, recentunlock = ?, failedattempts = ?, lastfailed = ?, locked = ?, lockedon = ?, firstfailed = ?, "
								+ "modifiedby = ?, modifieddate = ?, username = ? ";
	private String sqlUpdateSignOn = "UPDATE " + prefix + "." + tableName + " SET fname = ?, lname = ?, password = ?, email = ?, isActive = ?, "
			+ "profileImage = ?, role = ?, recentunlock = ?, failedattempts = ?, lastfailed = ?, locked = ?, lockedon = ?, firstfailed = ?  ";
	private String sqlSelectAll = "SELECT * FROM " + prefix + "." + tableName + ";";
	private String sqlDelete = "DELETE FROM " + prefix + "." + tableName + " WHERE id = ?;";
	private String sqlCreate = "INSERT INTO " + prefix + "." + tableName + " (fname, lname, password, email, role, createdby, username)"
								+ " VALUES (?,?,?,?,?,?,?);";
	private String sqlSelectLocked = "SELECT * FROM " + prefix + "." + tableName + " WHERE locked = ?;";
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public void updateUser(User user) {
		String whereClause = "WHERE id = ?";
		try {
			int i = 1;
			PreparedStatement statement = connection.prepareStatement(sqlUpdate + whereClause);
			statement.setString(i++, user.getFirstName());
			statement.setString(i++, user.getLastName());
			statement.setString(i++, user.getPassword());
			statement.setString(i++, user.getEmail());
			statement.setInt(i++, user.getActive());
			statement.setString(i++, user.getImagePath());
			statement.setString(i++, user.getRole());
			statement.setInt(i++, user.getRecentUnlock());
			statement.setInt(i++, user.getFailedAttempts());
			statement.setTimestamp(i++, user.getLastFailed());
			statement.setInt(i++, user.getLocked());
			statement.setTimestamp(i++, user.getLockedOn());
			statement.setTimestamp(i++, user.getFirstFailed());
			statement.setString(i++, user.getModifiedBy());
			Timestamp stamp = null;
			try {
				stamp = new Timestamp(user.getModifiedDate().getTime());
			} catch (Exception e) {
				;;
			}
			
			statement.setTimestamp(i++, stamp);
			statement.setString(i++, user.getUserName());
			/***SET WHERE PARAM**/
			
			statement.setInt(i++, user.getId());
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
				user.setCreatedBy(results.getString("createdby"));
				user.setModifiedBy(results.getString("modifiedby"));
				user.setModifiedDate(results.getTimestamp("modifieddate"));
				user.setUserName(results.getString("username"));
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
				user.setCreatedBy(results.getString("createdby"));
				user.setModifiedBy(results.getString("modifiedby"));
				user.setModifiedDate(results.getTimestamp("modifieddate"));
				user.setUserName(results.getString("username"));
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
	public void deleteUser(User user) throws SQLException {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sqlDelete);
			statement.setLong(1, user.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error with data base connectivity.");
		}
	}
	@Override
	public void createUser(User user) throws SQLException, DuplicateUserException {
		try {
			int i = 1;
			PreparedStatement statement = connection.prepareStatement(sqlCreate);
			statement.setString(i++, user.getFirstName());
			statement.setString(i++, user.getLastName());
			statement.setString(i++, user.getPassword());
			statement.setString(i++, user.getEmail());
			statement.setString(i++, user.getRole());
			statement.setString(i++, user.getCreatedBy());
			statement.setString(i++, user.getUserName());
			
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate entry")) {
				if (e.getMessage().contains("username_UNIQUE")) {
					throw new DuplicateUserException("The username specified is already taken.");
				}
				throw new DuplicateUserException("The email specified is already taken.");
			}
			throw new SQLException("Error in database connectivity.");
		}finally{
			DefaultConnectionProvider.closeConnection(connection);
		}
		
	}
	@Override
	public List<User> listByLocked(int locked) throws SQLException {
	List<User> userList = new ArrayList<User>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlSelectLocked);
			
			statement.setInt(1, locked);
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
				user.setCreatedBy(results.getString("createdby"));
				user.setModifiedBy(results.getString("modifiedby"));
				user.setModifiedDate(results.getTimestamp("modifieddate"));
				user.setUserName(results.getString("username"));
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
	public void updateUserSignonInfo(User user) {
		String whereClause = "WHERE id = ?;";
		try {
			int i = 1;
			PreparedStatement statement = connection.prepareStatement(sqlUpdateSignOn + whereClause);
			statement.setString(i++, user.getFirstName());
			statement.setString(i++, user.getLastName());
			statement.setString(i++, user.getPassword());
			statement.setString(i++, user.getEmail());
			statement.setInt(i++, user.getActive());
			statement.setString(i++, user.getImagePath());
			statement.setString(i++, user.getRole());
			statement.setInt(i++, user.getRecentUnlock());
			statement.setInt(i++, user.getFailedAttempts());
			statement.setTimestamp(i++, user.getLastFailed());
			statement.setInt(i++, user.getLocked());
			statement.setTimestamp(i++, user.getLockedOn());
			statement.setTimestamp(i++, user.getFirstFailed());
		
			/***SET WHERE PARAM**/
			
			statement.setInt(i++, user.getId());
			statement.execute();
		} catch (SQLException e) {
			System.err.println("<<<<<<---- " + e.getMessage());
			
			
		}finally{
			DefaultConnectionProvider.closeConnection(connection);
		}
		
		
	}

	

}
