package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.LinkedList;
import java.util.Map;

public class GameMap {
	//attributes
	//the game map will be represented by a Map, that Maps from the current room, the key,
	//to a LinkedList of Pairs of Rooms and a String that represent the Direction the room is from the current room
	// this represents the adjacency list, or the rooms that the current room is connected to
	private Map<Room, LinkedList<Pair<Room, String>>> map;
	
	//methods
	public GameMap(Map <Room, LinkedList<Pair<Room, String>>> map) {
		this.map = map;
	}

	public Map<Room, LinkedList<Pair<Room, String>>> getMap(){
		return map;
	}
	
	public void addRoom(Room room, LinkedList<Pair<Room, String>> rooms) {
		map.put(room, rooms);
	}
}
