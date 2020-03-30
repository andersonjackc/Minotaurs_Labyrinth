package edu.ycp.CS320_Minotaurs_Labyrinth.classes;


import java.util.Map;

public class GameMap {
	//attributes
	//the game map will be represented by a Map, that Maps from the current room, the key,
	//to an array of rooms where room[0] = north, room[1]=south, room[2] = east, room[3] = west
	private Map<Room, Room[]> map;
	
	//methods
	public GameMap(Map <Room, Room[]> map) {
		this.map = map;
	}

	public Map<Room, Room[]> getMap(){
		return map;
	}
	
	public void addRoom(Room room, Room[] rooms) {
		map.put(room, rooms);
	}
}
