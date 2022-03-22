package com.revature.modal;

import java.util.ArrayList;
import java.util.Objects;

public class Item {
	
	private int ItemID;
	private String ItemName;
	private String homeTown;
	private String description;
	//private ArrayList<Item> ItemList;
	public static int counter;
	
	public int getItemID() {
		return ItemID;
	}
	public void setItemID(int ItemID) {
		this.ItemID = ItemID;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String ItemName) {
		this.ItemName = ItemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	

	
	public Item() {
		ItemID = counter;
		counter++;
	}
	/*
	
	public Item(int ItemID,String name,String description) {
		
		this.ItemName = name;
		this.ItemID=ItemID;
		this.description = description;
	}
	*/
	public Item(int id, String name,String hometown,String description) {
		this();
		this.ItemName = name;
		this.ItemID=id;
		this.description = description;
		this.homeTown = hometown;
				
	}
	@Override
	public int hashCode() {
		return Objects.hash(ItemID, ItemName, description);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(ItemID, other.ItemID)
				&& Objects.equals(ItemName, other.ItemName) && Objects.equals(description, other.description);
	}
	@Override
	public String toString() {
		return "Item (ItemID: " + ItemID + ", ItemName: " + ItemName 
				+ ", Description: " + description + ")";
	}
	
	
	
	/*
	public static void main(String [] args) {
		//System.out.println("1st Project running off dribble!");
	}*/

}
