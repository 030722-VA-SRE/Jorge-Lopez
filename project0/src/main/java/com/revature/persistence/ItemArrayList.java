package com.revature.persistence;

import java.util.ArrayList;
import java.util.List;

import com.revature.modal.Item;

public class ItemArrayList implements ItemDao{
	
	static ArrayList<Item> itemList = new ArrayList<>();
	
	public ItemArrayList() { 
		super();
		Item i = new Item();
		i.setItemID(Item.counter);
		i.setItemName(null);
		i.setDescription(null);
		itemList.add(i);
	}
	
	public int addItem(Item newitem) {
		// TODO Auto-generated method stub
		//String name, String description
		//Item item = new Item();
		boolean doesExist = false;
		for(Item i: itemList) {
			//if(item.getItemName().equals(i.getItemName())) {
			if(newitem.getItemName().equals(i.getItemName())) {
				//return doesExist;
				doesExist = true;
				break;
			}
		}
		
		if(doesExist) {
			return -1;
		} else {
			
			//Item item = new Item(name,description);
			newitem.setItemID(Item.counter);
			itemList.add(newitem);
			return Item.counter;
			//return doesExist;
		}
		
	} 
	
	public Item getItem(int id) {		
		Item item = null;
		for(Item i: itemList) {
			if(i.getItemID() == id) {
				item = i;
				break;
			}
		}
		return item;
	}
	public boolean updateItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean deleteItem(int id) {
		// TODO Auto-generated method stub
		boolean deletedItem = false;
		for(int i = 0; i < itemList.size();i++) {
			if(itemList.get(i).getItemID() == id) {
				itemList.remove(i);
				deletedItem = true;
				
			}
		}
		
		return deletedItem;
	}
	public ArrayList<Item> getItems() {
		// TODO Auto-generated method stub
		for(int i = 0; i < itemList.size();i++) {
			System.out.println(itemList.get(i));
		}
		return itemList;
		
	}
	
	public List<Item>getItembyFirstCriteria(){
		return itemList;
	}

	@Override
	public List<Item> getItembyFirstCriteria(String hometown) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItembyName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
