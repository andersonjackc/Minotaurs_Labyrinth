package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	//Set up a test Player obj
	Player testPlayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null);
	NPC testNPC = new NPC(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null, 0, "A test NPC", "test", null);
	Item testItem = new Item("A generic item", 5, true, false, 50, "testItem");
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCheckStats() {
		assertEquals(testPlayer.checkStats(), "You have: " + testPlayer.getHP() + " HP, " + testPlayer.getResource() + " Mana, " +
				testPlayer.getAtk() + " Attack, " + testPlayer.getDef() + " Defense");
	}
	
	@Test
	public void testCheckNPC() {
		assertEquals(testPlayer.checkNPC(testNPC), "A generic item");
	}
	
	@Test
	public void testCheckValue() {
		assertEquals(testPlayer.checkValue(testItem), "testItem is worth 50 Gold");
	}

}
