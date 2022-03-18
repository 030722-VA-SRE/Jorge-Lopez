package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.modal.Item;
import com.revature.persistence.ItemArrayList;
import com.revature.persistence.ItemDao;
import com.revature.persistence.ItemPostgres;
//import com.revature.service.*;
import com.revature.modal.*;

public class ItemService {
	
	public ItemService() {
		//super();
	}
	
	
	static ArrayList<Item> ItemList = new ArrayList<Item>(); 
	
	 ItemDao itemDao = new ItemPostgres();
	 
		
	//TODO: Method to add an item to List
	public int addItem(Item newItem){
		
		int id = itemDao.addItem(newItem);
		
		if(id != -1) {
			return id;
		}
		//return Item.counter;
		return -1;
	}
	
	
	public List<Item> getAllProducts() {
		return itemDao.getItems();
		
		//return ItemList;
	}
	
	public Item getItemWithID(int id){
		if(id < 1) {
			return null;
		}
		
		Item item = null;
		item = itemDao.getItem(id);
		
		return item;
		
	}
	
	//TODO: Method to update item based on ID given
	public boolean updateItem(Item item) {
		/*
		if(item!=null) {
			itemDao.updateItem(item);
			return true;
		}
		return false;
		
		*/
		
		return itemDao.updateItem(item);
	}
	
	//TODO: Method to search for certain criteria
	public void searchForItem() {
		
	}
	
	public static void main(String [] args) {
		//System.out.println("1st Project running off dribble!");
		//Item item = new Item();
		//ItemService product = new ItemService();
		//ArrayList<Item> products = new ArrayList<Item>(); 
		//System.out.println("List should appear below");
		//product.addItem("Macbook Air");
		//product.addItem("Headset");
		//System.out.println("Add Item method: adds item by name to ArrayList");
		//ItemService item = (ItemService) new Item();
		//product.addItem(item.getItemName());
		//product.addItem("Naruto", "Hokage of Leaf Village");
		//product.addItem("Sasuke", "Part of the Uchiha Clan");
		//product.addItem("Shikamaru", "Master Strategist");

		//System.out.println("getAllProducts is called: Gets all items from ArrayList");
		//product.getAllProducts(ItemList);
		//System.out.println();
		
		//product.getItemWithID(item.getItemID());
		
		//item.
	}
}
