package com.hersa.sample.project.bom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hersa.sample.project.DuplicateUserException;
import com.hersa.sample.project.dao.user.SaveUserNames;
import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.utils.Constants;

public class UserManager extends AbstractBaseManager{
	final static Logger logger = Logger.getLogger(UserManager.class);

	public UserManager(){
		 DefaultConnectionProvider.
			setConnectionProvider(Constants.USER_PROVIDER);
	}
	public User getUserByUsername(String email){
		List<User> list = this.getUserDAO().retrieveUserByEmail(email);
		 User user = new User();
		 if (list.size() == 1) {
			for (User user1 : list) {
				user = user1;
				return user;
			}
		}
		 return null;
	}
	public void updateUser(User user){
		this.getUserDAO().updateUser(user);
	}
	public void updateUserSignon(User user) throws Exception{
		this.getUserDAO().updateUserSignonInfo(user);
	}
	public List<User> retrieveAllUsers(){
		List<User> userList = new ArrayList<User>();
		User[] userArray = this.getUserDAO().listAllUsers();
		if (userArray.length > 0) {
			for (int i = 0; i < userArray.length; i++) {
				userList.add(userArray[i]);
			}
		}
		
		SaveUserNames saveNames = new SaveUserNames(userList);
		Thread saveThread = new Thread(saveNames);
		saveThread.start();
		return userList;
	}
	public void createUser(User user) throws SQLException, DuplicateUserException{
		this.getUserDAO().createUser(user);
	}
	public void deleteUser(User user) throws SQLException{
		this.getUserDAO().deleteUser(user);
	}
	public void resetUser(User user) throws Exception{
		user.setLocked(0);
		user.setRecentUnlock(1);
		user.setFailedAttempts(0);
		this.updateUser(user);
	}
	public List<User> retrieveByLocked(int locked){
		List<User> list = null;
		try {
			list = this.getUserDAO().listByLocked(locked);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
