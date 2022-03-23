package com.revature.testing;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.modal.Item;
import com.revature.persistence.ItemDao;
import com.revature.service.ItemService;

@RunWith(MockitoJUnitRunner.class)
public class TestingService {
	
	static List<Item> itemList;
	static Item item;
	static ItemDao itemDao;
	
	ItemService itemService;

	@Before
	public void setup() {
		itemList = new ArrayList<Item>();
		item = new Item(1,"Boruto","Leaf village", "Naruto son");
		itemDao = mock(ItemDao.class);
		itemList.add(item);
		itemService = new ItemService(itemDao);
	}
	
	@Test
	public void testAdd() {
		when(itemDao.addItem(item)).thenReturn(1);
		assertEquals(1,itemService.addItem(item));
	}
	@Test
	public void testGetAll() {
		when(itemDao.getItems()).thenReturn(itemList);
		assertEquals(itemList,itemService.getAllProducts());
	}
	@Test
	public void testGetByID() throws ItemNotFoundException {
		when(itemDao.getItem(1)).thenReturn(item);
		assertDoesNotThrow(() -> {
			itemService.getItemWithID(1);
		});
		assertNotEquals(item,itemService.getItemWithID(2));
		assertEquals(item,itemService.getItemWithID(1));
	}
	@Test 
	public void testUpdateByID() {
		when(itemDao.updateItem(item)).thenReturn(true);
		assertEquals(true,itemService.updateItem(item));
	}
	@Test 
	public void testSearchCriteriaOne() throws ItemNotFoundException {
		when(itemDao.getItembyFirstCriteria("Leaf village")).thenReturn(itemList);
		assertDoesNotThrow(() -> {
			itemService.searchForItem("Leaf village");
		});
		
		assertNotEquals(itemList,itemService.searchForItem("Sand village"));
		assertEquals(itemList,itemService.searchForItem("Leaf village"));
	}
	@Test
	public void testSearchCriteriaTwo() throws ItemNotFoundException {
		when(itemDao.getItembyName("Boruto")).thenReturn(item);
		assertDoesNotThrow(() -> {
			itemService.searchForSecondCriteria("Boruto");
		});
		assertNotEquals(item,itemService.searchForSecondCriteria("Naruto"));
		assertEquals(item,itemService.searchForSecondCriteria("Boruto"));
	}
}
