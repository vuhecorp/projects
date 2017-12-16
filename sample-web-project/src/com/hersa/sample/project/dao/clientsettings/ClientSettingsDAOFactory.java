package com.hersa.sample.project.dao.clientsettings;

public class ClientSettingsDAOFactory {
	
	public ClientSettingsDAOFactory() {
		
	}
	public static ClientSettingsDAOImpl getDAO() {
		return new ClientSettingsDAOImpl();
	}
}
