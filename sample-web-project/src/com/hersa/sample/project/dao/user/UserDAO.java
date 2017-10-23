package com.hersa.sample.project.dao.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

public interface UserDAO {
	final static Logger logger = Logger.getLogger(UserDAO.class);

	public void setConnection(Connection connection);
	public List<User> retrieveUserByEmail(String Username);
	public void updateUser(User user);
	public void updateUserSignonInfo(User user);
	public void deleteUser(User user) throws SQLException;
	public void createUser(User user) throws SQLException;
	public List<User> listByLocked(int active) throws SQLException;
	public User[] listAllUsers();
}
