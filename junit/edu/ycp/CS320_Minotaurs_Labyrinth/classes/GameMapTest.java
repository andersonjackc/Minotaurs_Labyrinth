package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GameMapTest {
	
	GameMap testGameMap;
	Room room1, room2;
	Inventory inventory;
	LinkedList<Pair<Room, String>> rooms1, rooms2;
	@Before
	public void setUp() {
		Map<Room, LinkedList<Pair<Room, String>>> testMap = new HashMap<Room, LinkedList<Pair<Room, String>>>();
		testGameMap = new GameMap(testMap);
		
		inventory = new Inventory(0, 0, null);
		
		room1 = new Room("A test room", "empty", inventory );
		room2 = new Room("A test room", "empty", inventory );
		
		rooms1 = new LinkedList<Pair<Room, String>>();
		rooms2 = new LinkedList<Pair<Room, String>>();
		rooms1.add(new Pair<Room, String>(room2, "North"));
		rooms2.add(new Pair<Room, String>(room1, "South"));
		testGameMap.addRoom(room1, rooms1);
		testGameMap.addRoom(room2, rooms2);
	}
	@Test
	public void testGameMap() {
		
		Map<Room, LinkedList<Pair<Room, String>>> testMap = testGameMap.getMap();
		
		LinkedList<Pair<Room, String>> testRoomList = testMap.get(room1);
		String checkString = "";
		
		for(Pair<Room, String> pair : testRoomList) {
			
			if(pair.getRight() == "North") {
				checkString = pair.getRight();
			}
		}
		
		assertEquals(checkString, "North");
		
	}

}
