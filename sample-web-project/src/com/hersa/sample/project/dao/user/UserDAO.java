package com.hersa.sample.project.dao.user;

import java.sql.Connection;
import java.util.List;

public interface UserDAO {

	public void setConnection(Connection connection);
	public List<User> retrieveUserByEmail(String Username);
	public void updateUser(User user);
	public void deleteUser(User user);
	public User[] listAllUsers();
}
