package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class RoomTest {
	Obstacle obs = new Obstacle("test", "normal", null);
	Obstacle obs2 = new Obstacle("test", "normal", null);
	ArrayList<Item> Inv = new ArrayList<Item>();
	Inventory testRoomInv = new Inventory(100, 100, Inv);
	Inventory testInv = new Inventory(100, 100, Inv);
	HashMap<String, Integer> testMap;
	Room room = new Room("A test room", testRoomInv, obs, testMap, false, 1);

	
	@Test
	public void testDescriptionMethods() {
		room.setDescription("test");
		assertEquals("test", room.getDescription());
		
	}
	
	@Test
	public void testInventoryMethods() {
		room.setInventory(testInv);
		assertEquals(testInv, room.getInventory());
		
	}
	
	@Test
	public void testObstacleMethods() {
		room.setObstacle(obs2);
		assertEquals(obs2, room.getObstacle());
		
	}
	@Test
	public void testRoomMapMethods() {
		
		room.setRoomMap(testMap);
		assertEquals(testMap, room.getRoomMap());
		
	}

	@Test
	public void testIsFoundMethods() {
		
		room.setIsFound(true);
		assertTrue(room.getIsFound());
		
	}
	
	@Test
	public void testRoomIdMethods() {
		room.setRoomId(2);
		assertEquals(2, room.getRoomId());
	}

}
