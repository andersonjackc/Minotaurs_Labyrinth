package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class NPCTest {
private NPC testNPC;
Ability testMaxHPSpell = new Ability("test", "test ability", "test", "maxHP", 5, 5);
ArrayList<Ability> abilities = new ArrayList<Ability>();
ArrayList<Ability> abilities2 = new ArrayList<Ability>();
ArrayList<Item> items = new ArrayList<Item>();
Inventory i = new Inventory(0, 0, items);
Inventory inv = new Inventory(0, 0, items);
ArrayList<Item> Inv = new ArrayList<Item>();
Inventory testRoomInv = new Inventory(100, 100, Inv);
Item key = new Item("test", 1, true, false, false, 10, null, null, null);
Obstacle obs = new Obstacle("test", "jumping", key);
HashMap<String, Room> testMap;
Room room = new Room("A test room", testRoomInv, obs, testMap, false, 1);

	@Before
	public void setUp() {
		abilities.add(testMaxHPSpell);
		testNPC = new NPC(5, 5, 5, 5, 1, 5, 5, 5, abilities, "test", "test", 1, "test", "test", i, room, false);
		
	}
	@Test
	public void testMaxHPMethods() {
		testNPC.setMaxHP(6);
		assertEquals(6, testNPC.getMaxHP());
		
	}
	@Test
	public void testHPMethods() {
		testNPC.setHP(5);
		assertEquals(5, testNPC.getHP());
	}
	@Test
	public void testMaxResourceMethods() {
		testNPC.setMaxResource(6);
		assertEquals(6, testNPC.getMaxResource());
		
	}
	@Test
	public void testResourceMethods() {
		testNPC.setResource(5);
		assertEquals(5, testNPC.getResource());
	}
	@Test
	public void testAtkMethods() {
		testNPC.setAtk(5);
		assertEquals(5, testNPC.getAtk());
	}
	@Test
	public void testDefMethods() {
		testNPC.setDef(5);
		assertEquals(5, testNPC.getDef());
	}
	@Test
	public void testGoldMethods() {
		testNPC.setGold(5);
		assertEquals(5, testNPC.getGold());
	}
	@Test
	public void testXPMethods() {
		testNPC.setXP(5);
		assertEquals(5, testNPC.getXP());
	}
	@Test
	public void testAbilitiesMethods() {
		testNPC.setAbilities(abilities2);
		assertEquals(abilities2, testNPC.getAbilities());
	}
	@Test
	public void testDialogueMethods() {
		testNPC.setDialogue("test1");
		assertEquals("test1", testNPC.getDialogue());
	}
	@Test
	public void testInventoryMethods() {
		testNPC.setInventory(inv);
		assertEquals(inv , testNPC.getInventory());
	}
	@Test
	public void testAttitudeMethods() {
		testNPC.setAttitude(5);
		assertEquals(5, testNPC.getAttitude());
	}
	@Test
	public void testDescriptionMethods() {
		testNPC.setDescription("test1");
		assertEquals("test1", testNPC.getDescription());
	}
	@Test
	public void testNameMethods() {
		testNPC.setName("test1");
		assertEquals("test1", testNPC.getName());
	}
	@Test
	public void testBasicAttack() {
		testNPC.setAtk(1);
		testNPC.setHP(5);
		testNPC.basicAttack(testNPC); 
		assertEquals(4, testNPC.getHP());
	}
	@Test
	public void testCast() {
		testNPC.cast(testNPC, testMaxHPSpell);
		assertEquals(10, testNPC.getMaxHP());
		assertEquals(0, testNPC.getResource());

	}
	
	@Test
	public void testIsDeadMethods() {
		testNPC.setIsDead(true);
		assertTrue(testNPC.getIsDead());
	}
	
	@Test
	public void testRollForAction() {
		assertThat(testNPC.rollForAction(testNPC), anyOf(containsString("test did 1 to test, you now have 4 HP."), containsString("testNPC cast testMaxHPSpell it did 5 to testNPC's maxHP, you now have 5 maxHP")));
	}
}
