package com.hersa.sample.project.dao.usersignon;

public class UserSignOnDB {
	//table name
	public static final String USERSIGNON = "usersignon";
	
	//table columns
	public static final String ROWID = "rowid";
	public static final String USERID = "userid";
	public static final String FAILEDATTEMPTS = "failedattempts";
	public static final String LASTFAILED = "lastfailed";
	public static final String LOCKED = "locked";
	public static final String LOCKEDON = "lockedon";
	public static final String FIRSTFAILED = "firstfailed";
	public static final String RECENTUNLOCK = "recentunlock";
	public static final String LASTLOGIN = 	"lastlogin";
	public static final String LASTUPDATE = "lastupdate";
	public static final String EXPIRESON = "expireson";
	public static final String PASSWORD = "password";
	
	//query statements
	
	//select
	public static final String SELECT_USERSIGNON = "SELECT "
			+ ROWID
			+ " , "
			+ USERID
			+ " , "
			+ FAILEDATTEMPTS
			+ " , "
			+ LASTFAILED
			+ " , "
			+ LOCKED
			+ " , "
			+ LOCKEDON
			+ " , "
			+ FIRSTFAILED
			+ " , "
			+ RECENTUNLOCK
			+ " , "
			+ LASTLOGIN
			+ " , "
			+ LASTUPDATE
			+ " , "
			+ EXPIRESON
			+ " , "
			+ PASSWORD
			+ " FROM " + USERSIGNON;
			
	//delete
	public static final String DELETE_USERSIGNON = "DELETE FROM " + USERSIGNON;
	
	//update
	public static final String UPDATE_USERSIGNON = "UPDATE " + USERSIGNON + " SET "
			+ FAILEDATTEMPTS + "= ?, " + LASTFAILED + "= ?, " + LOCKED + "= ?, "
			+ LOCKEDON + "= ?, " + FIRSTFAILED + "= ?, " + RECENTUNLOCK + "= ?, "
			+ LASTLOGIN + "= ?, " + LASTUPDATE + "= ?, " + EXPIRESON + "= ?, " + PASSWORD + "= ? ";
	
	//create
	public static final String CREATE_USERSIGNON = "INSERT INTO " + USERSIGNON + " (" 
					+ USERID 
					+ " , "
					+ FAILEDATTEMPTS
					+ " , "
					+ LASTFAILED
					+ " , "
					+ LOCKED
					+ " , "
					+ LOCKEDON
					+ " , "
					+ FIRSTFAILED 
					+ " , "
					+ RECENTUNLOCK
					+ " , "
					+ EXPIRESON
					+ " , "
					+ PASSWORD
					+ ") "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
}
