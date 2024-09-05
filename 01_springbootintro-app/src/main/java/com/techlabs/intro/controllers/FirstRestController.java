package com.techlabs.intro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, welcome to springboot world";
		
	}
	
	@GetMapping("/bye")
	public String sayBye() {
		return "Bye, This was my first springBoot project";
		
	}

}
