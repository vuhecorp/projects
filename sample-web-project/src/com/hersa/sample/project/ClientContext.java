package com.hersa.sample.project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hersa.sample.project.xmlconfig.Client;
@ManagedBean
@SessionScoped
public class ClientContext {
	Client client;
	public ClientContext() {
		this.client = ApplicationContext.getInstance().getClient();
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
