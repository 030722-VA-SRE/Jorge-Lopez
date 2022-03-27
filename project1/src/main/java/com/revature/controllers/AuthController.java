package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.dto.UserDTO;
import com.revature.modals.Role;
import com.revature.service.AuthService;

public class AuthController {

	private AuthService authService;
	
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	
	public ResponseEntity<UserDTO> login(String username, String password, Role role){
		
		UserDTO principal = authService.login(username, password,role);
		
		if(principal == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return null;
		
	}
}
