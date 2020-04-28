package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {
	Pair<String, String> testPair = new Pair<String, String>("a","b");

	@Test
	public void testLeftMethods() {
		testPair.setLeft("c");
		assertEquals("c", testPair.getLeft());
	}
	@Test
	public void testRightMethods() {
		testPair.setRight("c");
		assertEquals("c", testPair.getRight());
	}

}
