package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemComparatorTest {
	Item test = new Item("test", 0, true, true, true, 0, "test", "test", "test");
	Item test1 = new Item("test1", 0, true, true, true, 0, "test1", "test1", "test1");
	ItemComparator IC = new ItemComparator();
	
	@Test
	public void testCompare() {
		assertEquals(1, IC.compare(test, test));
		assertEquals(-1, IC.compare(test, test1));
	}

}
