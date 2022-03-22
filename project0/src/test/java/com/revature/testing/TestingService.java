package com.revature.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.modal.Item;
import com.revature.persistence.ItemDao;
import com.revature.service.ItemService;

//import org.junit.Test;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class TestingService {
	
	static List<Item> itemList;
	static Item item;
	static ItemDao itemDao;
	
	//@Mock
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
		assertEquals(itemList,itemService.searchForItem("Leaf village"));
	}
	@Test
	public void testSearchCriteriaTwo() throws ItemNotFoundException {
		when(itemDao.getItembyName("Boruto")).thenReturn(item);
		assertEquals(item,itemService.searchForSecondCriteria("Boruto"));
	}
	

}
