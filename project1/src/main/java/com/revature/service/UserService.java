package com.revature.service;

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
	Role customer;
	
	@Autowired
	public UserService(UserRepository userRepo,NinjaRepository ninjaRepo) {
		super();
		this.userRepo = userRepo;
		this.ninjaRepo=ninjaRepo;
	}
	//Create customer account
	public Users addUser(Users user) {
		return userRepo.save(user);
	}
	//Employee can Ninja to DB 
	
	public Ninja addNinja(Ninja newNinja) {
		if(userRepo.findUsersByRole(customer) != null) {
			
		}
		return ninjaRepo.save(newNinja);
	}
	
}
