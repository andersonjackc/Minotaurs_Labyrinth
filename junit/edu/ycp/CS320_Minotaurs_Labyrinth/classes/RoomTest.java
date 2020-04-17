package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class RoomTest {
	Obstacle obs = new Obstacle("test", "normal", null);
	ArrayList<Item> Inv = new ArrayList<Item>();
	Inventory testRoomInv = new Inventory(100, 100, Inv);
	HashMap<String, Integer> testMap;
	Room room = new Room("A test room", testRoomInv, obs, null, 1, false);

	
	@Test
	public void testDescriptionMethods() {
		assertEquals("A test room", room.getDescription());
		
	}
	
	@Test
	public void testInventoryMethods() {
		assertEquals(testRoomInv, room.getInventory());
		
	}
	
	@Test
	public void testObstacleMethods() {
		assertEquals(obs, room.getObstacle());
		
	}
	@Test
	public void testRoomMapMethods() {
		
		room.setRoomMap(testMap);
		assertEquals(testMap, room.getRoomMap());
		
	}
	@Test
	public void testRooomIDMethods() {
		
		room.setRoomId(2);
		assertEquals(2, room.getRoomId());
		
	}
	@Test
	public void testIsFoundMethods() {
		
		room.setIsFound(true);
		assertTrue(room.getIsFound());
		
	}

}
