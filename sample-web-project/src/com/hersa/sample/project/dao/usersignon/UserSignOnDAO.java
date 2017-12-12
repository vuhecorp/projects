package com.hersa.sample.project.dao.usersignon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;

public interface UserSignOnDAO {
	final static Log4JLogger logger = new Log4JLogger();
	public void setConnection(Connection connection);
	public Connection getConnection();
	public List<UserSignOn> retrieveUserSignOnByUserId(long userid) throws SQLException, UserSignOnFinderException;
	public void updateUserSignOn(UserSignOn userSignon) throws SQLException, UserSignOnFinderException;
	public void deleteUserSignOn(UserSignOn userSignOn) throws SQLException, UserSignOnDeleteException;
	public void deleteUserSignOn(long userid) throws SQLException, UserSignOnDeleteException;
	public void createUserSignOn(UserSignOn userSignOn) throws SQLException, UserSignOnCreateException;
	public List<UserSignOn> listByLocked(int active) throws SQLException, UserSignOnFinderException;
	public List<UserSignOn> listAllUserSignOn() throws SQLException, UserSignOnFinderException;
}
