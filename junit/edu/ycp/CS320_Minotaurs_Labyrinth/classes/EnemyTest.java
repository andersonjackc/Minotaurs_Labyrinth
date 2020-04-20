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
ArrayList<Ability> abilities = new ArrayList<Ability>();
ArrayList<Ability> abilities2 = new ArrayList<Ability>();
ArrayList<Item> items = new ArrayList<Item>();
Inventory i = new Inventory(0, 0, items);
Inventory inv = new Inventory(0, 0, items);
ArrayList<Item> Inv = new ArrayList<Item>();
Inventory testRoomInv = new Inventory(100, 100, Inv);
Item key = new Item("test", 1, true, false, false, 10, null, null, null);
Obstacle obs = new Obstacle("test", "jumping", key);
HashMap<String, Integer> testMap;
Room room = new Room("A test room", testRoomInv, obs, testMap, false, 1);

	@Before
	public void setUp() {
		abilities.add(testMaxHPSpell);
		testEnemy = new Enemy(5, 5, 5, 5, 1, 0, 0, 0, abilities, "test", "test", 1, "test", "Enemy", i, room, false);
		
		
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
		assertThat(testEnemy.rollForAction(testEnemy), anyOf(containsString("Enemy did 1 to Enemy, you now have 4 HP."), containsString("Enemy cast testMaxHPSpell it did 5 to Enemy's maxHP, you now have 10 maxHP")));
	}
	@Test
	public void testIsDeadMethods() {
		testEnemy.setIsDead(true);
		assertTrue(testEnemy.getIsDead());
	}
}
