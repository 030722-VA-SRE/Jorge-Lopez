package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.revature.modals.Ninja;
import com.revature.repositories.NinjaRepository;

@Service
public class NinjaService {

	
	private NinjaRepository ninjaRepo;
	
	@Autowired
	public NinjaService(NinjaRepository ninjaRepo) {
		super();
		this.ninjaRepo = ninjaRepo;
	}
	// Gets All Ninjas in Database
	public List<Ninja> getAllNinjas(){
		return ninjaRepo.findAll();
	}
	// Adds/Creates new Ninja in Database
	public Ninja addNinja(Ninja newNinja) {
		
		return ninjaRepo.save(newNinja);
	}
	// Get Ninja based on ID
	public Ninja getNinjaByID(int ID) {
		return ninjaRepo.getById(ID);
		//ninjaRepo.
	}

	public List<Ninja> getNinjasByVillage(@Param("village") String village){
		return ninjaRepo.findByVillage(village);
	
	}
	public List<Ninja> getNinjaByJutsu(@Param("jutsu") String jutsu) {
		return ninjaRepo.findByJutsu(jutsu);
		
	}
	public boolean updateNinjaVillage(@Param("id")String id) {
		
		Ninja ninja = new Ninja();
		if(id.equals(ninja.getId()));
		
		ninjaRepo.save(ninja);
		
		return true;
	}
	
}
