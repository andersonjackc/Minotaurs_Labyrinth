package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Room {

	private ArrayList<String> directions;
	private String description;
	private Inventory inventory;
	private Obstacle obstacle;
	public Room(String description, Inventory inventory, Obstacle obstacle) {
		this.description = description;
		this.inventory = inventory;
		this.obstacle = obstacle;
	}
	
	public ArrayList<String> getDirection() {
		
		return directions;
	}
	
	public void setDirection(ArrayList<String> directions) {
		this.directions = directions;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}

	public void printDescription() {
		//For now just printing in terminal, will adapt to game later
		System.out.println(description);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public Obstacle getObstacle() {
		return obstacle;
	}
	}
