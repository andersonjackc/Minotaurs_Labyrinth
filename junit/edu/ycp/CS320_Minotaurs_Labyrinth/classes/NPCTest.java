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
Ability testHPSpell = new Ability("test", "test ability", "test", "HP", 5, 5);
Ability testMaxResourceSpell = new Ability("test", "test ability", "test", "maxResource", 5, 5);
Ability testResourceSpell = new Ability("test", "test ability", "test", "resource", 5, 5);
Ability testAtkSpell = new Ability("test", "test ability", "test", "atk", 5, 5);
Ability testDefSpell = new Ability("test", "test ability", "test", "def", 5, 5);
Ability testGodmodeSpell = new Ability("godmode", "test ability", "godmode", "godmode", 5, 5);
Ability testMaxHPSpell = new Ability("testMaxHPSpell", "test ability", "test", "maxHP", 5, 5);
ArrayList<Ability> abilities = new ArrayList<Ability>();
ArrayList<Ability> abilities2 = new ArrayList<Ability>();
ArrayList<Item> items = new ArrayList<Item>();
Inventory i = new Inventory(0, 0, items);
Inventory inv = new Inventory(0, 0, items);
ArrayList<Item> Inv = new ArrayList<Item>();
Inventory testRoomInv = new Inventory(100, 100, Inv);
Item key = new Item("test", 1, true, false, false, 10, null, null, null);
Obstacle obs = new Obstacle("test", "jumping", key, "door");
HashMap<String, Integer> testMap;
Room room = new Room("A test room", testRoomInv, obs, testMap, false, 1);
Room room2 = new Room("A test room", testRoomInv, obs, testMap, false, 1);
Enemy testEnemy = new Enemy(1000, 100, 200, 50, 10, 5, 2, 3, abilities, "test", "test", 1, "test", "Enemy", i, room, false);

	@Before
	public void setUp() {
		abilities.add(testMaxHPSpell);
		abilities.add(testHPSpell);
		abilities.add(testMaxResourceSpell);
		abilities.add(testResourceSpell);
		abilities.add(testAtkSpell);
		abilities.add(testDefSpell);
		abilities.add(testGodmodeSpell);
		abilities2.add(testMaxHPSpell);
		testNPC = new NPC(100, 50, 100, 50, 6, 5, 5, 5, abilities, "test", "test", 1, "test", "NPC", i, room, false);
		
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
		String tmp = testNPC.basicAttack(testNPC); 
		assertEquals(49, testNPC.getHP());
		assertEquals(testNPC.getName() + " did 1 to " + testNPC.getName() + ", you now have " + testNPC.getHP() + " HP. ", tmp);
		testEnemy.setIsDead(true);
		tmp = testNPC.basicAttack(testEnemy);
		assertEquals("You are dead.", tmp);
		testNPC.setIsDead(true);
		tmp = testNPC.basicAttack(testEnemy);
		assertEquals("", tmp);
		testNPC.setIsDead(false);
		testNPC.setAtk(1);
		testNPC.setDef(2);
		testNPC.basicAttack(testNPC);
		assertEquals(49, testNPC.getHP());
	}
	@Test
	public void testCast() {
		String tmp = testNPC.cast(testNPC, testMaxHPSpell);
		assertEquals(105, testNPC.getMaxHP());
		assertEquals(45, testNPC.getResource());
		assertEquals(testNPC.getName() + " cast " + testMaxHPSpell.getName() + " it did " + testMaxHPSpell.getEffect() + " to " + testNPC.getName() + "'s " + testMaxHPSpell.getAffectedStat() + ", you now have " + testNPC.getMaxHP() + " " + testMaxHPSpell.getAffectedStat(), tmp);
		testNPC.cast(testNPC, testHPSpell);
		assertEquals(55, testNPC.getHP());
		testNPC.cast(testNPC, testMaxResourceSpell);
		assertEquals(105, testNPC.getMaxResource());
		testNPC.cast(testNPC, testResourceSpell);
		assertEquals(35, testNPC.getResource());
		testNPC.cast(testNPC, testAtkSpell);
		assertEquals(11, testNPC.getAtk());
		testNPC.cast(testNPC, testDefSpell);
		assertEquals(10, testNPC.getDef());
		testNPC.setIsDead(true);
		tmp = testNPC.cast(testNPC, testGodmodeSpell);
		assertEquals(60, testNPC.getHP());
		assertEquals(25, testNPC.getResource());
		assertEquals(16, testNPC.getAtk());
		assertFalse(testNPC.getIsDead());
		assertEquals("Godmode activated.", tmp);
		testEnemy.setIsDead(true);
		tmp = testNPC.cast(testEnemy, testAtkSpell);
		assertEquals("You are dead.", tmp);
		testNPC.setResource(0);
		tmp = testNPC.cast(testNPC, testAtkSpell);
		assertEquals(testNPC.getName() + " doesn't have enough resource to cast " + testAtkSpell.getName(), tmp);
		testNPC.setResource(10);
		testNPC.setIsDead(true);
		tmp = testNPC.cast(testNPC, testHPSpell);
		assertEquals("", tmp);
		}
	
	@Test
	public void testIsDeadMethods() {
		testNPC.setIsDead(true);
		assertTrue(testNPC.getIsDead());
	}
	
	@Test
	public void testRollForAction() {
		testNPC.setAbilities(abilities2);
		assertThat(testNPC.rollForAction(testNPC), anyOf(containsString("NPC did 1 to NPC, you now have 49 HP."), containsString("NPC cast testMaxHPSpell it did 5 to NPC's maxHP, you now have 105 maxHP")));
	}
	
	@Test
	public void testStatusMethods() {
		testNPC.setStatus("test");
		assertEquals("test", testNPC.getStatus());
	}
	@Test
	public void testCurrentRoomMethods() {
		testNPC.setCurrentRoom(room2);
		assertEquals(room2.getDescription(), testNPC.getCurrentRoom().getDescription());
	}
}
