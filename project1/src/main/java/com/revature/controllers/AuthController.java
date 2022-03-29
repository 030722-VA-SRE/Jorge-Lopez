package com.revature.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.UserDTO;
import com.revature.modals.Role;
import com.revature.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthService authService;
	
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> login(@RequestParam("username") String username,@RequestParam("password") String password, @RequestParam("role") Role role) throws NoSuchAlgorithmException{
		
		UserDTO principal = authService.login(username, password,role);
		
		if(principal == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		HttpHeaders header = new HttpHeaders();
		// Add token or session cookie
		
		String token = authService.generateToken(principal);
		
		header.set("Authorization", token);
		return new ResponseEntity<>(principal,header,HttpStatus.ACCEPTED);
		
	}
}
