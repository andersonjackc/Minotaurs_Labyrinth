package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


public class InventoryTest {

	Inventory testInventory;
	ArrayList<Item> inventory = new ArrayList<Item>();
	ArrayList<Item> inventory2 = new ArrayList<Item>();
	@Before
	public void setUp() {
		
		testInventory = new Inventory(5, 5, inventory);
		
	}
	@Test
	public void testMaxStorageMethods() {
		testInventory.setMaxStorage(6);
		assertEquals(6, testInventory.getMaxStorage());
	}
	
	@Test
	public void testMaxQuantMethods() {
		testInventory.setMaxQuant(6);
		assertEquals(6, testInventory.getMaxQuant());
	}
	
	@Test
	public void testInventoryMethods() {
		
		testInventory.setInventory(inventory2);
		assertEquals(inventory2, testInventory.getInventory());
	}
	
	@Test
	public void testAddItem() {
		Item potion = new Item("test", 1, false, false, false, 0, null, null, null);
		testInventory.addItem(potion);
		assertEquals(potion, testInventory.getInventory().get(0));
		testInventory.setMaxStorage(1);
		String tmp = testInventory.addItem(potion);
		assertEquals("Your inventory is full", tmp);
	}
	
	@Test
	public void testRemoveItem() {
		Item potion = new Item("test", 1, false, false, false, 0, null, null, "HP");
		Item sword = new Item("test2", 2, false, false, false, 0, null, null, null);
		testInventory.addItem(potion);
		testInventory.addItem(sword);
		testInventory.removeItem(potion);
		assertEquals(sword, testInventory.getInventory().get(0));
		assertFalse(testInventory.getInventory().contains(potion));
		String tmp = testInventory.removeItem(potion);
		assertEquals("You don't have a " + potion.getName(), tmp);
	}

}
