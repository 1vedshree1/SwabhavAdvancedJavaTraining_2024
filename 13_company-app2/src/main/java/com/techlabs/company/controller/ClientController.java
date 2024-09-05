package com.techlabs.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.company.dto.ClientDto;
import com.techlabs.company.entity.Client;
import com.techlabs.company.entity.Employee;
import com.techlabs.company.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/companyapp")
public class ClientController {
	
	@Autowired
	ClientService clientservice;
	
	@GetMapping("/clients")
	public ResponseEntity<List<ClientDto>> getAllClient(){
		return ResponseEntity.ok(clientservice.getAllClients());	
		
	}
	@GetMapping("clients/{clientId}")
	public ClientDto getClientById(@PathVariable int clientId) {
		return clientservice.getClientById(clientId);
		
	}
	
	@PostMapping("/clients")
	public ResponseEntity<ClientDto> addClients(@Valid @RequestBody ClientDto clientDto) {
		return ResponseEntity.ok(clientservice.addClient(clientDto));
		
		
	}
	
	@PutMapping("/clients/employee")
	public ResponseEntity<Client> allocateEmployees(@Valid @RequestParam int clientId, @RequestBody List<Integer>EmployeeIds) {
		return ResponseEntity.ok(clientservice.allocateEmployees(clientId, EmployeeIds));
		
		
	}
	
	

	
}
