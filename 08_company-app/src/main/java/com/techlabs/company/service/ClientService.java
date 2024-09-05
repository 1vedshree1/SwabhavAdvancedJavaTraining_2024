package com.techlabs.company.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.techlabs.company.entity.Client;



public interface ClientService {
	
	public Page<Client> getAllClients( int pageNumber,int pageSize);
	public Optional<Client> getClientById(int clientId);
	public void addClient(Client client);
	public Page<Client> getClientByName(String companyName, int pageNumber,int pageSize);

}
