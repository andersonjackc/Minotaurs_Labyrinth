package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class ObstacleTest {
	Item key = new Item("test", 1, true, false, false, 10, "key", "quest", "HP");
	Item key2 = new Item("test", 1, true, false, false, 10, null, "quest", null);
	Obstacle obs = new Obstacle("test", "jumping", key, "door");
	ArrayList<Item> Inv = new ArrayList<Item>();
	ArrayList<Item> Inv2 = new ArrayList<Item>();
	Inventory testInv = new Inventory(100, 100, Inv);
	Inventory testRoomInv = new Inventory(100, 100, Inv2);
	HashMap<String, Integer> testMap;
	Room room = new Room("A test room", testRoomInv, obs, testMap,  false, 1);
	Player testPlayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, testInv, room, false, "test");
	
	@Test
	public void testDescriptionMethods() {
		obs.setDescription("obstacle");
		assertEquals("obstacle", obs.getDescription());
	}
	
	@Test
	public void testStatusMethods() {
		obs.setStatus("none");
		assertEquals("none", obs.getStatus());
	}
	
	@Test
	public void testRequirementMethods() {
		obs.setRequirement(key2);
		assertEquals(key2, obs.getRequirement());
	}
	
	@Test
	public void testCheckRequirement() {
		testRoomInv.addItem(key);
		testPlayer.take(key, testRoomInv);
		assertTrue(obs.checkRequirement(testPlayer));
		assertEquals(testRoomInv.getInventory().size(), 0);
	}
	
	@Test
	public void testCheckStatus() {
		testPlayer.setStatus("jumping");
		assertTrue(obs.checkStatus(testPlayer));
	}
	@Test
	public void testCheckStatusFalse() {
		testPlayer.setStatus("normal");
		assertFalse(obs.checkStatus(testPlayer));
	}

}
