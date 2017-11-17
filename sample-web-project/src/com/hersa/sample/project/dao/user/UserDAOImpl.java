package com.hersa.sample.project.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hersa.sample.project.DuplicateUserException;
import com.hersa.sample.project.bom.DefaultConnectionProvider;
import com.hersa.sample.project.dao.util.ConnectionProvider;
import com.hersa.sample.project.utils.Constants;


public class UserDAOImpl implements UserDAO {
	final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	private String tableName = Constants.USERS;
	private Connection connection;
	private boolean setByCaller;
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updateUser(User user) throws Exception {
		String whereClause = "WHERE rowid = ?";
		String sql = UserDB.UPDATE + whereClause;
		try {
			int i = 1;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(i++, user.getFirstName());
			statement.setString(i++, user.getLastName());
			statement.setString(i++, user.getPassword());
			statement.setString(i++, user.getEmail());
			statement.setInt(i++, user.getActive());
			statement.setString(i++, user.getRole());
			statement.setString(i++, user.getCreatedBy());
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
			
			statement.setLong(i++, user.getId());
			statement.execute();
		} catch (SQLException e) {
			System.err.println("<<<<<<---- " + e.getMessage());
			throw new Exception("Could not update user.");
			
		}finally{
			DefaultConnectionProvider.closeConnection(connection);
		}
		
		
	}
	@Override
	public List<User> retrieveUserByEmail(String email) {
		List<User> userList = new ArrayList<User>();
		String whereClause = " WHERE email = ?";
		String sql = UserDB.SELECT + whereClause;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				User user = null;
				user = extractUser(results);
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
		String sql = UserDB.SELECT;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				User user = null;
				user = extractUser(results);
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
		setByCaller = true;
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
		String whereClause = " WHERE rowid = ?";
		String sql = UserDB.DELETE + whereClause;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, user.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error with data base connectivity.");
		}
	}
	@Override
	public User createUser(User user) throws Exception {
		User createdUser = null;
		String sql = UserDB.CREATE;
		try {
			int i = 1;
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(i++, user.getFirstName());
			statement.setString(i++, user.getLastName());
			statement.setString(i++, user.getPassword());
			statement.setString(i++, user.getEmail());
			statement.setString(i++, user.getRole());
			statement.setString(i++, user.getCreatedBy());
			statement.setString(i++, user.getUserName());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				createdUser = this.listUserById(rs.getInt(1));
				System.err.println(createdUser.toString());
			}
			
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
			connection.close();
		}
		return createdUser;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User obj = new User();
		int i = 1;
		obj.setId(rs.getInt(i++));
		obj.setFirstName(rs.getString(i++));
		obj.setLastName(rs.getString(i++));
		obj.setPassword(rs.getString(i++));
		obj.setEmail(rs.getString(i++));
		obj.setActive(rs.getInt(i++));
		obj.setRole(rs.getString(i++));
		obj.setCreatedBy(rs.getString(i++));
		obj.setModifiedBy(rs.getString(i++));
		obj.setModifiedDate(rs.getTimestamp(i++));
		obj.setUserName(rs.getString(i++));
		return obj;
	}
	
	@Override
	public User listUserById(int id) throws Exception {
		User user = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String whereClause = " WHERE rowid = ?";
		String sql = UserDB.SELECT + whereClause;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new Exception("Error retrieving user info.");
		}finally {
			connection.close();
		}
		return user;
	}
	@Override
	public Connection getConnection() {
		if (connection != null) {
			return connection;
		}
		return DefaultConnectionProvider.setConnectionProvider(Constants.USER_PROVIDER);
	}
	@Override
	public void setConnectionProvider(ConnectionProvider factory) {
		// TODO Auto-generated method stub
		
	}

}
