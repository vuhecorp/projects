package com.hersa.sample.project.bom;

import com.hersa.sample.project.dao.user.UserDAO;
import com.hersa.sample.project.dao.user.UserDAOImpl;
import com.hersa.sample.project.utils.Constants;

public class AbstractBaseManager {

	public AbstractBaseManager() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDAO getUserDAO(){
		UserDAO dao = new UserDAOImpl(); 
		DefaultConnectionProvider.setConnectionProvider(Constants.USER_PROVIDER);
		dao.setConnection(DefaultConnectionProvider.getConnection());
		
		return dao;		
	}
}
