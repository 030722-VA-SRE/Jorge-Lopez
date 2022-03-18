package com.revature.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.modal.Item;
import com.revature.persistence.ItemArrayList;
import com.revature.persistence.ItemDao;
import com.revature.service.ItemService;
import com.revature.util.ConnectionUtil;

import io.javalin.Javalin;


public class Driver {
	
	static ArrayList<Item> ItemList = new ArrayList<Item>(); 
	
	
	public static void main(String[] args) {
	     //dbConnection();
		 basicItemApp();
	    
	}
	
	 public static void basicItemApp() {
		
		 //ItemDao itemDao = new ItemArrayList();
		// Item item = new Item();
		 ItemService iS = new ItemService();
		 Javalin app = Javalin.create();
	        //app.get("/", ctx -> ctx.result("Hello World"));
	        
	        app.start();
	        
	        app.get("/items", (ctx) -> {
	        	
	        	//String 
	        	//itemDao.getItems();
	        	//ctx.json(ItemList);
	        	ctx.json(iS.getAllProducts());
	        });
	        
	        app.get("/item/{id}",(ctx)->{
	        	//ctx.json(Item.class);
	        	//Item item = null;
	        	String itemid = ctx.pathParam("id");
	        	
	        	int idNum = Integer.parseInt(itemid);
	        	Item itemByID = iS.getItemWithID(idNum);
	        	
	        	if(itemByID == null ) {
	        		ctx.status(404);
	        		
	        	} else {
	        		ctx.json(itemByID);
	        	}
	        	
	        	
	        	
	        	//ctx.json(iS.getItemWithID(0))
	        	
	        });
	        app.post("/items", (ctx)->{
	        	Item newItem = ctx.bodyAsClass(Item.class);
	        	
	        	//String name = ctx.queryParam(newItem.getItemName());
	        	
	        	//String description = ctx.queryParam(newItem.getDescription());
	        	
	        	
	        	//int genID = itemDao.addItem(newItem);
	        	int genID = iS.addItem(newItem);
	        	
	        	if(genID == -1) {
	        		ctx.status(400);
	        		ctx.result("Item: " + newItem.getItemName() + "already exists");
	        		
	        	} else {
	        		ctx.status(HttpStatus.CREATED_201);
	        		
	        	}
	        
	        	
	        });
	        
	        app.put("/items/{id}", (ctx)->{
	        	String itemid = ctx.pathParam("id");
	        	Item newitem = ctx.bodyAsClass(Item.class);
	        	int id = Integer.parseInt(itemid);
	        	
	        	//Item itemToSearch = iS.getItemWithID(id);
	        	//Item itemToSearch = ItemList.get(id);
	        	newitem.setItemID(newitem.getItemID());
	        	
	        	newitem.setDescription(newitem.getDescription());
	        	
	        	//iS.updateItem(newitem);
	        	/*if(itemToSearch.getItemID() != id) {
	        		ctx.status(404);
	        	} else {
	        		iS.updateItem(itemToSearch);
	        		ctx.status(200);
	        		//
	        	}*/
	        	
	        	
	        	
	        });
	        app.get("/items?someCriteria=", (ctx)->{
	        	//String search = ctx.queryParam("")
	        });
	        
	        app.get("/items?someOtherCriteria=", (ctx)->{
	        	
	        });
	 }
	 
}
