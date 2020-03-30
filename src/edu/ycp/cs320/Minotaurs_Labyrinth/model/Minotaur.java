package edu.ycp.cs320.Minotaurs_Labyrinth.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.GameMap;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;

public class Minotaur {
	
	
	GameMap map;
	Room centerRoom, northRoom, southRoom, eastRoom, westRoom;
	Room[] adjCent, adjNorth, adjSouth, adjEast, adjWest;
	Inventory inv;
	Item requirement;
	ArrayList<Item> inventory;
	Obstacle obs, obs2;
	String errorMessage;
	String message;
	Player player;
	int roomPosition;
	String attackMessage;
	String defendMessage;
	Enemy ogre;
	//fills map w/ 0 for empty, 1 for player, 3 for non-enterable room
	public void initMap() {
		
		Map<Room, Room[]> gameMap = new HashMap<Room, Room[]>();
		
		map = new GameMap(gameMap);
		
		requirement = new Item("Key", 0, true, true, true, 0, "Key", "test", "test");
		inv = new Inventory(0, 0, inventory);
		obs = new Obstacle("No obstacle", "normal", requirement);
		obs2 = new Obstacle("No obstacle", "locked", requirement);
		
		centerRoom = new Room("You enter a small stone room there are four doorways at each cardinal direction.", "Starting Room", inv, obs);
		northRoom = new Room("You enter a small stone room, the stench of ogre is unbearable. The only exit is the way you entered.", "Combat room", inv, obs);
		southRoom = new Room("You enter a small stone room, it seems someone or something has been living here. The only exit is the way you entered.", "Dialogue room", inv, obs);
		eastRoom = new Room("You enter a small stone room, it is empty. The only exit is the way you entered.", "Locked room", inv, obs2);
		westRoom = new Room("You enter a small stone room, it is empty. The only exit is the way you entered.", "Empty room", inv, obs);

		
		adjCent = new Room[4];
		adjNorth = new Room[4];
		adjSouth = new Room[4];
		adjEast = new Room[4];
		adjWest = new Room[4];
		
		adjCent[0] = northRoom;
		adjCent[1] = southRoom;
		adjCent[2] = eastRoom;
		adjCent[3] = westRoom;
		
		adjNorth[1] = centerRoom;
		adjSouth[0] = centerRoom;
		adjEast[3] = centerRoom;
		adjWest[2] = centerRoom;
		
		map.addRoom(centerRoom, adjCent);
		map.addRoom(northRoom, adjNorth);
		map.addRoom(southRoom, adjSouth);
		map.addRoom(eastRoom, adjEast);
		map.addRoom(westRoom, adjWest);
	}
	
	//currently sets player to middle of the map
	public void initPlayer() {
		player = new Player(1000, 20, 0, 0, 2, 0, 0, 0, null, "normal", inv, centerRoom);
		roomPosition = 4;
		
		ogre = new Enemy(10, 10, 0, 0, 1, 0, 0, 0, null, "ogre", "Grr lets fight", 0, "A large ogre with a club, he has a leather tunic", "Ogre", inv, northRoom);

	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Room getRoom() {
		return player.getCurrentRoom();
	}
	
	public String getRoomDescription() {
		return player.getCurrentRoom().getDescription();
	}
	
	public GameMap getMap(){
		return map;
	}
	
	public int getRoomPosition() {
		return roomPosition;
	}
	
	public void setRoomPosition(int roomPosition) {
		
		this.roomPosition = roomPosition;
	}
	
	public Room getCenterRoom() {
		return centerRoom;
	}
	
	public Room getNorthRoom() {
		return northRoom;
	}
	
	public Room getSouthRoom() {
		return southRoom;
	}
	
	public Room getEastRoom() {
		return eastRoom;
	}
	
	public Room getWestRoom() {
		return westRoom;
	}
	
	//prints what type of error it is
	public String getError() {
		return errorMessage;
	}
	
	public void setError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	public Boolean isEnemyAlive(Enemy target) {
		if(target.getHP() <= 0) {
			return false;
		}else{
			return true;
		}
	}
	public void enemyAtk() {
		
		ogre.basicAttack(player);
		defendMessage = "Ogre did " + ogre.getAtk() + " You now have " + player.getHP();
	
	}
	
	public void playerAtk() {
	
		player.basicAttack(ogre);
		attackMessage = "You did " + player.getAtk() + " to " + ogre.getName() + ", it now has " + ogre.getHP() + " HP";
		
	}
	public String getAttackmessage() {
		return attackMessage;
	}
	public String getDefendmessage() {
		return defendMessage;
	}
	public void setPlayerHP(int HP) {
		player.setHP(HP);
	}
	public int getPlayerHP() {
		return player.getHP();
	}
	public void setEnemyHP(int HP) {
		ogre.setHP(HP);
	}
	public int getEnemyHP() {
		return ogre.getHP();
	}
}