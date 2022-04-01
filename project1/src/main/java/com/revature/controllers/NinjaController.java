package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.NinjaNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.modals.Ninja;
import com.revature.service.AuthService;
import com.revature.service.NinjaService;
import com.revature.service.UserService;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

	private NinjaService nS;
	private UserService uS;
	private AuthService aS;
	private Logger log = LoggerFactory.getLogger(NinjaController.class);
	
	@Autowired
	public NinjaController(NinjaService nS,UserService uS,AuthService aS) {
		//super();
		this.nS = nS;
		this.uS=uS;
		this.aS=aS;
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/ninjas")
//	@ResponseBody
	@GetMapping
	public ResponseEntity<List<Ninja>> getAllNinjas(@RequestParam(name="village", required=false) String village, @RequestParam(required=false) String jutsu,@RequestHeader(value="Authorization",required=true) String token) throws NinjaNotFoundException, UserNotFoundException{
		if(aS.verifyCustomerToken(token)) {
			if(village != null && jutsu == null) {
				return new ResponseEntity<>(nS.getNinjasByVillage(village),HttpStatus.OK);
			} else if (village == null && jutsu != null) {
			//return new Entity<>(nS.getNinjaByJutsu(jutsu),HttpStatus.OK);
				return new ResponseEntity<>(nS.getNinjaByJutsu(jutsu),HttpStatus.OK);
			} else {
			return new ResponseEntity<>(nS.getAllNinjas(),HttpStatus.OK);
			}
		} 
		log.error("Must be customer to view available items");
		return null;

	}
	
	//Adds Ninja to DB ONLY IF ROLE == EMPLOYEE
	@PostMapping
	public ResponseEntity<String> addNinja(@RequestBody Ninja newNinja, @RequestHeader(value="Authorization",required=true) String token) {
		//Users user = new Users();
		try {
			
			if(aS.verifyEmployee(token)==true) {
				uS.addNinja(newNinja);
				return new ResponseEntity<>("Ninja added to database!",HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {			
			log.error("Unable to add Ninja to Database: Must have proper authorization" );
			//log.error(null, "Error", e.printStackTrace());
		}
		
		
		return new ResponseEntity<>("Ninja can't be added: Need proper permissions",HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Ninja> getNinjaById(@PathVariable("id") int ninjaid) throws Exception{
		
		
		return new ResponseEntity<>(nS.getNinjaByID(ninjaid), HttpStatus.OK);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Ninja> updateNinja(@PathVariable("id") int id,@RequestBody Ninja ninja, @RequestHeader(value="Authorization", required=true) String token) throws Exception {
		if(aS.verifyEmployee(token)) {
			return new ResponseEntity<>(nS.updateNinjaVillage(id, ninja),HttpStatus.OK);
			
		}
		return null;
	}
	
	//Adds Ninja to DB ONLY IF ROLE == EMPLOYEE
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNinja(@PathVariable("id") int id, @RequestHeader(value = "Authorization", required=true) String token){
		try {
			if(aS.verifyEmployee(token)) {
				nS.deleteNinjaByID(id);
				return new ResponseEntity<>("Ninja was deleted successfully",HttpStatus.ACCEPTED);
			}
		} catch (UserNotFoundException | NinjaNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Unauthorized to perform this action!",HttpStatus.ACCEPTED);
	}
}
