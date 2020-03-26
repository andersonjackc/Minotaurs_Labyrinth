package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GearTest {
	Gear testGear;
	@Before
	public void setUp() {
		testGear = new Gear(5, 5, 5, "chestplate", false, "a simple iron chestplate", 5, false, false, 0, null);
	}
	@Test
	public void testAtkMethods() {
		assertEquals(5, testGear.getAtk());
	}
	
	@Test
	public void testDefMethods() {
		
		assertEquals(5, testGear.getDef());
	}
	
	@Test
	public void testHPMethods() {
		
		assertEquals(5, testGear.getHP());
	}
	
	@Test
	public void testFlammableMethods() {
		
		assertFalse(testGear.getFlammable());
	}
	
	@Test
	public void testLitMethods() {
		testGear.setLit(true);
		assertFalse(testGear.getLit());
	}
	
	@Test
	public void testVarietyMethods() {
		
		assertEquals("chestplate", testGear.getVariety());
	}
	
	

}
