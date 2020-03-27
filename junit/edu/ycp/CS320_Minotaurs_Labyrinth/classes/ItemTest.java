package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

private Item testItem;
	
	@Before
	public void setUp() {
		
		testItem = new Item("test", 1, true, false, 10, null);
		
	}
	
	@Test
	public void testDescriptionMethods() {
		
		assertEquals("test", testItem.getDescription());
	}
	
	@Test
	public void testEffectMethods() {
		
		assertEquals(1, testItem.getEffect());
	}
	
	@Test
	public void testFlammableMethods() {
		
		assertTrue(testItem.getFlammable());
	}
	
	@Test
	public void testLitMethods() {
		testItem.setLit(true);
		assertTrue(testItem.getLit());
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
