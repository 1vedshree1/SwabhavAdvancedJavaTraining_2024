package com.techlabs.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.company.dto.UserDto;
import com.techlabs.company.entity.User;
import com.techlabs.company.service.UserService;

@RestController
@RequestMapping("/companyapp")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		return ResponseEntity.ok(userService.addUsers(userDto));
		
	}
	
	@PutMapping("/users/roles")
	public ResponseEntity<UserDto> assignRoles(@RequestParam int userId, @RequestBody List<Integer> roleIds){
		return ResponseEntity.ok(userService.assignRoles(userId, roleIds));
		
	}
	
	

}
