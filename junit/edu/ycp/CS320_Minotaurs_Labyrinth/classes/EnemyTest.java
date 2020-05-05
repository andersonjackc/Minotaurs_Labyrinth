package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class EnemyTest {

private Enemy testEnemy;
Ability testMaxHPSpell = new Ability("testMaxHPSpell", "test ability", "test", "maxHP", 5, 5);
Ability testHPSpell = new Ability("test", "test ability", "test", "HP", 5, 5);
Ability testMaxResourceSpell = new Ability("test", "test ability", "test", "maxResource", 5, 5);
Ability testResourceSpell = new Ability("test", "test ability", "test", "resource", 5, 5);
Ability testAtkSpell = new Ability("test", "test ability", "test", "atk", 5, 5);
Ability testDefSpell = new Ability("test", "test ability", "test", "def", 5, 5);
Ability testGodmodeSpell = new Ability("godmode", "test ability", "godmode", "godmode", 5, 5);
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
NPC testNPC = new NPC(1000, 100, 200, 50, 10, 5, 0, 0, null, null, "testDialogue", 100, "A test NPC", "test", null, room, false);


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
		testEnemy = new Enemy(1000, 100, 200, 50, 10, 5, 2, 3, abilities, "test", "test", 1, "test", "Enemy", i, room, false);
		
		
	}
	@Test
	public void testMaxHPMethods() {
		testEnemy.setMaxHP(6);
		assertEquals(6, testEnemy.getMaxHP());
		
	}
	@Test
	public void testHPMethods() {
		testEnemy.setHP(5);
		assertEquals(5, testEnemy.getHP());
	}
	@Test
	public void testMaxResourceMethods() {
		testEnemy.setMaxResource(6);
		assertEquals(6, testEnemy.getMaxResource());
		
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
		testEnemy.setAbilities(abilities2);
		assertEquals(abilities2, testEnemy.getAbilities());
	}
	@Test
	public void testInventoryMethods() {
		testEnemy.setInventory(inv);
		assertEquals(inv , testEnemy.getInventory());
	}
	@Test
	public void testDialogueMethods() {
		testEnemy.setDialogue("test1");
		assertEquals("test1", testEnemy.getDialogue());
	}
	@Test
	public void testAttitudeMethods() {
		testEnemy.setAttitude(5);
		assertEquals(5, testEnemy.getAttitude());
	}
	@Test
	public void testDescriptionMethods() {
		testEnemy.setDescription("test1");
		assertEquals("test1", testEnemy.getDescription());
	}
	@Test
	public void testNameMethods() {
		testEnemy.setName("test1");
		assertEquals("test1", testEnemy.getName());
	}
	@Test
	public void testBasicAttack() {
		String tmp = testEnemy.basicAttack(testEnemy); 
		assertEquals(95, testEnemy.getHP());
		assertEquals(testEnemy.getName() + " did " + testEnemy.getAtk() + " to " + testEnemy.getName() + ", you now have " + testEnemy.getHP() + " HP.", tmp);
		testNPC.setIsDead(true);
		tmp = testEnemy.basicAttack(testNPC);
		assertEquals("You are dead.", tmp);
		testEnemy.setIsDead(true);
		tmp = testEnemy.basicAttack(testNPC);
		assertEquals("", tmp);
		testEnemy.setIsDead(false);
		testEnemy.setAtk(1);
		testEnemy.setDef(2);
		testEnemy.basicAttack(testEnemy);
		assertEquals(95, testEnemy.getHP());
	}
	@Test
	public void testCast() {
		String tmp = testEnemy.cast(testEnemy, testMaxHPSpell);
		assertEquals(1005, testEnemy.getMaxHP());
		assertEquals(45, testEnemy.getResource());
		assertEquals(testEnemy.getName() + " cast " + testMaxHPSpell.getName() + " it did " + testMaxHPSpell.getEffect() + " to " + testEnemy.getName() + "'s " + testMaxHPSpell.getAffectedStat() + ", you now have " + testEnemy.getMaxHP() + " " + testMaxHPSpell.getAffectedStat(), tmp);
		testEnemy.cast(testEnemy, testHPSpell);
		assertEquals(105, testEnemy.getHP());
		testEnemy.cast(testEnemy, testMaxResourceSpell);
		assertEquals(205, testEnemy.getMaxResource());
		testEnemy.cast(testEnemy, testResourceSpell);
		assertEquals(35, testEnemy.getResource());
		testEnemy.cast(testEnemy, testAtkSpell);
		assertEquals(15, testEnemy.getAtk());
		testEnemy.cast(testEnemy, testDefSpell);
		assertEquals(10, testEnemy.getDef());
		testEnemy.setIsDead(true);
		tmp = testEnemy.cast(testEnemy, testGodmodeSpell);
		assertEquals(110, testEnemy.getHP());
		assertEquals(25, testEnemy.getResource());
		assertEquals(20, testEnemy.getAtk());
		assertFalse(testEnemy.getIsDead());
		assertEquals("Godmode activated.", tmp);
		testNPC.setIsDead(true);
		tmp = testEnemy.cast(testNPC, testAtkSpell);
		assertEquals("You are dead.", tmp);
		testEnemy.setResource(0);
		tmp = testEnemy.cast(testNPC, testAtkSpell);
		assertEquals(testEnemy.getName() + " doesn't have enough resource to cast " + testAtkSpell.getName(), tmp);
		testEnemy.setResource(10);
		testEnemy.setIsDead(true);
		tmp = testEnemy.cast(testNPC, testHPSpell);
		assertEquals("", tmp);

	}
	@Test
	public void testRollForAction() {
		testEnemy.setAbilities(abilities2);
		assertThat(testEnemy.rollForAction(testEnemy), anyOf(containsString("Enemy did 10 to Enemy, you now have 95 HP."), containsString("Enemy cast testMaxHPSpell it did 5 to Enemy's maxHP, you now have 1005 maxHP")));
	}
	@Test
	public void testIsDeadMethods() {
		testEnemy.setIsDead(true);
		assertTrue(testEnemy.getIsDead());
	}
	@Test
	public void testStatusMethods() {
		testEnemy.setStatus("test");
		assertEquals("test", testEnemy.getStatus());
	}
	@Test
	public void testCurrentRoomMethods() {
		testEnemy.setCurrentRoom(room2);
		assertEquals(room2.getDescription(), testEnemy.getCurrentRoom().getDescription());
	}
}
