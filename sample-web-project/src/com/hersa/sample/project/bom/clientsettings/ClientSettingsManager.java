package com.hersa.sample.project.bom.clientsettings;

import java.sql.SQLException;

import com.hersa.sample.project.bom.AbstractBaseManager;
import com.hersa.sample.project.dao.clientsettings.ClientSettingsDAO;

public class ClientSettingsManager extends AbstractBaseManager{
	public ClientSettings retrieveClientSettingsByClientId(String clientId) {
		ClientSettingsDAO dao = this.getClientSettingsDAO();
		ClientSettings settings = null;

		try {
			settings = dao.retrieveClientSettingsByClientId(clientId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return settings;
	}
}
