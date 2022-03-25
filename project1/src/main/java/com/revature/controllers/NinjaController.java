package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.modals.Ninja;
import com.revature.service.NinjaService;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

	private NinjaService nS;
	
	@Autowired
	public NinjaController(NinjaService nS) {
		//super();
		this.nS = nS;
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/ninjas")
//	@ResponseBody
	@GetMapping
	public ResponseEntity<List<Ninja>> getAllNinjas(@RequestParam(name="village", required=false) String village, @RequestParam(required=false) String jutsu){
		
		if(village != null && jutsu == null) {
			return new ResponseEntity<>(nS.getNinjasByVillage(village),HttpStatus.OK);
		} else if (village == null && jutsu != null) {
			//return new Entity<>(nS.getNinjaByJutsu(jutsu),HttpStatus.OK);
			return new ResponseEntity<>(nS.getNinjaByJutsu(jutsu),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(nS.getAllNinjas(),HttpStatus.OK);
		}
	}
	@PostMapping
	public ResponseEntity<String> addNinja(@RequestBody Ninja newNinja){
		
		nS.addNinja(newNinja);
		return new ResponseEntity<>("Ninja added to database!",HttpStatus.OK);
	}
	//TODO: Needs work
	@GetMapping("/{id}")
	public ResponseEntity<Ninja> getNinjaById(@PathVariable("id") int ninjaid){
		
		return new ResponseEntity<>(nS.getNinjaByID(ninjaid), HttpStatus.OK);
		
	}
	//TODO UPDATE
	public void updateNinja() {
		
	}
}
