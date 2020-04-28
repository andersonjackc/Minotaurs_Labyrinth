package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChoiceTest {
	Choice testChoice;
	@Before
	public void setUp() {
		testChoice = new Choice(1, 2, "testChoice");
	}
	@Test
	public void testNPCIDMethods() {
		testChoice.setNPC_ID(5);
		assertEquals(5, testChoice.getNPC_ID());
	}
	@Test
	public void testChoiceIDMethods() {
		testChoice.setChoice_ID(5);
		assertEquals(5, testChoice.getChoice_ID());
	}
	@Test
	public void testRepsonseMethods() {
		testChoice.setResponse("test response");
		assertEquals("test response", testChoice.getResponse());
	}
}
