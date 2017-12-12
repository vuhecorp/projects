package com.hersa.sample.project.bom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;

import com.hersa.sample.project.DuplicateUserException;
import com.hersa.sample.project.dao.user.SaveUserNames;
import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.dao.user.UserDAO;
import com.hersa.sample.project.dao.usersignon.UserSignOn;
import com.hersa.sample.project.dao.usersignon.UserSignOnDAO;
import com.hersa.sample.project.dao.usersignon.UserSignOnDeleteException;


/**
 * @author Victor
 *
 */
public class UserManager extends AbstractBaseManager{
	final static Log4JLogger logger = new Log4JLogger();

	public UserManager(){
		 
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
	public void updateUser(User user) throws Exception{
		this.getUserDAO().updateUser(user);
	}
//	public void updateUserSignon(User user) throws Exception{
//		this.getUserDAO().updateUserSignonInfo(user);
//	}
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
	/**
	 * @param user
	 * @throws SQLException
	 * @throws DuplicateUserException
	 * 
	 * Create a new user. Create UserSignOn. If transaction
	 * fails, roll-back. Else, commit.
	 * 
	 */
	public void createUser(User user) throws SQLException, DuplicateUserException{
		Connection connection = this.getConnection();
		connection.setAutoCommit(false);
		boolean exception = false;
		String password = user.getPassword();
		try {
			//create user
			User newUser = null;
			UserDAO userdao = this.getUserDAO();
			userdao.setConnection(connection);
			newUser = userdao.createUser(user);
			
			//crate usersignon
			UserSignOnDAO usersignondao = this.getUserSignOnDAO();
			usersignondao.setConnection(connection);
			UserSignOn userSignOn = initUserSignOn(newUser);
			userSignOn.setPassword(password);
			usersignondao.createUserSignOn(userSignOn);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			exception = true;
		}finally {
			connection.close();
		}
		if (exception) {
			throw new DuplicateUserException("error creating user");
		}
	}
	private UserSignOn initUserSignOn(User newUser) {
		UserSignOn userSignOn = new UserSignOn();
		userSignOn.setUserid(newUser.getId());
		return userSignOn;
	}
	public void createUser(UserSignOnVO userVO) throws SQLException, DuplicateUserException{
		Connection connection = this.getConnection();
		connection.setAutoCommit(false);
		boolean exception = false;
		try {
			//create user
			User newUser = null;
			UserDAO userdao = this.getUserDAO();
			userdao.setConnection(connection);
			newUser = userdao.createUser(userVO.getUserDTO());
			
			//crate usersignon
			UserSignOnDAO usersignondao = this.getUserSignOnDAO();
			usersignondao.setConnection(connection);
			userVO.getUserSignOnDTO().setUserid(newUser.getId());
			usersignondao.createUserSignOn(userVO.getUserSignOnDTO());
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			exception = true;
		}finally {
			connection.close();
		}
		if (exception) {
			throw new DuplicateUserException("error creating user");
		}
	}

	/**
	 * @param user
	 * @throws SQLException
	 * @throws UserSignOnDeleteException
	 * 
	 * Deleted User and UserSignOn info. If exception, rollback.
	 */
	public void deleteUser(User user) throws SQLException, UserSignOnDeleteException{
		Connection connection = this.getConnection();
		connection.setAutoCommit(false);
		boolean exception = false;
		try {
			long userid = user.getId();
			this.getUserDAO().deleteUser(user);
			UserSignOnManager usm = new UserSignOnManager();
			usm.deleteUserSignOn(userid);
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			exception = true;
			
		}finally {
			connection.close();
			if (exception) {
				throw new UserSignOnDeleteException("error");
			}
		}
	}
	
//	public void resetUser(User user) throws Exception{
//		user.setLocked(0);
//		user.setRecentUnlock(1);
//		user.setFailedAttempts(0);
//		this.updateUser(user);
//	}
	

//	public List<User> retrieveByLocked(int locked){
//		List<User> list = null;
//		try {
//			list = this.getUserDAO().listByLocked(locked);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}

}
