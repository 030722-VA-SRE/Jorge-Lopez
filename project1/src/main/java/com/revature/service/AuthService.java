package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UserDTO;
import com.revature.modals.Role;
import com.revature.modals.Users;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {

	private UserRepository userRepo;
	
	@Autowired
	public AuthService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	
	public UserDTO login(String username, String password,Role role) {
		
		Users newUser = userRepo.findUsersByuserName(username);
		
		if(newUser == null || !password.equals(newUser.getPassWord()) && !role.equals(newUser.getRole())) {
			return null;
		}
		return new UserDTO(newUser);
		
	}
	
	//
	
}
