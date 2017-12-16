package com.hersa.sample.project.dao.clientsettings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hersa.sample.project.bom.DefaultConnectionProvider;
import com.hersa.sample.project.bom.clientsettings.ClientSettings;
import com.hersa.sample.project.utils.Constants;


public class ClientSettingsDAOImpl implements ClientSettingsDAO{
	private Connection connection = null;
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
	public ClientSettings retrieveClientSettingsByClientId(String clientId) throws SQLException {
		PreparedStatement ps = null;
		Connection connection = null;
		ResultSet rs = null;
		
		ClientSettings settings = new ClientSettings();
		
		try {
			int i = 1;
			connection = this.getConnection();
			String sql = ClientSettingsDB.SELECT_CLIENT_SETTINGS_BY_CLIENTID;
			ps = connection.prepareStatement(sql);
			
			//where clause
			ps.setString(i++, clientId);
			rs = ps.executeQuery();
			if (rs.next()) {
				settings = extractClientSettings(rs);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new SQLException("There was an error in retrieving client settings.");
		}finally {
			if (!setByCaller) {
				connection.close();
			}
		}
		return settings;
	}

	

	@Override
	public List<ClientSettings> listClientSettings(String whereClause, Object[] params, int[] types, String orderBy)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ClientSettings extractClientSettings(ResultSet rs) throws SQLException {
		ClientSettings cs = new ClientSettings();
		
		int i = 1;
		
		cs.setId(rs.getLong(i++));
		cs.setClientId(rs.getString(i++));
		cs.setDispWelcomePage(Boolean.parseBoolean(rs.getString(i++)));
	
		return cs;
	}

}
