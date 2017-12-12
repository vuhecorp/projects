package com.hersa.sample.project.dao.client;

public class ClientDB {
	
	//table name
	public static final String CLIENT = "client";
	
	//columns
	public static final String ROWID = "rowid";
	public static final String CLIENTID = "clientid";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	
	//statements
	
	//select
	public static final String SELECT_CLIENT = "SELECT "
							+ ROWID
							+ ", "
							+ CLIENTID
							+ ", "
							+ NAME
							+ ", "
							+ DESCRIPTION
							+ " FROM " + CLIENT;
	
}
