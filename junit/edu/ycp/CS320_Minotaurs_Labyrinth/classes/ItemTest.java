package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

private Item testItem;
	
	@Before
	public void setUp() {
		String text = "test";
		int effect = 1;
		testItem = new Item(text, effect);
		
	}
	
	
	@Test
	public void testPrintDescription() {
		fail("Not yet implemented");
		
	}
	
	@Test
	public void testAddEffect() {
		
		fail("Not yet implemented");
	}
	

}
