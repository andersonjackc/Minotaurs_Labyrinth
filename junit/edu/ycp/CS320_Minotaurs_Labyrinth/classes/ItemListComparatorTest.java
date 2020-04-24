package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ItemListComparatorTest {

	Item test = new Item("test", 0, true, true, true, 0, "test", "test", "test");
	Item test1 = new Item("test1", 0, true, true, true, 0, "test1", "test1", "test1");
	ArrayList<Item> list1 = new ArrayList();
	ArrayList<Item> list2 = new ArrayList();
	ArrayList<Item> list3 = new ArrayList();
	ArrayList<Item> list4 = new ArrayList();
	ItemListComparator IC = new ItemListComparator();
	
	@Before
	public void setUp() {
		list1.add(test);
		list2.add(test);
		list3.add(test1);
		list4.add(test);
		list4.add(test1);
		
	}

	@Test
	public void testCompare() {
		assertEquals(1, IC.compare(list1, list2));
		assertEquals(-1, IC.compare(list1, list3));
		assertEquals(-1, IC.compare(list1, list4));
	}

}
