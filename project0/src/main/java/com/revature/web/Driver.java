package com.revature.web;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import com.revature.modal.Item;
import com.revature.service.ItemService;
import io.javalin.Javalin;

public class Driver {
	
	static ArrayList<Item> ItemList = new ArrayList<Item>(); 
	
	public static void main(String[] args) {
		 basicItemApp();
	    
	}
	 public static void basicItemApp() {
		 ItemService iS = new ItemService();
		 Javalin app = Javalin.create();
	      
	        app.start();
	        
	        app.get("/items", (ctx) -> {
	        	String hometown = ctx.queryParam("hometown");
	        	String name = ctx.queryParam("name");
	        	
	        	if(name == null) {
	        		ctx.status(404);
	        		ctx.result("Ninja with name: "+ name+ "does not exist");
	        	}
	        	if(hometown == null) {
	        		ctx.status(404);
	        		ctx.result("Village: "+ hometown+ "does not exist");
	        	}
	        	if(hometown != null && name == null) {
	        		List<Item> itemList = iS.searchForItem(hometown);
	        		ctx.json(itemList);
	        	} else if (name != null && hometown == null) {
	        		Item newItem = iS.searchForSecondCriteria(name);
	        		ctx.json(newItem);
	        	} else {
	        		ctx.json(iS.getAllProducts());
	        	}	
	        
	        });
	        
	        app.get("/item/{id}",(ctx) -> {
	        	
	        	String itemid = ctx.pathParam("id");
	        	
	        	int idNum = Integer.parseInt(itemid);
	        	Item itemByID = iS.getItemWithID(idNum);
	        	
	        	if(itemByID == null ) {
	        		ctx.status(404);
	        		ctx.result("Item: " + idNum + " does not exist");
	        		
	        	} else {
	        		ctx.status(200);
	        		ctx.json(itemByID);
	        	}
	   
	        	
	        });
	        app.post("/items", (ctx) -> {
	        	Item newItem = ctx.bodyAsClass(Item.class);
	        	
	        	int genID = iS.addItem(newItem);
	        	
	        	if(genID == -1) {
	        		ctx.status(400);
	        		ctx.result("Item: " + newItem.getItemName() + "already exists");
	        		
	        	} else {
	        		ctx.status(HttpStatus.CREATED_201);
	        		
	        	}
	
	        });
	        
	        app.put("/item/{id}", (ctx) -> {
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
