package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DialogueTest {
	Dialogue testDialogue;
	@Before
	public void setUp() {
		testDialogue = new Dialogue("test question", 1, 2, 3, 4);
	}
	@Test
	public void testQuestionMethods() {
		testDialogue.setQuestion("test");
		assertEquals("test", testDialogue.getQuestion());
	}
	@Test
	public void testNPCIDMethods() {
		testDialogue.setNPC_ID(5);
		assertEquals(5, testDialogue.getNPC_ID());
	}
	@Test
	public void testChoice1Methods() {
		testDialogue.setChoice1(5);
		assertEquals(5, testDialogue.getChoice1());
	}
	@Test
	public void testChoice2Methods() {
		testDialogue.setChoice2(5);
		assertEquals(5, testDialogue.getChoice2());
	}
	@Test
	public void testChoice3Methods() {
		testDialogue.setChoice3(5);
		assertEquals(5, testDialogue.getChoice3());
	}
	
}
