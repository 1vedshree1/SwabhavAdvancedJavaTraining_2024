package com.techlabs.company.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.company.entity.Client;
import com.techlabs.company.service.ClientService;

@RestController
@RequestMapping("/companyapp")
public class ClientController {
	@Autowired
	private ClientService clientservice;
	
	@GetMapping("/clients")
	public ResponseEntity<Page<Client>> getAllStudents(@RequestParam(required = false)String companyName,@RequestParam int pageNumber, @RequestParam int pageSize) {
		if(companyName!=null) 
			return ResponseEntity.ok(clientservice.getClientByName(companyName, pageNumber, pageSize));
		
		return ResponseEntity.ok(clientservice.getAllClients(pageNumber, pageSize));
	}
	
	
	
	@GetMapping("/clients/{clientId}")
	public ResponseEntity<Optional<Client>> getStudent(@PathVariable int clientId){
		return ResponseEntity.ok(clientservice.getClientById(clientId));
		
	}
	
	@PostMapping("/clients")
	public String addStudents(@RequestBody Client client) {
		clientservice.addClient(client);
		return "client added successfully.";
		
	}
	

}
