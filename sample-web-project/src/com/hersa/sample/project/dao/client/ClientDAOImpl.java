package com.hersa.sample.project.dao.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.hersa.sample.project.bom.DefaultConnectionProvider;
import com.hersa.sample.project.dao.usersignon.UserSignOnFinderException;
import com.hersa.sample.project.utils.Constants;

public class ClientDAOImpl implements ClientDAO {
	private Connection connection;
	private boolean setByCaller;
	
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		setByCaller = true;
	}

	@Override
	public Connection getConnection() {
		if (setByCaller) {
			return connection;
		}
		connection = DefaultConnectionProvider.setConnectionProvider(Constants.USER_PROVIDER);
		return connection;
	}
	
	@Override
	public List<Client> retrieveClientByClientId(String clientId) throws SQLException {
		List<Client> list = null;
		String whereClause = " WHERE clientid = ?";
		Object[] params = {clientId};
		int[] paramTypes = {Types.VARCHAR};
		try {
			list = listClient(whereClause, params, paramTypes, null);
		} catch (SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public List<Client> retrieveClientById(long id) throws SQLException {
		List<Client> list = null;
		String whereClause = " WHERE rowid = ?";
		Object[] params = {id};
		int[] paramTypes = {Types.INTEGER};
		try {
			list = listClient(whereClause, params, paramTypes, null);
		} catch (SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
		return list;
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client createClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> listAllClients() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Client> listClient(String whereClause, Object[] params, int[]types, String orderBy) throws Exception, 
	UserSignOnFinderException, SQLException{
		PreparedStatement stmt = null;
		Connection con = this.getConnection();
		ResultSet rs = null;
		List<Client> list = new ArrayList<Client>();
		
		if (whereClause == null) {
			whereClause = "";
		}
		if (orderBy == null) {
			orderBy = "";
		}
		
		try {
			String sql = ClientDB.SELECT_CLIENT + whereClause;
			stmt = con.prepareStatement(sql);
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
				Client client = new Client();
				client = extractClient(rs);
				list.add(client);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new SQLException("There was an error in retrieving user sign on information");
		}finally {
			if (!setByCaller) {
				connection.close();
			}
		}
		return list;
	}
	private Client extractClient(ResultSet rs) throws SQLException {
		Client obj = new Client();
		
		int i = 1;
		obj.setRowid(rs.getLong(i++));
		obj.setClientId(rs.getString(i++));
		obj.setName(rs.getString(i++));
		obj.setDescription(rs.getString(i++));
		return obj;
	}



}
