package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GearTest {
	Gear testGear;
	@Before
	public void setUp() {
		testGear = new Gear(5, 5, 5, "chestplate", false, "a simple iron chestplate", 5, false, false, false, 0, null, null);
	}
	@Test
	public void testAtkMethods() {
		testGear.setAtk(6);
		assertEquals(6, testGear.getAtk());
	}
	
	@Test
	public void testDefMethods() {
		testGear.setDef(6);
		assertEquals(6, testGear.getDef());
	}
	
	@Test
	public void testHPMethods() {
		testGear.setHP(6);
		assertEquals(6, testGear.getHP());
	}
	
	@Test
	public void testFlammableMethods() {
		testGear.setFlammable(true);
		assertTrue(testGear.getFlammable());
	}
	
	@Test
	public void testLitMethods() {
		testGear.setLit(true);
		assertFalse(testGear.getLit());
	}
	
	@Test
	public void testVarietyMethods() {
		testGear.setVariety("helmet");
		assertEquals("helmet", testGear.getVariety());
	}
	
	

}
