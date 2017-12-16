package com.hersa.sample.project.xmlconfig.testing;

import java.io.InputStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.hersa.sample.project.utils.Constants;

/**
 * @author Victor
 * This is the application client. All application configuration relies
 * on this client information.
 */
@XmlRootElement(name = "client")
public class Client {
	
	//client information
	private String id;
	private String name;
	
	//client configuration options
	
	public Client() {
		
	}
	public static Client initialize() {
		String clientId = null;
		try {
			Context context = new InitialContext();
			clientId = (String) context.lookup(Constants.APPLICATON_CLIENT);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Client client = load(clientId);
		if (client != null) {
			
		}
		return client;
	}
	
	public static Client load(String clientId)  {
		 try {
			InputStream file = Client.class.getResourceAsStream("/configuration.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Clients.class, Client.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			Clients clients = (Clients) jaxbUnmarshaller.unmarshal(file);
			for (Client client : clients.getList()) {
				if (client.getId().equals(clientId)) {
					return client;
				}
			}
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return null;
	}
	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	
	public static void main(String[] args) {
		Client.initialize();
	}

}
