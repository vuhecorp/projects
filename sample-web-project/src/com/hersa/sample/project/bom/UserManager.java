package com.hersa.sample.project.bom;

import java.util.ArrayList;
import java.util.List;

import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.utils.Constants;

public class UserManager extends AbstractBaseManager{
	
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
	public List<User> retrieveAllUsers(){
		List<User> userList = new ArrayList<User>();
		User[] userArray = this.getUserDAO().listAllUsers();
		if (userArray.length > 0) {
			for (int i = 0; i < userArray.length; i++) {
				userList.add(userArray[i]);
			}
		}
		return userList;
	}
	public void resetUser(User user) throws Exception{
		user.setLocked(0);
		user.setRecentUnlock(1);
		user.setFailedAttempts(0);
		this.updateUser(user);
	}

}
