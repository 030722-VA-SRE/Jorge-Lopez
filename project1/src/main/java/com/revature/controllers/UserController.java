package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.modals.Users;
import com.revature.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
		
	}
	public ResponseEntity<String> createUser(@RequestBody Users newUser){
		Users u = userService.addUser(newUser);
		return new ResponseEntity<>("User" + u.getUserName() + "was created",HttpStatus.ACCEPTED);
		
	}
	
	

}
