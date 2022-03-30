package com.revature.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.UserNotFoundException;
import com.revature.modals.Role;
import com.revature.modals.Users;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {

	private UserRepository userRepo;
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	public AuthService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	private String convertToHex(final byte [] messageDigest) {
		BigInteger bigint = new BigInteger(1, messageDigest);
	    String hexText = bigint.toString(16);
	      while (hexText.length() < 32) {
	         hexText = "0".concat(hexText);
	      }
	      return hexText;
	}
	
	public String hashingAlgo(String input) throws NoSuchAlgorithmException {
		String hashedInput = null;
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte [] digestedMessage = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		hashedInput = convertToHex(digestedMessage);
		
		return hashedInput;
	}

	public String login(String username, String password,Role role) throws NoSuchAlgorithmException, UserNotFoundException {
		String hashedPassword;
		Users newUser = userRepo.findUsersByuserName(username);
		hashedPassword = hashingAlgo(password);
		if(newUser == null || !newUser.getPassWord().equals(password) && !newUser.getRole().equals(role)) {
			throw new UserNotFoundException();
			// LOG: Login failed
		}
		// LOG: login successful
		String token = newUser.getUserID() + ":" + newUser.getRole().toString();
		return token;
	}
	
	//
	public Users getUserByRole(Users user) {
		Users u = userRepo.findUsersByRole(user.getRole());
		
		if(!user.equals(u.getRole())) {
			return null;
		}
		
		return u;
		
	}
	/*
	public String generateToken(UserDTO user) {
		
		int ID = user.getUserID();
		
		
		String userID = Integer.toString(ID);
		String role = user.getRole().toString();
		
		return userID + "::" + role;
	}*/
	/*
	public void verifyCustomerToken(String token) throws UserNotFoundException {
		if(token == null) {
			throw new UserNotFoundException();
		}
		
		String [] tokenizedToken = token.split(":");
		Users principal = userRepo.findById(Integer.valueOf(tokenizedToken[0])).orElse(null);
		//int id = Integer.parseInt(tokenizedToken[1]);
		
		if(principal == null || !principal.getRole().toString().equals(tokenizedToken[1]) || !principal.getRole().toString().equals("CUSTOMER")) {
			System.out.println("Must be a customer!" + principal.getRole().toString());

			throw new UserNotFoundException();
		}
		
		LOG.info("Token verified successfully");
		
	}*/
	public boolean verifyEmployee(String token) throws UserNotFoundException {
		if(token == null) {
			throw new UserNotFoundException();
		}
		
		String [] tokenizedToken = token.split(":");
		Users principal = userRepo.findById(Integer.valueOf(tokenizedToken[0])).orElse(null);
		//int id = Integer.parseInt(tokenizedToken[1]);
		
		if(principal == null || !principal.getRole().toString().equals(tokenizedToken[1]) || !principal.getRole().toString().equals("EMPLOYEE")) {
			System.out.println("Must be an employee!" + principal.getRole().toString());
			LOG.error("Unauthorized member: " + principal.getUserID() + "is not an employee");
			throw new UserNotFoundException();
			//return false;
			
		}
		
		LOG.info("Token verified successfully");
		return true;
	}
}
