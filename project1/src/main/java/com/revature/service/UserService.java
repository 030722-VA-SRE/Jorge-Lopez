package com.revature.service;

import javax.persistence.Enumerated;

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
	//@Enumerated()
	Role employee = Role.EMPLOYEE;
	
	@Autowired
	public UserService(UserRepository userRepo,NinjaRepository ninjaRepo) {
		super();
		this.userRepo = userRepo;
		this.ninjaRepo=ninjaRepo;
	}
	//Create customer account
	public Users addUser(Users customer) {
		
		if(customer.equals(userRepo.findUsersByuserName(customer.getUserName()))) {
			System.out.println("User already exists");
		}
		
		return userRepo.save(customer);
	}
	
	//Employee can ADD Ninja to database 
	public Ninja addNinja(Ninja newNinja, Users user) {
		//check to see if user role is EMPLOYEE
		if(user.equals(userRepo.findUsersByRole(user.getRole()))) {
			System.out.println("User is: " + user.getRole());
		}
		/*
		if(user.getRole().equals(userRepo.findUsersByRole(employee))) {
			
		}*/
		return ninjaRepo.save(newNinja);
	}
	
	//Employee can DELETE Ninja to database
	public boolean deleteNinjaByID(int ID) throws Exception {
		//if(ninjaRepo.getById(ID))
		ninjaRepo.findById(ID).orElseThrow(Exception::new);
		
		ninjaRepo.deleteById(ID);
		return true;
	}
	
	
	
	
}
