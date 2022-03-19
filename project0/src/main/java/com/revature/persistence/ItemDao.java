package com.revature.persistence;

import java.util.List;

import com.revature.modal.Item;

/*
 * 	This interface is responsible for declaring method related to data perisistence
 */
public interface ItemDao {
	
	//int addItem(String name,String description);
	int addItem(Item item);
	
	List<Item> getItems();
	
	Item getItem(int id);
	
	boolean updateItem(Item item);
	
	boolean deleteItem(int id);
	
	List<Item> getItembyFirstCriteria(String hometown);
	
	Item getItembyName(String name);
	

}
