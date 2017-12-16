package com.hersa.sample.project.dao.clientsettings;

public class ClientSettingsDB {

	//table name
	public static final String CLIENT_SETTINGS = "clientsettings";
	
	//columns
	public static final String ID = "rowid"; 
	public static final String CLIENTID = "clientid";
	public static final String DISPAY_WELCOME_PAGE = "dispwelcomepage";

	//query statements
	
	//select
	public static final String SELECT_CLIENT_SETTINGS_BY_CLIENTID = " SELECT "
														+ ID
														+ ", "
														+ CLIENTID
														+ ", "
														+ DISPAY_WELCOME_PAGE
														+ " FROM "
														+ CLIENT_SETTINGS
														+ " WHERE "
														+ CLIENTID + " = ?";

			

}
