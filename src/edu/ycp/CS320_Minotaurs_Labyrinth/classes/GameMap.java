package edu.ycp.CS320_Minotaurs_Labyrinth.classes;
import java.util.LinkedList;
import java.util.Map;

public class GameMap {
	//attributes
	private Map<Room, LinkedList<Room>> map;
	
	//methods
	public GameMap(Map <Room, LinkedList<Room>> map) {
		this.map = map;
	}

	public Map<Room, LinkedList<Room>> getMap(){
		return map;
	}
}
