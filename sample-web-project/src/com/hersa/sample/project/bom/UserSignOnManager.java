package com.hersa.sample.project.bom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hersa.sample.project.dao.usersignon.UserSignOn;
import com.hersa.sample.project.dao.usersignon.UserSignOnCreateException;
import com.hersa.sample.project.dao.usersignon.UserSignOnDeleteException;
import com.hersa.sample.project.dao.usersignon.UserSignOnFinderException;

public class UserSignOnManager extends AbstractBaseManager {

	public void createUserSignOn(UserSignOn userSignOn) throws SQLException, UserSignOnCreateException {
		this.getUserSignOnDAO().createUserSignOn(userSignOn);
	}
	
	public List<UserSignOn> retrieveAllUserSignOn() throws SQLException, UserSignOnFinderException{
		List<UserSignOn> list = this.getUserSignOnDAO().listAllUserSignOn();
		return list;
	}
	
	public void updateUserSignOn(UserSignOn userSignOn) throws SQLException, UserSignOnFinderException {
		this.getUserSignOnDAO().updateUserSignOn(userSignOn);
	}
	
	public void deleteUserSignOn(UserSignOn userSignOn) throws SQLException, UserSignOnDeleteException {
		this.getUserSignOnDAO().deleteUserSignOn(userSignOn);
	}
	public void deleteUserSignOn(long userid) throws SQLException, UserSignOnDeleteException {
		this.getUserSignOnDAO().deleteUserSignOn(userid);
	}
	public UserSignOn retrieveUserSignOnByUserId(long userid) throws SQLException, UserSignOnFinderException {
		List<UserSignOn> list = new ArrayList<UserSignOn>();
		list = this.getUserSignOnDAO().retrieveUserSignOnByUserId(userid);
		if (list.size() != 1) {
			throw new UserSignOnFinderException("Duplicat information found. Contact Admin.");
		}
		return list.get(0);
	}
}
