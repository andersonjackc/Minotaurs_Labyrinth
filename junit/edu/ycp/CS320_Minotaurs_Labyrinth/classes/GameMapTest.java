package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

public class GameMapTest {
	
	GameMap testGameMap;
	Room room1, room2;
	Inventory inventory;
	Room[] rooms1, rooms2;
	Item key = new Item("test", 1, true, false, false, 10, null, null, null);
	Obstacle obs = new Obstacle("test", "jumping", key);
	@Before
	public void setUp() {
		Map<Room, Room[]> testMap = new HashMap<Room, Room[]>();
		testGameMap = new GameMap(testMap);
		
		inventory = new Inventory(0, 0, null);
		
		room1 = new Room("A test room", inventory, obs );
		room2 = new Room("A test room", inventory, obs );
		
		rooms1 = new Room[4];
		rooms2 = new Room[4];
		rooms1[0] = room2;
		rooms2[1] = room1;
		
		testGameMap.addRoom(room1, rooms1);
		testGameMap.addRoom(room2, rooms2);
	}
	@Test
	public void testGameMap() {
		
		Map<Room, Room[]> testMap = testGameMap.getMap();
		
		Room[] testRoomArray = testMap.get(room1);
		
	
		assertEquals(testRoomArray[0], room2);
		assertEquals(testRoomArray[1], null);
		
	}

}
