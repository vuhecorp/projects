package com.hersa.sample.project;


import javax.annotation.ManagedBean;

import org.springframework.context.annotation.Scope;

import com.hersa.sample.project.bom.client.ClientManager;
import com.hersa.sample.project.dao.client.Client;

@ManagedBean
@Scope("session") 
public class ClientContext {
	private static ClientContext instance = null;
	private Client client;
	
	public ClientContext() {
		//this.client = Client.initialize();
		ClientManager cm = new ClientManager();
		try {
			this.client = cm.retrieveClientByClientId("10745");
			if (client != null) {
				System.out.println(client.getName());
				System.out.println(client.getDescription());
			}else {
				System.out.println("failed..");
			}
		} catch (Exception e) {
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

}
