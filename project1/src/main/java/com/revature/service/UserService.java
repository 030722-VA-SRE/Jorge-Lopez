package com.revature.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.modals.CustomerTransaction;
import com.revature.modals.Ninja;
import com.revature.modals.Users;
import com.revature.repositories.NinjaRepository;
import com.revature.repositories.TransactionRepository;
import com.revature.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	private NinjaRepository ninjaRepo;
	private AuthService authService;
	private TransactionRepository transactionRepo;
	//@Enumerated()
	//Role employee = Role.EMPLOYEE;
	
	@Autowired
	public UserService(UserRepository userRepo,NinjaRepository ninjaRepo,AuthService authService,TransactionRepository transactionRepo) {
		super();
		this.userRepo = userRepo;
		this.ninjaRepo=ninjaRepo;
		this.authService = authService;
		this.transactionRepo = transactionRepo;
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
	
	//Employee can ADD Ninja to database (item shop)
	public Ninja addNinja(Ninja newNinja) {
		
		return ninjaRepo.save(newNinja);
	}
	
	//Employee can DELETE Ninja from database (item shop)
	public boolean deleteNinjaByID(int ID,Users employee) throws Exception {
		if(employee.equals(userRepo.findUsersByRole(employee.getRole()))){
			ninjaRepo.findById(ID).orElseThrow(Exception::new);
			ninjaRepo.deleteById(ID);
			return true;
		}
		
		return false;
	}
	
	//TODO:: Customer can view items purchased
	public List<CustomerTransaction> viewNinjasPurchased(int cust_id) {
		return transactionRepo.findByUser(cust_id);
		//return null;
	}
	//TODO:: Customer must be able to purchase item
	public boolean purchaseNinja(int ninja_id) {
		//get ninja from ninja table by ID 
		
		//update rows from ninja table
		
		//store ninja in object 
		
		//newNinja object is passed into transactionRepo.save()
		return false;
	}
	//TODO: Customer can view ALL AVAILABLE Items
	public List<Ninja> availableNinjas(){
		return ninjaRepo.findAll();
	}

	
}
