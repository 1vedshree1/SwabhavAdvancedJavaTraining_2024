package com.techlabs.company.service;

import java.util.List;

import com.techlabs.company.dto.RoleDto;

public interface RoleService {
	
	public RoleDto addRole(RoleDto roleDto);
	
	RoleDto assignUsers(int roleId, List<Integer>UserIds);
	

}
