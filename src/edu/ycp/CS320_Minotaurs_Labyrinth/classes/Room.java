package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Room {

	private ArrayList<String> directions;
	private String description, status;
	private Inventory inventory;
	public Room(String description, String status, Inventory inventory) {
		this.description = description;
		this.status = status;
		this.inventory = inventory;
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

	public void printDescription() {
		//For now just printing in terminal, will adapt to game later
		System.out.println(description);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
