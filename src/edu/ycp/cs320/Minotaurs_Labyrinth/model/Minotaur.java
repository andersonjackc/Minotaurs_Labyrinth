package edu.ycp.cs320.Minotaurs_Labyrinth.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Actor;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Gear;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DatabaseProvider;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DerbyDatabase;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.IDatabase;

public class Minotaur {
	
	//attributes
	HashMap<String, Actor> targets;
	Room[] roomArray;
	ArrayList<Room> allRooms;
	Room room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15, room16, room17, room18, room19, room20, room21, room22, room23, room24, room25, room26, room27, room28, room29, room30, room31, room32, room33, room34, room35, room36, room37, room38, room39, room40, room41, room42;
	HashMap<String, Integer> roomMap1, roomMap2, roomMap3, roomMap4, roomMap5, roomMap6, roomMap7, roomMap8, roomMap9, roomMap10, roomMap11, roomMap12, roomMap13, roomMap14, roomMap15, roomMap16, roomMap17, roomMap18, roomMap19, roomMap20, roomMap21, roomMap22, roomMap23, roomMap24, roomMap25, roomMap26, roomMap27, roomMap28, roomMap29, roomMap30, roomMap31, roomMap32, roomMap33, roomMap34, roomMap35, roomMap36, roomMap37, roomMap38, roomMap39, roomMap40, roomMap41, roomMap42;
	Inventory inv, inv39;
	Item requirement, rope, MKey, shears;
	ArrayList<Item> inventory, inventory39;
	ArrayList<Ability> abilities, gabilities;
	Ability fireball, heal, cripple;
	Obstacle obs, obs2, hole, weird_door, vines;
	String errorMessage;
	String message;
	Player player;
	int roomPosition;
	String attackMessage;
	String defendMessage;
	Enemy ogre, goblin, minotaur;
	int enemyDeadVal;
	int villagerDeadVal;
	int playerDeadVal;
	String NPCDesc;
	String NPCResp;
	NPC villager;
	ArrayList<Message<String, Integer>> outputstrings;
	String PlayerResp;

	//methods
	//init methods
	//Creates the map and rooms
	public void initMap() {
				
		requirement = new Item("Key", 0, false, false, true, 0, "Key", "test", "test");
		rope = new Item("a hemp rope", 0, true, false, true, 0, "rope", "obstacle item", "");
		MKey = new Item("a key with a minotaur engraved on it", 0, false, false, true, 0, "MKey", "quest", "");
		shears = new Item("a pair of shears", 0, false, false, true, 0, "shears", "obstacle item", "");
		
		inventory = new ArrayList<Item>();
		inventory39 = new ArrayList<Item>();
		inventory39.add(shears);

		inv = new Inventory(0, 0, inventory);
		inv39 = new Inventory(0, 0, inventory39);
		obs = new Obstacle("No obstacle", "normal", null);
		obs2 = new Obstacle("The room is locked", "locked", requirement);
		hole = new Obstacle("A large hole across the middle of the room", "normal", rope);
		weird_door = new Obstacle("There is a strange door with a Minotaur engraved on it blocking your path, it seems to require a key of some kind.", "normal", MKey);
		vines = new Obstacle("large thick vines block the door", "normal", shears);
		
		roomArray = new Room[43];
		roomMap1 = new HashMap<String, Integer>();
		roomMap2 = new HashMap<String,Integer>();
		roomMap3 = new HashMap<String,Integer>();
		roomMap4 = new HashMap<String,Integer>();
		roomMap5 = new HashMap<String,Integer>();
		roomMap6 = new HashMap<String,Integer>();
		roomMap7 = new HashMap<String,Integer>();
		roomMap8 = new HashMap<String,Integer>();
		roomMap9 = new HashMap<String,Integer>();
		roomMap10 = new HashMap<String,Integer>();
		roomMap11 = new HashMap<String,Integer>();
		roomMap12 = new HashMap<String,Integer>();
		roomMap13 = new HashMap<String,Integer>();
		roomMap14 = new HashMap<String,Integer>();
		roomMap15 = new HashMap<String,Integer>();
		roomMap16 = new HashMap<String,Integer>();
		roomMap17 = new HashMap<String,Integer>();
		roomMap18 = new HashMap<String,Integer>();
		roomMap19 = new HashMap<String,Integer>();
		roomMap20 = new HashMap<String,Integer>();
		roomMap21 = new HashMap<String,Integer>();
		roomMap22 = new HashMap<String,Integer>();
		roomMap23 = new HashMap<String,Integer>();
		roomMap24 = new HashMap<String,Integer>();
		roomMap25 = new HashMap<String,Integer>();
		roomMap26 = new HashMap<String,Integer>();
		roomMap27 = new HashMap<String,Integer>();
		roomMap28 = new HashMap<String,Integer>();
		roomMap29 = new HashMap<String,Integer>();
		roomMap30 = new HashMap<String,Integer>();
		roomMap31 = new HashMap<String,Integer>();
		roomMap32 = new HashMap<String,Integer>();
		roomMap33 = new HashMap<String,Integer>();
		roomMap34 = new HashMap<String,Integer>();
		roomMap35 = new HashMap<String,Integer>();
		roomMap36 = new HashMap<String,Integer>();
		roomMap37 = new HashMap<String,Integer>();
		roomMap38 = new HashMap<String,Integer>();
		roomMap39 = new HashMap<String,Integer>();
		roomMap40 = new HashMap<String,Integer>();
		roomMap41 = new HashMap<String,Integer>();
		roomMap42 = new HashMap<String,Integer>();
		
		allRooms = new ArrayList<Room>();
		
		room1 = new Room("You enter a small stone room there are four doorways at each cardinal direction.", inv, obs, roomMap1, true, 1);
		room2 = new Room("You enter a small stone room, the stench of ogre is unbearable. There is an exit on the opposite side of the room from the way you entered.", inv, obs, roomMap2, false, 2);
		room3 = new Room("You enter a small stone room, a villager stands before you. The only exit is the way you entered.", inv, obs, roomMap3, false, 3);
		room4 = new Room("You enter a small stone room, it is empty. The only exit is the way you entered.",  inv, obs2, roomMap4, false, 4);
		room5 = new Room("You enter a small stone room, it is empty. There is an exit on the opposite side of the room from the way you entered.",  inv, obs, roomMap5, false, 5);
		room6 = new Room("Empty room",  inv, obs, roomMap6, false, 6);
		room7 = new Room("Empty room",  inv, obs, roomMap7, false, 7);
		room8 = new Room("Empty room",  inv, obs, roomMap8, false, 8);
		room9 = new Room("Empty room",  inv, obs, roomMap9, false, 9);
		room10 = new Room("Empty room",  inv, obs, roomMap10, false, 10);
		room11 = new Room("Empty room",  inv, obs, roomMap11, false, 11);
		room12 = new Room("Empty room",  inv, obs, roomMap12, false, 12);
		room13 = new Room("Empty room",  inv, obs, roomMap13, false, 13);
		room14 = new Room("Empty room",  inv, obs, roomMap14, false, 14);
		room15 = new Room("Empty room",  inv, obs, roomMap15, false, 15);
		room16 = new Room("Empty room",  inv, obs, roomMap16, false, 16);
		room17 = new Room("Empty room",  inv, obs, roomMap17, false, 17);
		room18 = new Room("Empty room",  inv, obs, roomMap18, false, 18);
		room19 = new Room("Empty room",  inv, obs, roomMap19, false, 19);
		room20 = new Room("Empty room",  inv, obs, roomMap20, false, 20);
		room21 = new Room("Empty room",  inv, obs, roomMap21, false, 21);
		room22 = new Room("Empty room",  inv, obs, roomMap22, false, 22);
		room23 = new Room("Empty room",  inv, obs, roomMap23, false, 23);
		room24 = new Room("Empty room",  inv, obs, roomMap24, false, 24);
		room25 = new Room("Empty room",  inv, obs, roomMap25, false, 25);
		room26 = new Room("Empty room",  inv, obs, roomMap26, false, 26);
		room27 = new Room("Empty room",  inv, obs, roomMap27, false, 27);
		room28 = new Room("Empty room",  inv, obs, roomMap28, false, 28);
		room29 = new Room("You enter a small stone room, it is empty. There are four doorways at each cardinal direction.",  inv, obs, roomMap29, false, 29);
		room30 = new Room("You enter a small stone room, there is a large hole in the floor and an exit on the other side.",  inv, hole, roomMap30, false, 30);
		room31 = new Room("Empty room",  inv, obs, roomMap31, false, 31);
		room32 = new Room("Empty room",  inv, obs, roomMap32, false, 32);
		room33 = new Room("You enter a small stone room, there is an exit on the opposite side of the room.",  inv, weird_door, roomMap33, false, 33);
		room34 = new Room("You enter a small stone room, the stench of goblin is unbearable. There is an exit to the north.",  inv, obs, roomMap34, false, 34);
		room35 = new Room("You enter a small stone room, it is empty. There are three doorways at each cardinal direction except east.",  inv, obs, roomMap35, false, 35);
		room36 = new Room("Empty room",  inv, obs, roomMap36, false, 36);
		room37 = new Room("Empty room",  inv, obs, roomMap37, false, 37);
		room38 = new Room("Empty room",  inv, obs, roomMap38, false, 38);
		room39 = new Room("You enter a small stone room, in the center of the room there is a pair of shears lying on a table. There is an exit on the opposite side of the room.",  inv39, obs, roomMap39, false, 39);
		room40 = new Room("You enter a small stone room, vines and other brush grow out of control from a hole in the west wall. There is an exit to your east",  inv, vines, roomMap40, false, 40);
		room41 = new Room("You enter a small stone room, there is an exit to your north",  inv, obs, roomMap41, false, 41);
		room42 = new Room("You enter a large stone amphitheater, in the center of the room you see a large creature. It is half bull and half man, you recognize this as the minotaur",  inv, obs, roomMap42, false, 42);
		allRooms.add(room1);
		allRooms.add(room2);
		allRooms.add(room3);
		allRooms.add(room4);
		allRooms.add(room5);
		allRooms.add(room6);
		allRooms.add(room7);
		allRooms.add(room8);
		allRooms.add(room9);
		allRooms.add(room10);
		allRooms.add(room11);
		allRooms.add(room12);
		allRooms.add(room13);
		allRooms.add(room14);
		allRooms.add(room15);
		allRooms.add(room16);
		allRooms.add(room17);
		allRooms.add(room18);
		allRooms.add(room19);
		allRooms.add(room20);
		allRooms.add(room21);
		allRooms.add(room22);
		allRooms.add(room23);
		allRooms.add(room24);
		allRooms.add(room25);
		allRooms.add(room26);
		allRooms.add(room27);
		allRooms.add(room28);
		allRooms.add(room29);
		allRooms.add(room30);
		allRooms.add(room31);
		allRooms.add(room32);
		allRooms.add(room33);
		allRooms.add(room34);
		allRooms.add(room35);
		allRooms.add(room36);
		allRooms.add(room37);
		allRooms.add(room38);
		allRooms.add(room39);
		allRooms.add(room40);
		allRooms.add(room41);
		allRooms.add(room42);
		
		
		roomMap1.put("north", 2);
		roomMap1.put("south", 3);
		roomMap1.put("east", 4);
		roomMap1.put("west", 5);
		
		roomMap2.put("south", 1);
		roomMap2.put("north", 30);
		
		roomMap3.put("north", 1);
		
		roomMap4.put("west", 1);
		roomMap4.put("east", 7);
		
		roomMap5.put("east", 1);
		roomMap5.put("west", 6);
		
		roomMap6.put("east", 5);
		
		roomMap7.put("west", 4);
		roomMap7.put("east", 8);
		
		roomMap8.put("west", 7);
		roomMap8.put("north", 9);
		
		roomMap9.put("south", 8);
		roomMap9.put("north", 10);
		
		roomMap10.put("south", 9);
		roomMap10.put("north", 11);
		
		roomMap11.put("north", 12);
		roomMap11.put("south", 10);
		roomMap11.put("east", 14);
		roomMap11.put("west", 27);
		
		roomMap12.put("north", 13);
		roomMap12.put("south", 11);
		
		roomMap13.put("south", 12);
		
		roomMap14.put("west", 11);
		roomMap14.put("east", 15);
		
		roomMap15.put("west", 14);
		roomMap15.put("east", 16);
		
		roomMap16.put("north", 17);
		roomMap16.put("south", 23);
		roomMap16.put("east", 19);
		roomMap16.put("west", 15);
		
		roomMap17.put("south", 16);
		roomMap17.put("north", 18);
		
		roomMap18.put("south", 17);
		
		roomMap19.put("west", 16);
		roomMap19.put("east", 20);
		
		roomMap20.put("west", 19);
		roomMap20.put("north", 21);
		roomMap20.put("south", 22);
		
		roomMap21.put("south", 20);
		
		roomMap22.put("north", 20);
		
		roomMap23.put("north", 16);
		roomMap23.put("south", 24);
		
		roomMap24.put("north", 23);
		roomMap24.put("south", 25);
		
		roomMap25.put("north", 24);
		roomMap25.put("south", 26);
		
		roomMap26.put("north", 25);
		
		roomMap27.put("east", 11);
		roomMap27.put("west", 28);
		
		roomMap28.put("east", 27);
		roomMap28.put("west", 29);
		
		roomMap29.put("north", 31);
		roomMap29.put("south", 30);
		roomMap29.put("east", 28);
		roomMap29.put("west", 33);
		
		roomMap30.put("north", 29);
		roomMap30.put("south", 2);
		
		roomMap31.put("south", 29);
		roomMap31.put("north", 32);
		
		roomMap32.put("south", 31);
		
		roomMap33.put("east", 29);
		roomMap33.put("west", 34);
		
		roomMap34.put("east", 33);
		roomMap34.put("north", 35);
		
		roomMap35.put("south", 34);
		roomMap35.put("west", 36);
		roomMap35.put("north", 39);
		
		roomMap36.put("east", 35);
		roomMap36.put("west", 37);
		
		roomMap37.put("east", 36);
		roomMap37.put("south", 38);
		
		roomMap38.put("north", 37);
		
		roomMap39.put("south", 35);
		roomMap39.put("north", 40);
		
		roomMap40.put("south", 39);
		roomMap40.put("east", 41);
		
		roomMap41.put("west", 40);
		roomMap41.put("north", 42);
		
		roomMap42.put("south", 41);
		
		roomArray[1] = room1;
		roomArray[2] = room2; 
		roomArray[3] = room3;
		roomArray[4] = room4;
		roomArray[5] = room5;
		roomArray[6] = room6;
		roomArray[7] = room7;
		roomArray[8] = room8;
		roomArray[9] = room9;
		roomArray[10] = room10;
		roomArray[11] = room11;
		roomArray[12] = room12;
		roomArray[13] = room13;
		roomArray[14] = room14;
		roomArray[15] = room15;
		roomArray[16] = room16;
		roomArray[17] = room17;
		roomArray[18] = room18;
		roomArray[19] = room19;
		roomArray[20] = room20;
		roomArray[21] = room21;
		roomArray[22] = room22;
		roomArray[23] = room23;
		roomArray[24] = room24;
		roomArray[25] = room25;
		roomArray[26] = room26;
		roomArray[27] = room27;
		roomArray[28] = room28;
		roomArray[29] = room29;
		roomArray[30] = room30;
		roomArray[31] = room31;
		roomArray[32] = room32;
		roomArray[33] = room33;
		roomArray[34] = room34;
		roomArray[35] = room35;
		roomArray[36] = room36;
		roomArray[37] = room37;
		roomArray[38] = room38;
		roomArray[39] = room39;
		roomArray[40] = room40;
		roomArray[41] = room41;
		roomArray[42] = room42;

	}
	
	//Creates the actors
	public void initPlayer() {
		
		
		targets = new HashMap<String, Actor>();
	
		ArrayList<Ability> VillagerAbilities = new ArrayList<Ability>();
		villager = new NPC(10, 10, 0, 0, 1, 0, 0, 0, VillagerAbilities, "Villager", "Hello Traveler!", 100, "An old man with tattered clothing", "Villager", inv, room3, false);
		villagerDeadVal = 0;
		targets.put("villager", villager);
		
	
	
	}
	
	//Fills in player response, adds responses to output strings
	public void initResponses(ArrayList<Message<String, Integer>> DBoutputStrings) {
		PlayerResp = "Greetings";
		Message<String, Integer> respMsg = new Message<String, Integer>(PlayerResp, 0);
		DBoutputStrings.add(respMsg);
		NPCResp = "The " + villager.getName() + " says " + villager.getDialogue();
		Message<String, Integer> npcrespMsg = new Message<String, Integer>(NPCResp, 0);
		DBoutputStrings.add(npcrespMsg);

	}

	//getters
	public Player getPlayer() {
		return player;
	}

	
	public NPC getVillager() {
		return villager;
	}
	
	
	public int getVillagerHP() {
		return villager.getHP();
	}
	
	

	public int getVillagerDead() {
		return villagerDeadVal;
	}
	public ArrayList<Message<String, Integer>> getOutputStrings(){
		return outputstrings;
	}
	
	public Room getRoomByRoomId(int roomID) {
		//System.out.println(roomID);
		return roomArray[roomID];
	}
	
	public HashMap<String, Actor> getTargets(){
		return targets;
	}
	
	//setters

	
	public void setVillagerHP(int HP) {
		villager.setHP(HP);
	}
	
	
	public void setVillagerDead(int villagerDeadVal) {
		this.villagerDeadVal = villagerDeadVal;
	}
	
	public void setOutputStrings(ArrayList<Message<String, Integer>> outputstrings) {
		this.outputstrings = outputstrings;
	}
	
	public ArrayList<Room> getAllRooms(){
		return allRooms;
	}
	
	public void setPlayer(Player newPlayer) {
		this.player = newPlayer;
	}
}