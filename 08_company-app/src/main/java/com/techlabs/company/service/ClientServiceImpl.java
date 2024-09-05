package com.techlabs.company.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.company.entity.Client;
import com.techlabs.company.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	private ClientRepository clientRepo;

	@Override
	public Page<Client> getAllClients(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		// TODO Auto-generated method stub
		return clientRepo.findAll(pageable);
	}

	@Override
	public Optional<Client> getClientById(int clientId) {
		// TODO Auto-generated method stub
		return clientRepo.findById(clientId);
	}

	@Override
	public void addClient(Client client) {
		// TODO Auto-generated method stub
		clientRepo.save(client);
		
	}

	@Override
	public Page<Client> getClientByName(String companyName, int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		// TODO Auto-generated method stub
		return clientRepo.findByCompanyName(companyName, pageable);
	}

}
