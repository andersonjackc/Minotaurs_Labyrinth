package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	//Set up a test Player obj
	Player testplayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null);
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCheckPlayerStats() {
		assertEquals(testplayer.checkStats(), "You have: " + testplayer.getHP() + " HP, " + testplayer.getResource() + " Mana, " +
				testplayer.getAtk() + " Attack, " + testplayer.getDef() + " Defense");
	}

}
