package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AbilityTest {
	private Ability testAbility;
	private Ability testMaxHPSpell;
	private Ability testResSpell;
	private Ability testMaxResSpell;
	private Ability testAtkSpell;
	private Ability testDefSpell;
	private Player testPlayer;
	@Before
	public void setup() {
		testAbility = new Ability("test", "test ability", "test", "HP", 5, 5);
		testMaxHPSpell = new Ability("test", "test ability", "test", "maxHP", 5, 5);
		testResSpell = new Ability("test", "test ability", "test", "resource", 5, 5);
		testMaxResSpell = new Ability("test", "test ability", "test", "maxResource", 5, 5);
		testAtkSpell = new Ability("test", "test ability", "test", "atk", 5, 5);
		testDefSpell = new Ability("test", "test ability", "test", "def", 5, 5);
		testPlayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null, null, false);
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
	public void testAffectedStatMethods() {
		assertEquals("HP", testAbility.getAffectedStat());
	}
	@Test
	public void testEffectMethods() {
		assertEquals(5, testAbility.getEffect());
	}
	@Test
	public void testCostMethods() {
		assertEquals(5, testAbility.getCost());
	}
	@Test
	public void testAddEffect() {
		testAbility.addEffect(testPlayer);
		assertEquals(105, testPlayer.getHP());
		testMaxHPSpell.addEffect(testPlayer);
		assertEquals(1005, testPlayer.getMaxHP());
		testResSpell.addEffect(testPlayer);
		assertEquals(55, testPlayer.getResource());
		testMaxResSpell.addEffect(testPlayer);
		assertEquals(205, testPlayer.getMaxResource());
		testAtkSpell.addEffect(testPlayer);
		assertEquals(15, testPlayer.getAtk());
		testDefSpell.addEffect(testPlayer);
		assertEquals(10, testPlayer.getDef());
	}
	

}
