package com.hersa.sample.project.xmlconfig.testing;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "clients")
public class Clients {
	
	private List<Client> list = null;
	public Clients() {
		
	}
	public Clients(List<Client> list) {
		this.list = list;
	}
	public List<Client> getList() {
		return list;
	}
	@XmlElement(name = "client")
	public void setList(List<Client> list) {
		this.list = list;
	}

}