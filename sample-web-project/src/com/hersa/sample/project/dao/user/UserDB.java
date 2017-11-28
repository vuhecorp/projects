package com.hersa.sample.project.dao.user;

import com.hersa.sample.project.utils.Constants;

/**
 * @author Victor
 * This class holds the CRUD statements for the users table.
 */
public class UserDB {
	
	private static String prefix = Constants.USER_PROVIDER;
	private static String tableName = prefix + "." + Constants.USERS;
	
	public static final String ID = "rowid";
	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String EMAIL = "email";
	public static final String ACTIVE = "isactive";
	public static final String ROLE = "role";
	public static final String CREATEDBY = "createdby";
	public static final String MODIFIEDBY = "modifiedby";
	public static final String MODDATE = "modifieddate";
	public static final String USERNAME = "username";
	
	private static final String ALL_COLUMNS = ID + " , " +
											  FNAME + " , " +
											  LNAME + " , " +
											  EMAIL + " , " +
											  ACTIVE + " , " +
											  ROLE + " , " +
											  CREATEDBY + " , " +
											  MODIFIEDBY + " , " +
											  MODDATE + " , " +
											  USERNAME + " ";
	
	public static final String SELECT = "SELECT " + ALL_COLUMNS +  " FROM " + tableName;
	
	public static final String UPDATE = "UPDATE " + 
											tableName + 
											" SET " +  
											FNAME + " = ?, " +
											LNAME + " = ?, " +
											EMAIL + " = ?, " +
											ACTIVE + " = ?, " +
											ROLE + " = ?, " +
											CREATEDBY + " = ?, " +
											MODIFIEDBY + " = ?, " +
											MODDATE + " = ?, " +
											USERNAME + " = ? ";

	public static final String DELETE = "DELETE FROM " + tableName;

	public static final String CREATE = "INSERT INTO " + tableName + " (" + 
																FNAME + " , " +
																LNAME + " , " + 
																EMAIL + " , " + 
																ROLE + " , " + 
																CREATEDBY + " , " + 
																USERNAME + ") "
															+ "VALUES (?,?,?,?,?,?)";

}