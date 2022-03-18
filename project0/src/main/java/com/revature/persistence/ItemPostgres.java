package com.revature.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.modal.Item;
import com.revature.service.*;
import com.revature.util.ConnectionUtil;

public class ItemPostgres implements ItemDao {

	//Item item = new Item();
	//static List<Item> itemList = new ArrayList<>();
	
	
	//public int addItem(String name,String description) {
	@Override
	public int addItem(Item newItem) {
	
		
		int randItemID = 0;
		String sql = " INSERT INTO item (itemName,hometown,itemDescription) values (?,?,?) returning itemid";
		
		try(Connection c = ConnectionUtil.getConnectionfromProperyFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, newItem.getItemName());
			ps.setString(2, newItem.getHomeTown());
			ps.setString(3, newItem.getDescription());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				randItemID = rs.getInt("itemID");
			}
			
			
		}catch (SQLException | IOException except ) {
			except.printStackTrace();
		}
		
		return randItemID;
	}

	@Override
	public Item getItem(int id) {
		
		
		String sql = "SELECT * from item where itemid = ?";
		Item itemToRetrieve = null;
		
		try(Connection c= ConnectionUtil.getConnectionfromProperyFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				itemToRetrieve = new Item();
				itemToRetrieve.setItemID(rs.getInt("itemid"));
				itemToRetrieve.setItemName(rs.getString("itemname"));
				itemToRetrieve.setHomeTown(rs.getString("hometown"));
				itemToRetrieve.setDescription(rs.getString("itemDescription"));
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return 	itemToRetrieve;	
	}

	@Override
	public boolean updateItem(Item item) {
		
		String sql = "UPDATE item SET itemdescription = ? WHERE itemid = ? returning *";
		int changedRows = -1;
		String details = null;
		try(Connection c = ConnectionUtil.getConnectionfromProperyFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, item.getDescription());
			ps.setInt(2, item.getItemID());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			} 
			
			
			changedRows = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			//System.out.println("in catch  exception");
			e.printStackTrace();
			
		}
		
		/*if(changedRows < 1) {
			return false;
		}
		return true;*/
		return false;
	}

	@Override
	public boolean deleteItem(int id) {
		
		
		String sql = "DELETE FROM item WHERE id = ?";
		int changedRows = 0;
		
		try(Connection c = ConnectionUtil.getConnectionfromProperyFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			changedRows = ps.executeUpdate();
			
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		if(changedRows < 1) {
			return false;
		} 
		return true;
	
	}

	@Override
	public List<Item> getItems() {
		String sql = "SELECT * FROM item";
		
		List<Item> itemList = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnectionfromProperyFile()){
			Statement s = c.createStatement();
			
			ResultSet r = s.executeQuery(sql);
			
			while(r.next()) {
				Item i = new Item();
				i.setItemID(r.getInt("itemid"));
				i.setItemName(r.getString("itemname"));
				i.setHomeTown(r.getString("hometown"));
				i.setDescription(r.getString("itemdescription"));
				itemList.add(i);
			}
			
			
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemList;
	}

	@Override
	public List<Item> getItembyFirstCriteria(String hometown) {
		
		
		String sql = "SELECT * FROM item WHERE hometown = ? ";
		List<Item> itemList = new ArrayList<>();
		Item item = null;
		try(Connection c = ConnectionUtil.getConnectionfromProperyFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			
			ps.setString(1,hometown);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				item = new Item();
				
				item.setItemID(rs.getInt("itemid"));
				item.setItemName(rs.getString("itemname"));
				item.setHomeTown(rs.getString("hometown"));
				item.setDescription(rs.getString("itemdescription"));
				
				itemList.add(item);
			}
		
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
			
		return itemList;
	}
	
	//public List
	

}
