package com.techlabs.di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.di.entity.Computer;

@RestController
public class ComputerController {
	
	@Autowired
	private Computer computer;
	
	@GetMapping("/computers")
	public Computer getComputer() {
		return computer;
		
	}

}
