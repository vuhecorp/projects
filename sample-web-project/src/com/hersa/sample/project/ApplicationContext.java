package com.hersa.sample.project;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.hersa.sample.project.xmlconfig.Client;

@ManagedBean 	
@ApplicationScoped
public class ApplicationContext {
	
	private static ApplicationContext instance = null;
	private Client client;
	
	protected ApplicationContext() {
		this.client = Client.initialize();
	}
	
	public static ApplicationContext getInstance() {
      if(instance == null) {
         instance = new ApplicationContext();
      }
      return instance;
	 }

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
