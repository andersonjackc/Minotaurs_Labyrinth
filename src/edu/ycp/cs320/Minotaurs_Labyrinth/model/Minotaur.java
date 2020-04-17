package edu.ycp.cs320.Minotaurs_Labyrinth.model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.GameMap;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;

public class Minotaur {
	
	
	
	Room centerRoom, northRoom, southRoom, eastRoom, westRoom;
	HashMap<String, Room> centerRoomMap, northRoomMap, southRoomMap, eastRoomMap, westRoomMap;
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
	int enemyDeadVal;
	int villagerDeadVal;
	String NPCDesc;
	String NPCResp;
	NPC villager;
	ArrayList<Message<String, Integer>> outputstrings;
	String PlayerResp;

	//Creates the map and rooms
	public void initMap() {
		
		Map<Room, Room[]> gameMap = new HashMap<Room, Room[]>();
		
		
		
		requirement = new Item("Key", 0, false, false, true, 0, "Key", "test", "test");
		inv = new Inventory(0, 0, inventory);
		obs = new Obstacle("No obstacle", "normal", null);
		obs2 = new Obstacle("The room is locked", "locked", requirement);
		
		
		centerRoomMap = new HashMap<String, Room>();
		northRoomMap = new HashMap<String, Room>();
		southRoomMap = new HashMap<String, Room>();
		eastRoomMap = new HashMap<String, Room>();
		westRoomMap = new HashMap<String, Room>();
		
		centerRoom = new Room("You enter a small stone room there are four doorways at each cardinal direction.", inv, obs, centerRoomMap, true);
		northRoom = new Room("You enter a small stone room, the stench of ogre is unbearable. The only exit is the way you entered.", inv, obs, northRoomMap, false);
		southRoom = new Room("You enter a small stone room, a villager stands before you. The only exit is the way you entered.", inv, obs, southRoomMap, false);
		eastRoom = new Room("You enter a small stone room, it is empty. The only exit is the way you entered.",  inv, obs2, eastRoomMap, false);
		westRoom = new Room("You enter a small stone room, it is empty. The only exit is the way you entered.",  inv, obs, westRoomMap, false);

		outputstrings = new ArrayList<Message<String, Integer>>();
		Message<String, Integer> initialMessage = new Message<String, Integer>("You enter a small stone room there are four doorways at each cardinal direction.", 0);
		outputstrings.add(initialMessage);
		
		
		centerRoomMap.put("north", northRoom);
		centerRoomMap.put("south", southRoom);
		centerRoomMap.put("east", eastRoom);
		centerRoomMap.put("west", westRoom);
		
		northRoomMap.put("south", centerRoom);
		southRoomMap.put("north", centerRoom);
		eastRoomMap.put("west", centerRoom);
		westRoomMap.put("east", centerRoom);
		
		
	}
	
	//Creates the actors
	public void initPlayer() {
		player = new Player(1000, 20, 0, 0, 2, 0, 0, 0, null, "normal", inv, centerRoom, false);
		roomPosition = 4;
		
		ogre = new Enemy(10, 10, 0, 0, 1, 0, 0, 0, null, "ogre", "Grr lets fight", 0, "A large ogre with a club, he has a leather tunic", "Ogre", inv, northRoom, false);
		enemyDeadVal = 0;
		
		ArrayList<Ability> VillagerAbilities = new ArrayList<Ability>();
		villager = new NPC(10, 10, 0, 0, 1, 0, 0, 0, VillagerAbilities, "Villager", "Hello Traveler!", 100, "An old man with tattered clothing", "Villager", inv, southRoom, false);
		villagerDeadVal = 0;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Enemy getEnemy() {
		return ogre;
	}
	
	public NPC getVillager() {
		return villager;
	}
	
	public Room getRoom() {
		return player.getCurrentRoom();
	}
	
	public String getRoomDescription() {
		
		return player.getCurrentRoom().getDescription();
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
	
	public String getError() {
		return errorMessage;
	}
	
	public void setError(String errorMessage) {
		Message<String, Integer> error = new Message<String, Integer>(errorMessage, 0);
		outputstrings.add(error);
		this.errorMessage = errorMessage;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	//Returns a boolean if the target is alive
	public Boolean isEnemyAlive(Enemy target) {
		if(target.getHP() <= 0) {
			return false;
		}else{
			return true;
		}
	}
	
	//Ogre attacking Player (NOT GENERIC)
	public void enemyAtk() {
		if(!ogre.getIsDead()) {
			ogre.basicAttack(player);
			defendMessage = "Ogre did " + ogre.getAtk() + " You now have " + player.getHP() + " HP";
			Message<String, Integer> defendMsg = new Message<String, Integer>(defendMessage, 0);
			outputstrings.add(defendMsg);
		}
	}
	
	//Player attacking Ogre (NOT GENERIC)
	public void playerAtk() {
		if(!ogre.getIsDead()) {
			player.basicAttack(ogre);
			attackMessage = "You did " + player.getAtk() + " to " + ogre.getName() + ", it now has " + ogre.getHP() + " HP";
			Message<String, Integer> atkMsg = new Message<String, Integer>(attackMessage, 0);
			outputstrings.add(atkMsg);
		}
		if(ogre.getIsDead()) {
			message = "Ogre is dead!";
			Message<String, Integer> deadMsg = new Message<String, Integer>(message, 0);
			outputstrings.add(deadMsg);
			enemyDeadVal=1;
		}
	}
	
	//Villager attacking Player (NOT GENERIC)
	public void enemyAtkVillager() {
		if(!villager.getIsDead()) {
		villager.basicAttack(player);
		defendMessage = "Villager did " + villager.getAtk() + " You now have " + player.getHP() +" HP";
		Message<String, Integer> defendMsg = new Message<String, Integer>(defendMessage, 0);
		outputstrings.add(defendMsg);
		}
	}
	
	//Player attacking Villager (NOT GENERIC)
	public void playerAtkVillager() {
		if(!villager.getIsDead()) {
			player.basicAttack(villager);
			attackMessage = "You did " + player.getAtk() + " to " + villager.getName() + ", it now has " + villager.getHP() + " HP";
			Message<String, Integer> atkMsg = new Message<String, Integer>(attackMessage, 0);
			outputstrings.add(atkMsg);
		}
		if(villager.getIsDead()) {
			message = "Villager is dead";
			Message<String, Integer> deadMsg = new Message<String, Integer>(message, 0);
			outputstrings.add(deadMsg);
			villagerDeadVal = 1;
		}
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
	public int getVillagerHP() {
		return villager.getHP();
	}
	public void setVillagerHP(int HP) {
		villager.setHP(HP);
	}
	public int getEnemyDead() {
		return enemyDeadVal;
	}
	
	public void setEnemyDead(int enemyDeadVal) {
		this.enemyDeadVal = enemyDeadVal;
	}
	public int getVillagerDead() {
		return villagerDeadVal;
	}
	
	public void setVillagerDead(int villagerDeadVal) {
		this.villagerDeadVal = villagerDeadVal;
	}
	public ArrayList<Message<String, Integer>> getOutputStrings(){
		return outputstrings;
	}
	
	public void setOutputStrings(ArrayList<Message<String, Integer>> outputstrings) {
		this.outputstrings = outputstrings;
	}
	
	public String getNPCDesc() {
		NPCDesc = "You see before you " + villager.getDescription();
		Message<String, Integer> descMessage = new Message<String, Integer>(NPCDesc, 0);
		outputstrings.add(descMessage);
		return NPCDesc;
	}
	public String getNPCResp() {
		return NPCResp;
	}
	
	//Fills in player response, adds responses to output strings
	public void initResponses() {
		PlayerResp = "Greetings";
		Message<String, Integer> respMsg = new Message<String, Integer>(PlayerResp, 0);
		outputstrings.add(respMsg);
		NPCResp = "The " + villager.getName() + " says " + villager.getDialogue();
		Message<String, Integer> npcrespMsg = new Message<String, Integer>(NPCResp, 0);
		outputstrings.add(npcrespMsg);

	}
	
	public String getPlayerResp() {
		
		return PlayerResp;
	}
}