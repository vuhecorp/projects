package com.hersa.sample.project.dao.client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;

public interface ClientDAO {
	
	final static Log4JLogger logger = new Log4JLogger();
	
	public void setConnection(Connection connection);
	public Connection getConnection();
	public List<Client> retrieveClientById(long id) throws SQLException;
	public List<Client> retrieveClientByClientId(String clientId) throws SQLException;
	public void updateClient(Client client);
	public void deleteClient(Client client);
	public Client createClient(Client client);
	public List<Client> listAllClients();
}
