package com.hersa.sample.project.dao.client;

public class Client {
	private long rowid;
	private String clientId;
	private String name;
	private String description;
	
	public Client(){
		
	}
	
	public long getRowid() {
		return rowid;
	}
	
	public void setRowid(long rowid) {
		this.rowid = rowid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
