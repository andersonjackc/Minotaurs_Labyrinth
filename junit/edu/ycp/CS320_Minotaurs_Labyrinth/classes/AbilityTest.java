package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AbilityTest {
	private Ability testAbility;
	@Before
	public void setup() {
		testAbility = new Ability("test", "test ability", "test", 5, 5);
	}
	
	@Test
	public void testNameMethods() {
		assertEquals("test", testAbility.getName());
	}
	@Test
	public void testDescriptionMethods() {
		assertEquals("test ability", testAbility.getDescription());
		}
	@Test
	public void testVarietyMethods() {
		assertEquals("test", testAbility.getVariety());
	}
	@Test
	public void testEffectMethods() {
		assertEquals(5, testAbility.getEffect());
	}
	@Test
	public void testCostMethods() {
		assertEquals(5, testAbility.getCost());
	}

}
