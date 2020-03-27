package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest {

	//Set up a test Player obj
	ArrayList<Item> Inv = new ArrayList<Item>();
	Inventory testInv = new Inventory(100, 100, Inv);
	Player testPlayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, testInv);
	NPC testNPC = new NPC(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null, 0, "A test NPC", "test", null);
	Item testItem = new Item("A test item", 5, true, false, 50, "testItem");
	Room room = new Room("A test room");
	ArrayList<Item> Inventory = new ArrayList<Item>();
	Inventory testInventory = new Inventory(100, 100, Inventory);
	Item testPotion = new Item("test", 10, false, false, 5, "health potion");
	Item testTorch = new Item("test", 0, true, false, 1, "torch");
	Gear testSword = new Gear(5, 0 , 0, "sword", false, "test", 0, false, false, 5, "sword");

	
	@Test
	public void testCheckStats() {
		assertEquals(testPlayer.checkStats(), "You have: 100 HP, 50 Mana, 10 Attack, 5 Defense");
	}
	
	@Test
	public void testCheckNPC() {
		assertEquals(testPlayer.checkNPC(testNPC), "A test NPC");
	}
	
	@Test
	public void testCheckItem() {
		assertEquals(testPlayer.checkItem(testItem), "A test item");
	}
	
	@Test
	public void testCheckValue() {
		assertEquals(testPlayer.checkValue(testItem), "testItem is worth 50 Gold");
	}
	
	@Test
	public void testCheckInventory() {
		assertEquals(testPlayer.checkInventory(testPlayer.getInventory()), "Your inventory is empty!");
		testPlayer.getInventory().addItem(testItem);
		assertEquals(testPlayer.checkInventory(testPlayer.getInventory()), "testItem ");
	}
	
	@Test
	public void testBarter() {
		testNPC.setAttitude(100);
		assertEquals(testPlayer.barter(testNPC, testItem), 15);
		testNPC.setAttitude(80);
		assertEquals(testPlayer.barter(testNPC, testItem), 10);
		testNPC.setAttitude(60);
		assertEquals(testPlayer.barter(testNPC, testItem), 5);
		testNPC.setAttitude(40);
		assertEquals(testPlayer.barter(testNPC, testItem), 0);
	}
	
	@Test
	public void testLight() {
		testPlayer.take(testTorch);
		testPlayer.light(testTorch);
		assertTrue(testTorch.getLit());
	}
	
	@Test
	public void testEquip() {
		int oldatk = testPlayer.getAtk();
		testPlayer.take(testSword);
		testPlayer.equip(testSword);
		assertEquals(oldatk + testSword.getAtk(), testPlayer.getAtk());
	}
	
	@Test
	public void testUnequip() {
		testPlayer.take(testSword);
		testPlayer.equip(testSword);
		int oldatk = testPlayer.getAtk();
		testPlayer.unequip(testSword);
		assertEquals(oldatk - testSword.getAtk(), testPlayer.getAtk());
	}
	
	@Test
	public void testUse() {
		testPlayer.take(testPotion);
		testPlayer.use(testPotion, testPlayer);
		assertEquals(110, testPlayer.getHP());
	}
	
	@Test
	public void testTake() {
		testPlayer.take(testItem);
		assertTrue(testPlayer.getInventory().getInventory().contains(testItem));
	}
	
	@Test
	public void testDrop() {
		testPlayer.take(testItem);
		testPlayer.drop(testItem);
		assertFalse(testPlayer.getInventory().getInventory().contains(testItem));
	}
	
	@Test
	public void testThro() {
		fail("not implemented");
	}
	
	@Test
	public void testCrawl() {
		fail("need to implement move and map first");
	}
	
	@Test
	public void testJump() {
		fail("need to implement move and map first");
	}
	@Test
	public void testTalk() {
		assertEquals(testNPC.getDialogue(), testPlayer.talk(testNPC));
	}
	
}
