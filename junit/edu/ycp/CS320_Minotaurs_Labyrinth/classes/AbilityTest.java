package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AbilityTest {
	private Ability testAbility;
	private Ability testDamageAbility;
	private Ability testMaxHPSpell;
	private Ability testResSpell;
	private Ability testMaxResSpell;
	private Ability testAtkSpell;
	private Ability testDefSpell;
	private Ability testGodmode;
	private Player testPlayer;
	@Before
	public void setup() {
		testAbility = new Ability("test", "test ability", "test", "HP", 5, 5);
		testDamageAbility = new Ability("test", "test ability", "test", "HP", -5, 5);
		testMaxHPSpell = new Ability("test", "test ability", "test", "maxHP", 5, 5);
		testResSpell = new Ability("test", "test ability", "test", "resource", 5, 5);
		testMaxResSpell = new Ability("test", "test ability", "test", "maxResource", 5, 5);
		testAtkSpell = new Ability("test", "test ability", "test", "atk", 5, 5);
		testDefSpell = new Ability("test", "test ability", "test", "def", 5, 5);
		testGodmode = new Ability("test", "test ability", "test", "godmode", 1, 1);
		testPlayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null, null, false, "test");
	}
	
	@Test
	public void testNameMethods() {
		testAbility.setName("test1");
		assertEquals("test1", testAbility.getName());
	}
	@Test
	public void testDescriptionMethods() {
		testAbility.setDescription("test ability1");
		assertEquals("test ability1", testAbility.getDescription());
		}
	@Test
	public void testVarietyMethods() {
		testAbility.setVariety("test1");
		assertEquals("test1", testAbility.getVariety());
	}
	@Test
	public void testAffectedStatMethods() {
		testAbility.setAffectedStat("maxHP");
		assertEquals("maxHP", testAbility.getAffectedStat());
	}
	@Test
	public void testEffectMethods() {
		testAbility.setEffect(6);
		assertEquals(6, testAbility.getEffect());
	}
	@Test
	public void testCostMethods() {
		testAbility.setCost(6);
		assertEquals(6, testAbility.getCost());
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
		testPlayer.setIsDead(true);
		testGodmode.addEffect(testPlayer);
		assertEquals(106, testPlayer.getHP());
		assertEquals(56, testPlayer.getResource());
		assertEquals(16, testPlayer.getAtk());
		assertFalse(testPlayer.getIsDead());
		testPlayer.setHP(5);
		testDamageAbility.addEffect(testPlayer);
		assertTrue(testPlayer.getIsDead());
	}
	

}
