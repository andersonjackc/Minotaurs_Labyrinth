package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.HashMap;

public class Room {

	private String description;
	private Inventory inventory;
	private Obstacle obstacle;
	private HashMap<String, Integer> roomMap;
	private boolean isFound;
	private int roomID;
	
	public Room(String description, Inventory inventory, Obstacle obstacle, HashMap<String, Integer> roomMap, boolean isFound, int roomID) {
		this.description = description;
		this.inventory = inventory;
		this.obstacle = obstacle;
		this.roomMap = roomMap;
		this.isFound = isFound;
		this.roomID = roomID;
	}
	
	public HashMap<String, Integer> getRoomMap() {
		return roomMap;
	}
	
	//getters
	public String getDescription() {
		return description;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public boolean getIsFound() {
		return isFound;
	}
	
	public Obstacle getObstacle() {
		return obstacle;
	}
	
	public int getRoomId() {
		
		return roomID;
	}
	
	//setters
	public void setDescription(String description) {
		this.description=description;
	}

	public void setRoomMap(HashMap<String, Integer> roomMap) {
		this.roomMap=roomMap;
	}
	
	public void setIsFound(boolean isFound) {
		this.isFound = isFound;
	}
	
	public void setRoomId(int roomID) {
		this.roomID = roomID;
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	
	}
