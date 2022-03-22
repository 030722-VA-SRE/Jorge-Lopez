package com.revature.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.modal.Item;

import com.revature.persistence.ItemDao;
import com.revature.persistence.ItemPostgres;
import com.revature.exceptions.ItemNotFoundException;
//import com.revature.service.*;


public class ItemService {
	public static Logger log = LogManager.getRootLogger();
	
	private ItemDao itemDao;
	public ItemService (ItemDao mockDao) {
		itemDao = mockDao;
		
	}
	public ItemService() {
		itemDao = new ItemPostgres();

	}

	//private ItemDao itemDao;

	public int addItem(Item newItem){
		
		int id = itemDao.addItem(newItem);
		
		if(id != -1) {
			return id;
		}
		return -1;
	}
	
	public List<Item> getAllProducts() {
		return itemDao.getItems();
	}
	
	public Item getItemWithID(int id) throws ItemNotFoundException{
		if(id < 1) {
			throw new ItemNotFoundException();
		}
		
		Item item = itemDao.getItem(id);
		try {
			if(item == null) {
				throw new ItemNotFoundException();
			}
		} catch (ItemNotFoundException e) {
			log.error("Exception Thrown: Item not found with that ID");
			//log.error("Appeared in file");
		}
		return item;
		
	}
	
	public boolean updateItem(Item item) {
		
		if(item!=null) {
			itemDao.updateItem(item);
			//System.out.println("After update called in service");
			return true;
			
		}
		//System.out.println("Outside if: In service class");
		return false;
		
		
		
		//return itemDao.updateItem(item);
	}
	
	public List<Item> searchForItem(String hometown) throws ItemNotFoundException {
		List<Item> newList = itemDao.getItembyFirstCriteria(hometown);
		try {
			if(newList == null) {
				throw new ItemNotFoundException();
				
			}
		} catch(ItemNotFoundException  e) {
			log.error("Exception was thrown: Village "+hometown+ " Not Found: Try different village");
		}
		
		return newList;
	}
	
	public Item searchForSecondCriteria(String name) throws ItemNotFoundException {
		Item searchedItem = itemDao.getItembyName(name);
		try {
			if(searchedItem == null) {
				throw new ItemNotFoundException();
				
			}
		} catch (ItemNotFoundException e) {
			//log.info("Exception was thrown: " + "Item not Found");
			log.error("Exception was thrown: Item not Found - Must enter non-null string value for name");
		}
		
		return searchedItem;
		
	}
	
	
}
