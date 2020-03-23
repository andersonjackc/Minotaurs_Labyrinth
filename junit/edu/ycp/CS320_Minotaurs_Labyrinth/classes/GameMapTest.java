package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import java.util.Map;

public class GameMapTest {
	
	GameMap testGameMap;
	LinkedList<Room> testList = null;
	Room room;
	@Before
	public void setUp() {
		Map<Room, LinkedList<Room>> testMap = null;
		testGameMap = new GameMap(testMap);
		
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
