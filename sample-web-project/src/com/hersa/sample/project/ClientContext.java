package com.hersa.sample.project;


import javax.annotation.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.context.annotation.Scope;

import com.hersa.sample.project.bom.client.ClientManager;
import com.hersa.sample.project.bom.clientsettings.ClientSettings;
import com.hersa.sample.project.bom.clientsettings.ClientSettingsManager;
import com.hersa.sample.project.dao.client.Client;
import com.hersa.sample.project.utils.Constants;

@ManagedBean
@Scope("application") 
public class ClientContext {
	private static ClientContext instance = null;
	private Client client;
	private ClientSettings clientSettings;
	
	public ClientContext() {
		initializeClientContext();
	}

	private void initializeClientContext() {
		ClientManager cm = new ClientManager();
		ClientSettingsManager csm = new ClientSettingsManager();
		String clientId = null;
		
		try {
			Context context = new InitialContext();
			clientId = (String) context.lookup(Constants.APPLICATON_CLIENT);
			this.client = cm.retrieveClientByClientId(clientId);
			this.clientSettings = csm.retrieveClientSettingsByClientId(clientId);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public static ClientContext getInstance() {
		if (instance == null) {
			instance = new ClientContext();
		}
		return instance;
	}

	public ClientSettings getClientSettings() {
		return clientSettings;
	}

	public void setClientSettings(ClientSettings clientSettings) {
		this.clientSettings = clientSettings;
	}

}
