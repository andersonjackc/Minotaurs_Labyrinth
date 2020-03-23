package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


public class InventoryTest {

	Inventory testInventory;
	ArrayList<Item> inventory = new ArrayList<Item>();
	@Before
	public void setUp() {
		
		testInventory = new Inventory(5, 5, inventory);
		
	}
	@Test
	public void testMaxStorageMethods() {
		
		assertEquals(5, testInventory.getMaxStorage());
	}
	
	@Test
	public void testMaxQuantMethods() {
		
		assertEquals(5, testInventory.getMaxQuant());
	}
	
	@Test
	public void testInventoryMethods() {
		
		
		assertEquals(inventory, testInventory.getInventory());
	}
	
	@Test
	public void testAddItem() {
		Item potion = new Item("test", 1);
		testInventory.addItem(potion);
		assertEquals(potion, testInventory.getInventory().get(0));
	}
	
	@Test
	public void testRemoveItem() {
		Item potion = new Item("test", 1);
		Item sword = new Item("test2", 2);
		testInventory.addItem(potion);
		testInventory.addItem(sword);
		testInventory.removeItem(potion);
		assertEquals(sword, testInventory.getInventory().get(0));
	}

}
