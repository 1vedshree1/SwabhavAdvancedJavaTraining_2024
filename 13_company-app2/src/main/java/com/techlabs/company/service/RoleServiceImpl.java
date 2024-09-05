package com.techlabs.company.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.company.dto.RoleDto;
import com.techlabs.company.entity.Role;
import com.techlabs.company.entity.User;
import com.techlabs.company.repository.RoleRepository;
import com.techlabs.company.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserRepository userRepo;
	@Override
	public RoleDto addRole(RoleDto roleDto) {
		
		Role role = toRoleMapper(roleDto);
		role = roleRepo.save(role);
		return toRoleDtoMapper(role);
	}

	

	@Override
	public RoleDto assignUsers(int roleId, List<Integer> userIds) {
		Role dbRole = roleRepo.findById(roleId).orElseThrow(()->new NullPointerException("role not found"));
		
		List<User> existingUsers = dbRole.getUsers();
		if(existingUsers==null) {
			existingUsers = new ArrayList<>();
		}
		Set<User> usersToAdd = new HashSet<>();
		for(Integer userId:userIds) {
			User user = userRepo.findById(userId).orElseThrow(()->new NullPointerException("user does not found"));
			usersToAdd.add(user);
		}
		
		existingUsers.addAll(usersToAdd);
		dbRole.setUsers(existingUsers);
		
		for(User user : usersToAdd) {
			Set<Role> userRoles = user.getRoles();
			if(userRoles == null) {
				userRoles = new HashSet<>();
			}
			userRoles.add(dbRole);
			user.setRoles(userRoles);
			userRepo.save(user);
		}
		
		Role updatedRole = roleRepo.save(dbRole);
		
		return toRoleDtoMapper(updatedRole);
	}
	
	private Role toRoleMapper(RoleDto roleDto) {
		Role role = new Role();
		
		role.setRoleName(roleDto.getRoleName());
		
		return role;
	}

	private RoleDto toRoleDtoMapper(Role role) {
		RoleDto roleDto = new RoleDto();
		
		roleDto.setRoleId(role.getRoleId());
		roleDto.setRoleName(role.getRoleName());
		return roleDto;
	}
	
}
