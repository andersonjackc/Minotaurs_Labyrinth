package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class EnemyTest {

private Enemy testEnemy;
Ability testMaxHPSpell = new Ability("test", "test ability", "test", "maxHP", 5, 5);
ArrayList<Ability> abilities = new ArrayList<Ability>();
ArrayList<Item> items = new ArrayList<Item>();
Inventory i = new Inventory(0, 0, items);
ArrayList<Item> Inv = new ArrayList<Item>();
Inventory testRoomInv = new Inventory(100, 100, Inv);
Item key = new Item("test", 1, true, false, false, 10, null, null, null);
Obstacle obs = new Obstacle("test", "jumping", key);
HashMap<String, Integer> testMap;
Room room = new Room("A test room", testRoomInv, obs, testMap, 1, false);

	@Before
	public void setUp() {
		abilities.add(testMaxHPSpell);
		testEnemy = new Enemy(5, 5, 5, 5, 1, 0, 0, 0, abilities, "test", "test", 1, "test", "test", i, room, false);
		
		
	}
	@Test
	public void testMaxHPMethods() {
		
		assertEquals(5, testEnemy.getMaxHP());
		
	}
	@Test
	public void testHPMethods() {
		testEnemy.setHP(5);
		assertEquals(5, testEnemy.getHP());
	}
	@Test
	public void testMaxResourceMethods() {
		
		assertEquals(5, testEnemy.getMaxResource());
		
	}
	@Test
	public void testResourceMethods() {
		testEnemy.setResource(5);
		assertEquals(5, testEnemy.getResource());
	}
	@Test
	public void testAtkMethods() {
		testEnemy.setAtk(5);
		assertEquals(5, testEnemy.getAtk());
	}
	@Test
	public void testDefMethods() {
		testEnemy.setDef(5);
		assertEquals(5, testEnemy.getDef());
	}
	@Test
	public void testGoldMethods() {
		testEnemy.setGold(5);
		assertEquals(5, testEnemy.getGold());
	}
	@Test
	public void testXPMethods() {
		testEnemy.setXP(5);
		assertEquals(5, testEnemy.getXP());
	}
	@Test
	public void testAbilitiesMethods() {
		assertEquals(abilities, testEnemy.getAbilities());
	}
	@Test
	public void testInventoryMethods() {
		
		assertEquals(i , testEnemy.getInventory());
	}
	@Test
	public void testDialogueMethods() {
		assertEquals("test", testEnemy.getDialogue());
	}
	@Test
	public void testAttitudeMethods() {
		testEnemy.setAttitude(5);
		assertEquals(5, testEnemy.getAttitude());
	}
	@Test
	public void testDescriptionMethods() {
		assertEquals("test", testEnemy.getDescription());
	}
	@Test
	public void testNameMethods() {
		assertEquals("test", testEnemy.getName());
	}
	@Test
	public void testBasicAttack() {
		testEnemy.setAtk(1);
		testEnemy.setHP(5);
		testEnemy.basicAttack(testEnemy); 
		assertEquals(4, testEnemy.getHP());
	}
	@Test
	public void testCast() {
		testEnemy.cast(testEnemy, testMaxHPSpell);
		assertEquals(10, testEnemy.getMaxHP());
		assertEquals(0, testEnemy.getResource());
	}
	@Test
	public void testRollForAction() {
		fail("Not yet implemented");
	}
	@Test
	public void testIsDeadMethods() {
		testEnemy.setIsDead(true);
		assertTrue(testEnemy.getIsDead());
	}
}
