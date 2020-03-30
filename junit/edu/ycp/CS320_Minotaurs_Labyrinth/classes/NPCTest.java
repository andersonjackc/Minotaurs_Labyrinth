package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class NPCTest {
private NPC testNPC;
ArrayList<Ability> abilities = null;
ArrayList<Item> items = new ArrayList<Item>();
Inventory i = new Inventory(0, 0, items);
ArrayList<Item> Inv = new ArrayList<Item>();
Inventory testRoomInv = new Inventory(100, 100, Inv);
Item key = new Item("test", 1, true, false, false, 10, null, null, null);
Obstacle obs = new Obstacle("test", "jumping", key);
Room room = new Room("A test room", "test", testRoomInv, obs);

	@Before
	public void setUp() {
		
		testNPC = new NPC(5, 5, 5, 5, 1, 5, 5, 5, abilities, "test", "test", 1, "test", "test", i, room, false);
		Ability fireball;
	}
	@Test
	public void testMaxHPMethods() {
		
		assertEquals(5, testNPC.getMaxHP());
		
	}
	@Test
	public void testHPMethods() {
		testNPC.setHP(5);
		assertEquals(5, testNPC.getHP());
	}
	@Test
	public void testMaxResourceMethods() {
		
		assertEquals(5, testNPC.getMaxResource());
		
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
		fail("Not yet implemented");
	}
	@Test
	public void testDialogueMethods() {
		
		assertEquals("test", testNPC.getDialogue());
	}
	@Test
	public void testInventoryMethods() {
		
		assertEquals(i , testNPC.getInventory());
	}
	@Test
	public void testAttitudeMethods() {
		testNPC.setAttitude(5);
		assertEquals(5, testNPC.getAttitude());
	}
	@Test
	public void testDescriptionMethods() {
		
		assertEquals("test", testNPC.getDescription());
	}
	@Test
	public void testNameMethods() {
		assertEquals("test", testNPC.getName());
	}
	@Test
	public void testPrintDescription(){
		assertEquals(testNPC.getDescription(), "test");	
	}
	@Test
	public void testPrintResponses(){
		fail("Not yet implemented");	
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
		fail("Not yet implemented");
		
	}
}
