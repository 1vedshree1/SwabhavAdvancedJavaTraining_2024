package com.techlabs.company.service;

import java.util.List;

import com.techlabs.company.dto.UserDto;

public interface UserService {
	
	public UserDto addUsers(UserDto userDto);
	public UserDto assignRoles(int userId, List<Integer> roleIds);

}
