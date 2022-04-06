package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.modals.Role;
import com.revature.modals.Users;
import com.revature.repositories.UserRepository;
import com.revature.service.AuthService;
import com.revature.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	UserRepository userRepo;
	
	@InjectMocks
	UserService userService;
	@InjectMocks
	AuthService authServ;
	
	
	
	@Test
	void getAllUsersTest() {
		List<Users> userList = new ArrayList<Users>();
		Role employee = Role.EMPLOYEE;
		Role cust = Role.CUSTOMER;
		
		Users u = new Users(1,"username","password",employee);
		Users u2 = new Users(2,"customer","custpass",cust);
		userList.add(u);
		userList.add(u2);
		
		Mockito.when(userRepo.findAll()).thenReturn(userList);
		assertEquals(userService.getUsers(),userList);
	} 
	@Test
	void addUserTest() throws NoSuchAlgorithmException {
		Role employee = Role.EMPLOYEE;
		String hashedPW = authServ.hashingAlgo("password");
		//Users u = null ;
		//u.setPassWord(hashedPW);
		Users u = new Users(1,"username",hashedPW,employee);
		Mockito.when(userRepo.save(u)).thenReturn(u);
		assertEquals(userService.addUser(u),u);

	}
	
}
