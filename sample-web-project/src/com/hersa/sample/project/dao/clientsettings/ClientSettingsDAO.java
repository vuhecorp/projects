package com.hersa.sample.project.dao.clientsettings;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.hersa.sample.project.bom.clientsettings.ClientSettings;

public interface ClientSettingsDAO {
	public void setConnection(Connection connection);
	public Connection getConnection();
	public ClientSettings retrieveClientSettingsByClientId(String clientId) throws SQLException;
	public List<ClientSettings> listClientSettings(String whereClause, Object[] params, int[]types, String orderBy) throws SQLException;
}
