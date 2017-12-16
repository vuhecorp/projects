package com.hersa.sample.project.bom.clientsettings;

public class ClientSettings {
	
	private long id;
	private String clientId;
	private boolean dispWelcomePage;
	
	public ClientSettings() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public boolean isDispWelcomePage() {
		return dispWelcomePage;
	}
	public void setDispWelcomePage(boolean dispWelcomePage) {
		this.dispWelcomePage = dispWelcomePage;
	}
}
