package com.hersa.sample.project.dao.usersignon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserSignOnDAOImpl implements UserSignOnDAO{
	private Connection connection;
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Connection getConnection() {
		
		return connection;
	}
	@Override
	public void updateUserSignOn(UserSignOn userSignon) throws SQLException, UserSignOnFinderException {
		PreparedStatement stmt = null;
		String whereClause = " WHERE userid = ?";
		try {
			int i = 1;
			String sql = UserSignOnDB.UPDATE_USERSIGNON + whereClause;
			stmt = connection.prepareStatement(sql);
			stmt.setInt(i++, userSignon.getFailedAttempts());
			
			if (userSignon.getLastFailed() != null) {
				Timestamp stamp = new Timestamp(userSignon.getLastFailed().getTime());
				stmt.setTimestamp(i++,  stamp);
			}else {stmt.setTimestamp(i++, null);}
			
			stmt.setInt(i++,  userSignon.getLocked());
			
			if (userSignon.getLockedOn() != null) {
				Timestamp stamp = new Timestamp(userSignon.getLockedOn().getTime());
				stmt.setTimestamp(i++,  stamp);
			}else {stmt.setTimestamp(i++, null);}
			
			if (userSignon.getFirstFailed() != null) {
				Timestamp stamp = new Timestamp(userSignon.getFirstFailed().getTime());
				stmt.setTimestamp(i++,  stamp);
			}else {stmt.setTimestamp(i++, null);}
			
			stmt.setInt(i++, userSignon.getRecentUnlock());
			
			if (userSignon.getLastLogin() != null) {
				Timestamp stamp = new Timestamp(userSignon.getLastLogin().getTime());
				stmt.setTimestamp(i++,  stamp);
			}else {stmt.setTimestamp(i++, null);}
			
			//set whereClause param
			stmt.setLong(i++, userSignon.getUserid());
			stmt.execute();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UserSignOnFinderException();
		}finally {
			connection.close();
		}
	}

	@Override
	public void deleteUserSignOn(UserSignOn userSignOn) throws SQLException, UserSignOnDeleteException {
		String whereClause = " WHERE userid = ?";
		PreparedStatement stmt = null;
		try {
			String sql = UserSignOnDB.DELETE_USERSIGNON + whereClause;
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, userSignOn.getUserid());
			stmt.execute();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
			throw new UserSignOnDeleteException();
		}finally {
			connection.close();
		}
	}
	@Override
	public void deleteUserSignOn(long userid) throws SQLException, UserSignOnDeleteException {
		String whereClause = " WHERE userid = ?";
		PreparedStatement stmt = null;
		try {
			String sql = UserSignOnDB.DELETE_USERSIGNON + whereClause;
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, userid);
			stmt.execute();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
			throw new UserSignOnDeleteException();
		}finally {
			connection.close();
		}
	}

	@Override
	public void createUserSignOn(UserSignOn userSignOn) throws SQLException, UserSignOnCreateException {
		PreparedStatement stmt = null;
		
		try {
			int i = 1;
			stmt = connection.prepareStatement(UserSignOnDB.CREATE_USERSIGNON);
			stmt.setLong(i++, userSignOn.getUserid());
			stmt.setInt(i++, userSignOn.getFailedAttempts());
			
			if (userSignOn.getLastFailed() != null) {
				Timestamp stamp = new Timestamp(userSignOn.getLastFailed().getTime()); 
				stmt.setTimestamp(i++, stamp);
			}else {
				stmt.setTimestamp(i++, null);
			}
			
			stmt.setInt(i++, 0);
			
			if (userSignOn.getLockedOn() != null) {
				Timestamp stamp = new Timestamp(userSignOn.getLockedOn().getTime()); 
				stmt.setTimestamp(i++, stamp);
			}else {
				stmt.setTimestamp(i++, null);
			}
			
			if (userSignOn.getFirstFailed() != null) {
				Timestamp stamp = new Timestamp(userSignOn.getFirstFailed().getTime()); 
				stmt.setTimestamp(i++, stamp);
			}else {
				stmt.setTimestamp(i++, null);
			}
		
			stmt.setInt(i++, 0);
			stmt.execute();
			
		} catch (SQLException e) {
			if (e.getMessage().contains("UNIQUE")) {
				throw new UserSignOnCreateException("Dupicate entry.");
			}else {
				throw new UserSignOnCreateException();
			}
			
		}finally {
			connection.close();
		}
	}
	
	@Override
	public List<UserSignOn> retrieveUserSignOnByUserId(long userid) throws SQLException, UserSignOnFinderException {
		List<UserSignOn> list = null;
		String whereClause = " WHERE userid = ?";
		Object[] params = {userid};
		int[] paramTypes = {Types.INTEGER};
		try {
			list = listUserSignOn(whereClause, params, paramTypes, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	@Override
	public List<UserSignOn> listByLocked(int active) throws SQLException, UserSignOnFinderException {
		List<UserSignOn> list = null;
		String whereClause = " WHERE active = ?";
		Object[] params = {active};
		int[] paramTypes = {Types.INTEGER};
		try {
			list = listUserSignOn(whereClause, params, paramTypes, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<UserSignOn> listAllUserSignOn() throws SQLException, UserSignOnFinderException {
		List<UserSignOn> list = null;
		try {
			list = listUserSignOn(null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<UserSignOn> listUserSignOn(String whereClause, Object[] params, int[]types, String orderBy) throws Exception, 
	UserSignOnFinderException, SQLException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserSignOn> list = new ArrayList<UserSignOn>();
		
		if (whereClause == null) {
			whereClause = "";
		}
		if (orderBy == null) {
			orderBy = "";
		}
		
		try {
			String sql = UserSignOnDB.SELECT_USERSIGNON + whereClause;
			stmt = connection.prepareStatement(sql);
			if (!whereClause.isEmpty()) {
				try {
					for (int i = 0; i < params.length; i++) {
						stmt.setObject(i+1, params[i], types[i]);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new UserSignOnFinderException("Error setting params.");
				}
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserSignOn uso = new UserSignOn();
				uso = extractUserSignOn(rs);
				list.add(uso);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
		return list;
	}

	private UserSignOn extractUserSignOn(ResultSet rs) throws SQLException {
		UserSignOn obj = new UserSignOn();
		
		int i = 1;
		obj.setRowid(rs.getLong(i++));
		obj.setUserid(rs.getLong(i++));
		obj.setFailedAttempts(rs.getInt(i++));
		obj.setLastFailed(rs.getTimestamp(i++));
		obj.setLocked(rs.getInt(i++));
		obj.setLockedOn(rs.getTimestamp(i++));
		obj.setFirstFailed(rs.getTimestamp(i++));
		obj.setRecentUnlock(rs.getInt(i++));
		obj.setLastLogin(rs.getTimestamp(i++));
		
		return obj;
	}
}
