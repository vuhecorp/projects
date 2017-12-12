package com.hersa.sample.project.bom.client;

import java.util.ArrayList;
import java.util.List;

import com.hersa.sample.project.bom.AbstractBaseManager;
import com.hersa.sample.project.dao.client.Client;

public class ClientManager extends AbstractBaseManager{
	public Client retrieveClientById(long id) throws Exception{
		List<Client> list = new ArrayList<Client>();
		list = this.getClientDAO().retrieveClientById(id);
		if (list.size() != 1) {
			throw new Exception("Duplicat information found. Contact Admin.");
		}
		return list.get(0);
	}
	
	public Client retrieveClientByClientId(String clientId) throws Exception{
		List<Client> list = new ArrayList<Client>();
		list = this.getClientDAO().retrieveClientByClientId(clientId);
		if (list.size() != 1) {
			throw new Exception("Duplicat information found. Contact Admin.");
		}
		return list.get(0);
	}
}
