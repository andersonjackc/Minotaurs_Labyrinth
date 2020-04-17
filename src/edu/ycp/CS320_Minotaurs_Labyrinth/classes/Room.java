package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.HashMap;

public class Room {

	private String description;
	private Inventory inventory;
	private Obstacle obstacle;
	private HashMap<String, Room> roomMap;
	private boolean isFound;
	
	public Room(String description, Inventory inventory, Obstacle obstacle, HashMap<String, Room> roomMap, boolean isFound) {
		this.description = description;
		this.inventory = inventory;
		this.obstacle = obstacle;
		this.roomMap = roomMap;
		this.isFound = isFound;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}

	public void setRoomMap(HashMap<String, Room> roomMap) {
		this.roomMap=roomMap;
	}
	
	public void setIsFound(boolean isFound) {
		this.isFound = isFound;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public HashMap<String, Room> getRoomMap() {
		return roomMap;
	}
	
	
	public boolean getIsFound() {
		return isFound;
	}
	
	public Obstacle getObstacle() {
		return obstacle;
	}
	}
