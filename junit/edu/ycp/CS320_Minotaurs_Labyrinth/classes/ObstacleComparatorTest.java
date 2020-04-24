package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObstacleComparatorTest {
	Item test = new Item("test", 0, true, true, true, 0, "test", "test", "test");
	Obstacle obs1 = new Obstacle("test", "test", test);
	Obstacle obs2 = new Obstacle("test1", "test1", test);
	ObstacleComparator OC = new ObstacleComparator();
	
	
	@Test
	public void testCompare() {
		assertEquals(1, OC.compare(obs1, obs1));
		assertEquals(-1, OC.compare(obs1, obs2));
	}

}
