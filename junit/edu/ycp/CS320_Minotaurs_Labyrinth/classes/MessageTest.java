package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MessageTest {
	Message<String, Integer> testMessage;
	
	@Before
	public void setUp() {
		testMessage = new Message<String, Integer>("test message", 0);
	}
	
	@Test
	public void testPlayerActionMethods() {
		testMessage.setPlayerAction(1);
		assertEquals(1, testMessage.getPlayerAction());
	}
	@Test
	public void testMessageMethods() {
		testMessage.setMessage("test");
		assertEquals("test", testMessage.getMessage());
	}
}
