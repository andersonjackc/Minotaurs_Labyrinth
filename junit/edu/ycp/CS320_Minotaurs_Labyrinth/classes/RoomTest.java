package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RoomTest {
	Obstacle obs = new Obstacle("test", "normal", null);
	ArrayList<Item> Inv = new ArrayList<Item>();
	Inventory testRoomInv = new Inventory(100, 100, Inv);
	Room room = new Room("A test room", testRoomInv, obs);

	@Test
	public void testDirectionMethods() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("north");
		room.setDirection(list);
		assertEquals("north", room.getDirection().get(0));
		
	}
	
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

}
