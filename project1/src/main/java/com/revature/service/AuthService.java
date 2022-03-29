package com.revature.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	public UserDTO login(String username, String password,Role role) throws NoSuchAlgorithmException {
		String hashedPassword;
		Users newUser = userRepo.findUsersByuserName(username);
		hashedPassword = hashingAlgo(password);
		if(newUser == null || !password.equals(newUser.getPassWord()) && !role.equals(newUser.getRole())) {
			return null;
		}
		return new UserDTO(newUser);
		
	}
	
	//
	public Users getUserByRole(Users user) {
		Users u = userRepo.findUsersByRole(user.getRole());
		
		if(!user.equals(u.getRole())) {
			return null;
		}
		
		return u;
		
	}
	
	public String generateToken(UserDTO user) {
		
		int ID = user.getUserID();
		
		
		String userID = Integer.toString(ID);
		String username = user.getUsername();
		
		return userID + "" + username;
	}
}
