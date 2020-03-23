package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


public class InventoryTest {

	Inventory testInventory;
	ArrayList<Item> inventory = null;
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
		Item potion = null;
		testInventory.addItem(potion);
		assertEquals(potion, testInventory.getInventory().get(0));
	}
	
	@Test
	public void testRemoveItem() {
		Item potion = null;
		Item sword = null;
		testInventory.addItem(potion);
		testInventory.addItem(sword);
		testInventory.removeItem(potion);
		assertEquals(sword, testInventory.getInventory().get(0));
	}

}
