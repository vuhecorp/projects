package com.hersa.sample.project.dao.userprofile;


import java.sql.Connection;
import java.sql.SQLException;
public interface UserProfileDAO{
	public void setConnection(Connection connection);
	public void updateUserProfile(UserProfile userProfile) throws SQLException;
	public void deleteUserProfile(UserProfile userProfile) throws SQLException;
	public void createUserProfile(UserProfile userProfile) throws SQLException;
	public UserProfile[] listAllUserProfiles();
	}