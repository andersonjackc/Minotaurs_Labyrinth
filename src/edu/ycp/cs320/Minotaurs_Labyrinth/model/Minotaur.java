package edu.ycp.cs320.Minotaurs_Labyrinth.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;

public class Minotaur {
	
	
	Room[] roomArray;
	Room room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15, room16, room17, room18, room19, room20, room21, room22, room23, room24, room25, room26, room27, room28, room29, room30, room31, room32, room33, room34, room35, room36, room37, room38, room39, room40, room41, room42;
	HashMap<String, Room> roomMap1, roomMap2, roomMap3, roomMap4, roomMap5, roomMap6, roomMap7, roomMap8, roomMap9, roomMap10, roomMap11, roomMap12, roomMap13, roomMap14, roomMap15, roomMap16, roomMap17, roomMap18, roomMap19, roomMap20, roomMap21, roomMap22, roomMap23, roomMap24, roomMap25, roomMap26, roomMap27, roomMap28, roomMap29, roomMap30, roomMap31, roomMap32, roomMap33, roomMap34, roomMap35, roomMap36, roomMap37, roomMap38, roomMap39, roomMap40, roomMap41, roomMap42;
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
				
		requirement = new Item("Key", 0, false, false, true, 0, "Key", "test", "test");
		inv = new Inventory(0, 0, inventory);
		obs = new Obstacle("No obstacle", "normal", null);
		obs2 = new Obstacle("The room is locked", "locked", requirement);
		
		roomArray = new Room[43];
		roomMap1 = new HashMap<String, Room>();
		roomMap2 = new HashMap<String, Room>();
		roomMap3 = new HashMap<String, Room>();
		roomMap4 = new HashMap<String, Room>();
		roomMap5 = new HashMap<String, Room>();
		roomMap6 = new HashMap<String, Room>();
		roomMap7 = new HashMap<String, Room>();
		roomMap8 = new HashMap<String, Room>();
		roomMap9 = new HashMap<String, Room>();
		roomMap10 = new HashMap<String, Room>();
		roomMap11 = new HashMap<String, Room>();
		roomMap12 = new HashMap<String, Room>();
		roomMap13 = new HashMap<String, Room>();
		roomMap14 = new HashMap<String, Room>();
		roomMap15 = new HashMap<String, Room>();
		roomMap16 = new HashMap<String, Room>();
		roomMap17 = new HashMap<String, Room>();
		roomMap18 = new HashMap<String, Room>();
		roomMap19 = new HashMap<String, Room>();
		roomMap20 = new HashMap<String, Room>();
		roomMap21 = new HashMap<String, Room>();
		roomMap22 = new HashMap<String, Room>();
		roomMap23 = new HashMap<String, Room>();
		roomMap24 = new HashMap<String, Room>();
		roomMap25 = new HashMap<String, Room>();
		roomMap26 = new HashMap<String, Room>();
		roomMap27 = new HashMap<String, Room>();
		roomMap28 = new HashMap<String, Room>();
		roomMap29 = new HashMap<String, Room>();
		roomMap30 = new HashMap<String, Room>();
		roomMap31 = new HashMap<String, Room>();
		roomMap32 = new HashMap<String, Room>();
		roomMap33 = new HashMap<String, Room>();
		roomMap34 = new HashMap<String, Room>();
		roomMap35 = new HashMap<String, Room>();
		roomMap36 = new HashMap<String, Room>();
		roomMap37 = new HashMap<String, Room>();
		roomMap38 = new HashMap<String, Room>();
		roomMap39 = new HashMap<String, Room>();
		roomMap40 = new HashMap<String, Room>();
		roomMap41 = new HashMap<String, Room>();
		roomMap42 = new HashMap<String, Room>();
		
		
		room1 = new Room("You enter a small stone room there are four doorways at each cardinal direction.", inv, obs, roomMap1, true, 1);
		room2 = new Room("You enter a small stone room, the stench of ogre is unbearable. The only exit is the way you entered.", inv, obs, roomMap2, false, 2);
		room3 = new Room("You enter a small stone room, a villager stands before you. The only exit is the way you entered.", inv, obs, roomMap3, false, 3);
		room4 = new Room("You enter a small stone room, it is empty. The only exit is the way you entered.",  inv, obs2, roomMap4, false, 4);
		room5 = new Room("You enter a small stone room, it is empty. The only exit is the way you entered.",  inv, obs, roomMap5, false, 5);
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
		room29 = new Room("Empty room",  inv, obs, roomMap29, false, 29);
		room30 = new Room("Empty room",  inv, obs, roomMap30, false, 30);
		room31 = new Room("Empty room",  inv, obs, roomMap31, false, 31);
		room32 = new Room("Empty room",  inv, obs, roomMap32, false, 32);
		room33 = new Room("Empty room",  inv, obs, roomMap33, false, 33);
		room34 = new Room("Empty room",  inv, obs, roomMap34, false, 34);
		room35 = new Room("Empty room",  inv, obs, roomMap35, false, 35);
		room36 = new Room("Empty room",  inv, obs, roomMap36, false, 36);
		room37 = new Room("Empty room",  inv, obs, roomMap37, false, 37);
		room38 = new Room("Empty room",  inv, obs, roomMap38, false, 38);
		room39 = new Room("Empty room",  inv, obs, roomMap39, false, 39);
		room40 = new Room("Empty room",  inv, obs, roomMap40, false, 40);
		room41 = new Room("Empty room",  inv, obs, roomMap41, false, 41);
		room42 = new Room("Empty room",  inv, obs, roomMap42, false, 42);
		
		outputstrings = new ArrayList<Message<String, Integer>>();
		Message<String, Integer> initialMessage = new Message<String, Integer>("You enter a small stone room there are four doorways at each cardinal direction.", 0);
		outputstrings.add(initialMessage);
		
		
		roomMap1.put("north", room2);
		roomMap1.put("south", room3);
		roomMap1.put("east", room4);
		roomMap1.put("west", room5);
		
		roomMap2.put("south", room1);
		roomMap2.put("north", room30);
		
		roomMap3.put("north", room1);
		
		roomMap4.put("west", room1);
		roomMap4.put("east", room7);
		
		roomMap5.put("east", room1);
		roomMap5.put("west", room6);
		
		roomMap6.put("east", room5);
		
		roomMap7.put("west", room4);
		roomMap7.put("east", room8);
		
		roomMap8.put("west", room7);
		roomMap8.put("north", room9);
		
		roomMap9.put("south", room8);
		roomMap9.put("north", room10);
		
		roomMap10.put("south", room9);
		roomMap10.put("north", room11);
		
		roomMap11.put("north", room12);
		roomMap11.put("south", room10);
		roomMap11.put("east", room14);
		roomMap11.put("west", room27);
		
		roomMap12.put("north", room13);
		roomMap12.put("south", room11);
		
		roomMap13.put("south", room12);
		
		roomMap14.put("west", room11);
		roomMap14.put("east", room15);
		
		roomMap15.put("west", room14);
		roomMap15.put("east", room16);
		
		roomMap16.put("north", room17);
		roomMap16.put("south", room23);
		roomMap16.put("east", room19);
		roomMap16.put("west", room15);
		
		roomMap17.put("south", room16);
		roomMap17.put("north", room18);
		
		roomMap18.put("south", room17);
		
		roomMap19.put("west", room16);
		roomMap19.put("east", room20);
		
		roomMap20.put("west", room19);
		roomMap20.put("north", room21);
		roomMap20.put("south", room22);
		
		roomMap21.put("south", room20);
		
		roomMap22.put("north", room20);
		
		roomMap23.put("north", room16);
		roomMap23.put("south", room24);
		
		roomMap24.put("north", room23);
		roomMap24.put("south", room25);
		
		roomMap25.put("north", room24);
		roomMap25.put("south", room26);
		
		roomMap26.put("north", room25);
		
		roomMap27.put("east", room11);
		roomMap27.put("west", room28);
		
		roomMap28.put("east", room27);
		roomMap28.put("west", room29);
		
		roomMap29.put("north", room31);
		roomMap29.put("south", room30);
		roomMap29.put("east", room28);
		roomMap29.put("west", room33);
		
		roomMap30.put("north", room29);
		roomMap30.put("south", room2);
		
		roomMap31.put("south", room29);
		roomMap31.put("north", room32);
		
		roomMap32.put("south", room31);
		
		roomMap33.put("east", room29);
		roomMap33.put("west", room34);
		
		roomMap34.put("east", room33);
		roomMap34.put("north", room35);
		
		roomMap35.put("south", room34);
		roomMap35.put("west", room36);
		roomMap35.put("north", room39);
		
		roomMap36.put("east", room35);
		roomMap36.put("west", room37);
		
		roomMap37.put("east", room36);
		roomMap37.put("south", room38);
		
		roomMap38.put("north", room37);
		
		roomMap39.put("south", room35);
		roomMap39.put("north", room40);
		
		roomMap40.put("south", room39);
		roomMap40.put("east", room41);
		
		roomMap41.put("west", room40);
		roomMap41.put("north", room42);
		
		roomMap42.put("south", room41);
		
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
		player = new Player(1000, 20, 0, 0, 2, 0, 0, 0, null, "normal", inv, room1, false);
		roomPosition = 1;
		
		ogre = new Enemy(10, 10, 0, 0, 1, 0, 0, 0, null, "ogre", "Grr lets fight", 0, "A large ogre with a club, he has a leather tunic", "Ogre", inv, room2, false);
		enemyDeadVal = 0;
		
		ArrayList<Ability> VillagerAbilities = new ArrayList<Ability>();
		villager = new NPC(10, 10, 0, 0, 1, 0, 0, 0, VillagerAbilities, "Villager", "Hello Traveler!", 100, "An old man with tattered clothing", "Villager", inv, room3, false);
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
	
	public Room getRoomByRoomId(int roomID) {
		//System.out.println(roomID);
		return roomArray[roomID];
	}
	
}