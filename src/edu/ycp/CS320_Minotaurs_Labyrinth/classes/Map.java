package edu.ycp.CS320_Minotaurs_Labyrinth.classes;
import java.util.LinkedList;

public class Map {
	//attributes
	private LinkedList<LinkedList<Room>> map;
	
	//methods
	public Map(LinkedList<LinkedList<Room>> map) {
		this.map = map;
	}

	public LinkedList<LinkedList<Room>> getMap(){
		return map;
	}
}
