package com.techlabs.company.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.company.dto.UserDto;
import com.techlabs.company.entity.Role;
import com.techlabs.company.entity.User;
import com.techlabs.company.repository.RoleRepository;
import com.techlabs.company.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Override
	public UserDto addUsers(UserDto userDto) {
		User user = toUserMapper(userDto);
		user = userRepo.save(user);
		return toUserDtoMapper(user);
	}

	

	@Override
	public UserDto assignRoles(int userId, List<Integer> roleIds) {
		User userdb = userRepo.findById(userId).orElseThrow(() -> new NullPointerException("User does not exist"));
		
		Set<Role> existingRoles = userdb.getRoles();
		if(existingRoles==null)
			existingRoles = new HashSet<>();
		
		Set<Role> rolesToadd = new HashSet<>();
		for(Integer roleId:roleIds) {
			
			Role role = roleRepo.findById(roleId).orElseThrow(()->new NullPointerException("roles does not exist"));
			
			rolesToadd.add(role);
			
		}
		
		existingRoles.addAll(rolesToadd);
		userdb.setRoles(existingRoles);
		
		User updatedUser =userRepo.save(userdb);
		return toUserDtoMapper(updatedUser);
		 	
	}
	
	private User toUserMapper(UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto toUserDtoMapper(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		return userDto;
	
	}

}
