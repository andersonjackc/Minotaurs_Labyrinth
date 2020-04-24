package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class InventoryComparatorTest {
	Item test = new Item("test", 0, true, true, true, 0, "test", "test", "test");
	Item test1 = new Item("test1", 0, true, true, true, 0, "test1", "test1", "test1");
	ArrayList<Item> list1 = new ArrayList();
	ArrayList<Item> list2 = new ArrayList();
	Inventory inv1 = new Inventory(1, 1, list1);
	Inventory inv2 = new Inventory(1, 1, list2);
	
	InventoryComparator IC = new InventoryComparator();
	
	@Before
	public void setUp() {
		inv1.addItem(test);
		inv2.addItem(test1);
		
		
	}
	
	@Test
	public void testCompare() {
		assertEquals(1, IC.compare(inv1, inv1));
		assertEquals(-1, IC.compare(inv1, inv2));
	}

}
