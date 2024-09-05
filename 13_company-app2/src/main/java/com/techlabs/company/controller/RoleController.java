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

import com.techlabs.company.dto.RoleDto;
import com.techlabs.company.service.RoleService;

@RestController
@RequestMapping("/companyapp")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/roles")
	public ResponseEntity<RoleDto> addRoles(@RequestBody RoleDto roleDto){
		return ResponseEntity.ok(roleService.addRole(roleDto));
		
	}
	
	@PutMapping("/roles/users")
	public ResponseEntity<RoleDto> assignUser(@RequestParam int roleId, @RequestBody List<Integer>userIds){
		return ResponseEntity.ok(roleService.assignUsers(roleId, userIds));
		
	}
	


}
