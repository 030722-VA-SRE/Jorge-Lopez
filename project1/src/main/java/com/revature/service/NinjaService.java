package com.revature.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.revature.exceptions.NinjaNotFoundException;
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
	public Ninja getNinjaByID(int ID) throws NinjaNotFoundException {
		return ninjaRepo.findById(ID).orElseThrow(NinjaNotFoundException::new);
		//ninjaRepo.
	}

	public List<Ninja> getNinjasByVillage(@Param("village") String village) throws NinjaNotFoundException{
		if(village == null) {
			throw new NinjaNotFoundException();
		}
		
		return ninjaRepo.findByVillage(village);
	
	}
	public List<Ninja> getNinjaByJutsu(@Param("jutsu") String jutsu) {
		return ninjaRepo.findByJutsu(jutsu);
		
	}
	@Transactional
	public Ninja updateNinjaVillage(int ID, Ninja ninja) throws NinjaNotFoundException {
		//int ID = Integer.parseInt(id);
		Ninja n = ninjaRepo.findById(ID).orElseThrow(NinjaNotFoundException::new);
		
		ninja.setId(n.getId());
		
		return ninjaRepo.save(ninja);
		
	}
	@Transactional
	public void deleteNinjaByID(int ID) throws NinjaNotFoundException {
		ninjaRepo.findById(ID).orElseThrow(NinjaNotFoundException::new);
		
		ninjaRepo.deleteById(ID);
	}
	
}
