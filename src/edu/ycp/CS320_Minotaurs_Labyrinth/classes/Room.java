package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Room {

	private ArrayList<String> directions;
	private String description;
	
	public Room(String description) {
		this.description = description;
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
		throw new UnsupportedOperationException("TODO");
	}
	
}
