package com.revature.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.modals.Ninja;
import com.revature.modals.Role;
import com.revature.modals.Users;
import com.revature.repositories.NinjaRepository;
import com.revature.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	private NinjaRepository ninjaRepo;
	private AuthService authService;
	//@Enumerated()
	Role employee = Role.EMPLOYEE;
	
	@Autowired
	public UserService(UserRepository userRepo,NinjaRepository ninjaRepo,AuthService authService) {
		super();
		this.userRepo = userRepo;
		this.ninjaRepo=ninjaRepo;
		this.authService = authService;
	}
	//Create customer account
	public Users addUser(Users customer) throws NoSuchAlgorithmException {
		String passwordEntered = null;
		String hashedPassword;
		if(customer.equals(userRepo.findUsersByuserName(customer.getUserName()))) {
			System.out.println("User already exists");
		}
		passwordEntered = customer.getPassWord();
		hashedPassword = authService.hashingAlgo(passwordEntered);
		customer.setPassWord(hashedPassword);
		return userRepo.save(customer);
	}
	public List<Users> getUsers(){
		return userRepo.findAll();	
	}
	
	//Employee can ADD Ninja to database 
	public Ninja addNinja(Ninja newNinja) {
		//check to see if user role is EMPLOYEE
		
		/*
		if(user.getRole().equals(userRepo.findUsersByRole(employee))) {
			
		}*/
		return ninjaRepo.save(newNinja);
	}
	
	//Employee can DELETE Ninja to database
	public boolean deleteNinjaByID(int ID,Users employee) throws Exception {
		if(employee.equals(userRepo.findUsersByRole(employee.getRole()))){
			ninjaRepo.findById(ID).orElseThrow(Exception::new);
			ninjaRepo.deleteById(ID);
			return true;
		}
		
		return false;
	}
	
}
