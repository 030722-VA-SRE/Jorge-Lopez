package com.revature.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.exceptions.ItemNotFoundException;
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
		
		 ItemService iS = new ItemService();
		 Javalin app = Javalin.create();
	        //app.get("/", ctx -> ctx.result("Hello World"));
	        
	        app.start();
	        
	        app.get("/items", (ctx) -> {
	        	String hometown = ctx.queryParam("hometown");
	        	String name = ctx.queryParam("name");
	        	
	        		if(hometown != null && name == null) {
		        		List<Item> newItem = iS.searchForItem(hometown);
		        		ctx.json(newItem);
		        	} else if(name != null && hometown == null) {
		        		Item nuItem = iS.searchForSecondCriteria(name);
		        		ctx.json(nuItem);
		        	}
		        	else {
		        	
		        		ctx.json(iS.getAllProducts());
		        	}
	        	
	        		
	        
	        });
	        
	        app.get("/item/{id}",(ctx)->{
	        	
	        	String itemid = ctx.pathParam("id");
	        	
	        	int idNum = Integer.parseInt(itemid);
	        	Item itemByID = iS.getItemWithID(idNum);
	        	
	        	if(itemByID == null ) {
	        		ctx.status(404);
	        		
	        	} else {
	        		ctx.status(200);
	        		ctx.json(itemByID);
	        	}
	   
	        	
	        });
	        app.post("/items", (ctx)->{
	        	Item newItem = ctx.bodyAsClass(Item.class);
	        	
	        	int genID = iS.addItem(newItem);
	        	
	        	if(genID == -1) {
	        		ctx.status(400);
	        		ctx.result("Item: " + newItem.getItemName() + "already exists");
	        		
	        	} else {
	        		ctx.status(HttpStatus.CREATED_201);
	        		
	        	}
	        
	        	
	        });
	        
	        app.put("/item/{id}", (ctx)->{
	        	String itemid = ctx.pathParam("id");
	        	Item newitem = ctx.bodyAsClass(Item.class);
	        	int id = Integer.parseInt(itemid);
	        	
	        	
	        	newitem.setItemID(newitem.getItemID());
	        	newitem.setDescription(newitem.getDescription());
	        
	        	if(id != newitem.getItemID()) {
	        		ctx.status(404);
	        	} else {
	        		iS.updateItem(newitem);
	        		ctx.status(200);
	        	}
	        	
	        	
	        	
	        	
	        });

	 }
	 
}
