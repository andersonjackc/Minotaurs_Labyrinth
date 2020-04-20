package edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Gear;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.ReadCSV;

public class InitialData {

	public static List<Ability> getAbilities() throws IOException{
		
		List<Ability> abilityList = new ArrayList<Ability>();
		ReadCSV readAbilities = new ReadCSV("Ability.csv");
		try {
			while (true) {
				List<String> tuple = readAbilities.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Ability ability = new Ability(null, null, null, null, 0, 0);
				i.next();
				ability.setName(i.next());
				ability.setDescription(i.next());
				ability.setVariety(i.next());
				ability.setAffectedStat(i.next());
				ability.setEffect(Integer.parseInt(i.next()));
				ability.setCost(Integer.parseInt(i.next()));
				abilityList.add(ability);
			}
			
			System.out.println("abilitiesList loaded from CSV file");			
			return abilityList;
		} finally {
			readAbilities.close();
		}
	
	}
		
	public static List<ArrayList<Ability>> getAbilitiesList() throws IOException{
		
		List<ArrayList<Ability>> abilityList2 = new ArrayList<ArrayList<Ability>>();
		ReadCSV readAbilitiesList = new ReadCSV("AbilityList.csv");
		try {
			while (true) {
				List<String> tuple = readAbilitiesList.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ArrayList<Ability> abilities = new ArrayList<Ability>();
				ArrayList<Ability> tmpList = (ArrayList<Ability>)getAbilities();
				i.next();
				while(i.hasNext()) {
					
					abilities.add(getAbilitybyString(tmpList, i.next()));
				}
				
				abilityList2.add(abilities);
			
			}
			
			System.out.println("abilitiesList loaded from CSV file");			
			return abilityList2;
		} finally {
			readAbilitiesList.close();
		}
		
	}
	
	public static List<Inventory> getInventory() throws IOException{
		
		List<Inventory> inventoryList = new ArrayList<Inventory>();
		ReadCSV readInventory = new ReadCSV("Inventory.csv");
		try {
			while (true) {
				List<String> tuple = readInventory.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Inventory inventory = new Inventory(0, 0, null);
				i.next();
				inventory.setMaxStorage(Integer.parseInt(i.next()));
				inventory.setMaxQuant(Integer.parseInt(i.next()));
				List<ArrayList<Item>> tmpList = getItemList();
				inventory.setInventory(tmpList.get(Integer.parseInt(i.next())-1));
				
				inventoryList.add(inventory);
			}
			
			System.out.println("inventoryList loaded from CSV file");			
			return inventoryList;
		} finally {
			readInventory.close();
		}
	
		
		
	}
	
	public static List<Item> getItems() throws IOException{
		
		List<Item> itemList = new ArrayList<Item>();
		ReadCSV readItem = new ReadCSV("Item.csv");
		try {
			while (true) {
				List<String> tuple = readItem.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Item item = new Item(null, 0, null, null, null, 0, null, null, null);
				i.next();
				item.setDescription(i.next());
				item.setEffect(Integer.parseInt(i.next()));
				
				if(Integer.parseInt(i.next())==0) {
					item.setFlammable(false);
				}else {
					item.setFlammable(true);
				}
								
				if(Integer.parseInt(i.next())==0) {
				
					item.setLit(false);
				}else {
					
					item.setLit(true);
				}
				
				
				
				if(Integer.parseInt(i.next())==0) {
					item.setThrowable(false);
				}else {
					item.setThrowable(true);
				}
				
				item.setValue(Integer.parseInt(i.next()));
				
				item.setName(i.next());
				
				item.setVariety(i.next());
				
				item.setAffectedStat(i.next());
				
				itemList.add(item);
				
			}
			
			System.out.println("itemList loaded from CSV file");			
			return itemList;
		} finally {
			readItem.close();
		}
	}

	public static List<Gear> getGear() throws IOException{
		
		List<Gear> gearList = new ArrayList<Gear>();
		ReadCSV readGear = new ReadCSV("Gear.csv");
		try {
			while (true) {
				List<String> tuple = readGear.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Gear gear = new Gear(0, 0, 0, null, null, null, 0, null, null, null, 0, null, null);
				
				i.next();
				
				gear.setAtk(Integer.parseInt(i.next()));
				gear.setDef(Integer.parseInt(i.next()));
				gear.setHP(Integer.parseInt(i.next()));
				gear.setVariety(i.next());
				
				if(Integer.parseInt(i.next())==0) {
					gear.setEquipped(false);
				}else {
					gear.setEquipped(true);
				}
				
				gear.setDescription(i.next());
				gear.setEffect(Integer.parseInt(i.next()));
				
				if(Integer.parseInt(i.next())==0) {
					gear.setFlammable(false);
				}else {
					gear.setFlammable(true);
				}
				
				if(Integer.parseInt(i.next())==0) {
					gear.setLit(false);
				}else {
					gear.setLit(true);
				}
				
				if(Integer.parseInt(i.next())==0) {
					gear.setThrowable(false);
				}else {
					gear.setThrowable(true);
				}
				
				gear.setValue(Integer.parseInt(i.next()));
				
				gear.setName(i.next());
				
				gear.setAffectedStat(i.next());
				
				gearList.add(gear);
				
			}
			
			System.out.println("gearList loaded from CSV file");			
			return gearList;
		} finally {
			readGear.close();
		}
	}
	
	public static List<ArrayList<Item>> getItemList() throws IOException{
		
		List<ArrayList<Item>> itemList2 = new ArrayList<ArrayList<Item>>();
		ReadCSV readItemList = new ReadCSV("ItemList.csv");
		try {
			while (true) {
				List<String> tuple = readItemList.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ArrayList<Item> items = new ArrayList<Item>();
				ArrayList<Item> tmpList = (ArrayList<Item>) getItems();
				i.next();
				while(i.hasNext()) {
					
					items.add(getItembyString(tmpList, i.next()));
				}
				
				itemList2.add(items);
			
			}
			
			System.out.println("ArrayList<Item> loaded from CSV file");			
			return itemList2;
		} finally {
			readItemList.close();
		}
		
	}
	
	public static List<Room> getRooms() throws IOException{
		
		List<Room> roomList = new ArrayList<Room>();
		ReadCSV readRooms = new ReadCSV("Room.csv");
		try {
			while (true) {
				List<String> tuple = readRooms.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Room room = new Room(null, null, null, null, false, 0);
				
				
				room.setRoomId(Integer.parseInt(i.next()));
				
				room.setDescription(i.next());
				
				List<Inventory> tmpList = getInventory();
				room.setInventory(tmpList.get(Integer.parseInt(i.next())-1));
				
				List<Obstacle> tmpList2 = getObstacles();
				room.setObstacle(tmpList2.get(Integer.parseInt(i.next())-1));
				
				if(Integer.parseInt(i.next())==0) {
					room.setIsFound(false);
				}else {
					room.setIsFound(true);	
				}
				
				HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
				
				while(i.hasNext()) {
					tmpMap.put(i.next(), Integer.parseInt(i.next()));
				}
				
				
				roomList.add(room);
			}
			
			System.out.println("roomList loaded from CSV file");			
			return roomList;
		} finally {
			readRooms.close();
		}
	
	}
	
	public static List<Obstacle> getObstacles() throws IOException{
		
		List<Obstacle> obstacleList = new ArrayList<Obstacle>();
		ReadCSV readObstacle = new ReadCSV("Obstacle.csv");
		try {
			while (true) {
				List<String> tuple = readObstacle.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Obstacle obstacle = new Obstacle(null, null, null);
				i.next();
				obstacle.setDescription(i.next());
				
				obstacle.setStatus(i.next());
				
				List<Item> tmpList = getItems();
				
				obstacle.setRequirement(tmpList.get(Integer.parseInt(i.next())-1));
				
				obstacleList.add(obstacle);
				
			}
			
			System.out.println("obstacleList loaded from CSV file");			
			return obstacleList;
		} finally {
			readObstacle.close();
		}
	
	}
	
	public static List<HashMap<String, Integer>> getMaps() throws IOException{
		
		List<HashMap<String, Integer>> mapList = new ArrayList<HashMap<String, Integer>>();
		ReadCSV readMaps = new ReadCSV("RoomMap.csv");
		try {
			while (true) {
				List<String> tuple = readMaps.next();
				if (tuple == null) {
					break;
				}
				
				Iterator<String> i = tuple.iterator();
				
				while(i.hasNext()) {
					if(mapList.size() == Integer.parseInt(i.next())) {
						HashMap<String, Integer> prevMap = mapList.get(mapList.size()-1);
						prevMap.put(i.next(), Integer.parseInt(i.next()));
					}else {
						HashMap<String, Integer> roomMap = new HashMap<String, Integer>();
						roomMap.put(i.next(), Integer.parseInt(i.next()));
						mapList.add(roomMap);
					}
				}
			}
			
			System.out.println("roomList loaded from CSV file");			
			return mapList;
		} finally {
			readMaps.close();
		}
	}
	
	public static List<Enemy> getEnemies() throws IOException {
		List<Enemy> enemyList = new ArrayList<Enemy>();
		ReadCSV readEnemies = new ReadCSV("Enemy.csv");
		try {
			while (true) {
				List<String> tuple = readEnemies.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Enemy enemy = new Enemy(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, false);
				i.next();
				enemy.setMaxHP(Integer.parseInt(i.next()));
				enemy.setHP(Integer.parseInt(i.next()));
				enemy.setMaxResource(Integer.parseInt(i.next()));
				enemy.setResource(Integer.parseInt(i.next()));
				enemy.setAtk(Integer.parseInt(i.next()));
				enemy.setDef(Integer.parseInt(i.next()));
				enemy.setGold(Integer.parseInt(i.next()));
				enemy.setXP(Integer.parseInt(i.next()));
				List<ArrayList<Ability>> tmpList = getAbilitiesList();
				enemy.setAbilities(tmpList.get(Integer.parseInt(i.next())-1));
				enemy.setStatus(i.next());
				enemy.setDialogue(i.next());
				enemy.setAttitude(Integer.parseInt(i.next()));
				enemy.setDescription(i.next());
				enemy.setName(i.next());
				List<Inventory> tmpList2 = getInventory();
				enemy.setInventory(tmpList2.get(Integer.parseInt(i.next())-1));
				
				List<Room> tmpList3 = getRooms();
				enemy.setCurrentRoom(tmpList3.get(Integer.parseInt(i.next())-1));
				
				if(Integer.parseInt(i.next())==0) {
					enemy.setIsDead(false);
				}else {
					enemy.setIsDead(true);
				}
				
				enemyList.add(enemy);
			
			}
				
			
			System.out.println("enemyList loaded from CSV file");			
			return enemyList;
		} finally {
			readEnemies.close();
		}
	}
	
	public static List<Player> getPlayers() throws IOException {
	List<Player> playerList = new ArrayList<Player>();
	ReadCSV readPlayers = new ReadCSV("Player.csv");
	try {
		while (true) {
			List<String> tuple = readPlayers.next();
			if (tuple == null) {
				break;
			}
			Iterator<String> i = tuple.iterator();
			Player player = new Player(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, false, null);
			i.next();
			player.setMaxHP(Integer.parseInt(i.next()));
			player.setHP(Integer.parseInt(i.next()));
			player.setMaxResource(Integer.parseInt(i.next()));
			player.setResource(Integer.parseInt(i.next()));
			player.setAtk(Integer.parseInt(i.next()));
			player.setDef(Integer.parseInt(i.next()));
			player.setGold(Integer.parseInt(i.next()));
			player.setXP(Integer.parseInt(i.next()));
			List<ArrayList<Ability>> tmpList = getAbilitiesList();
			player.setAbilities(tmpList.get(Integer.parseInt(i.next())-1));
			player.setStatus(i.next());
			List<Inventory> tmpList2 = getInventory();
			player.setInventory(tmpList2.get(Integer.parseInt(i.next())-1));
			List<Room> tmpList3 = getRooms();
			player.setCurrentRoom(tmpList3.get(Integer.parseInt(i.next())-1));
			if(Integer.parseInt(i.next())==0) {
				player.setIsDead(false);
			}else {
				player.setIsDead(true);
			}
			player.setName(i.next());

			playerList.add(player);
		
		}
			
		
		System.out.println("playerList loaded from CSV file");			
		return playerList;
	} finally {
		readPlayers.close();
	}
}

	public static List<NPC> getNPCs() throws IOException {
		List<NPC> npcList = new ArrayList<NPC>();
		ReadCSV readNPCs = new ReadCSV("NPC.csv");
		try {
			while (true) {
				List<String> tuple = readNPCs.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				NPC npc = new NPC(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, false);
				i.next();
				npc.setMaxHP(Integer.parseInt(i.next()));
				npc.setHP(Integer.parseInt(i.next()));
				npc.setMaxResource(Integer.parseInt(i.next()));
				npc.setResource(Integer.parseInt(i.next()));
				npc.setAtk(Integer.parseInt(i.next()));
				npc.setDef(Integer.parseInt(i.next()));
				npc.setGold(Integer.parseInt(i.next()));
				npc.setXP(Integer.parseInt(i.next()));
				List<ArrayList<Ability>> tmpList = getAbilitiesList();
				npc.setAbilities(tmpList.get(Integer.parseInt(i.next())-1));
				npc.setStatus(i.next());
				npc.setDialogue(i.next());
				npc.setAttitude(Integer.parseInt(i.next()));
				npc.setDescription(i.next());
				npc.setName(i.next());
				List<Inventory> tmpList2 = getInventory();
				npc.setInventory(tmpList2.get(Integer.parseInt(i.next())-1));
				
				List<Room> tmpList3 = getRooms();
				npc.setCurrentRoom(tmpList3.get(Integer.parseInt(i.next())-1));
				
				if(Integer.parseInt(i.next())==0) {
					npc.setIsDead(false);
				}else {
					npc.setIsDead(true);
				}
				
				npcList.add(npc);
			
			}
				
			
			System.out.println("enemyList loaded from CSV file");			
			return npcList;
		} finally {
			readNPCs.close();
		}
	}

	public static List<Message<String, Integer>> getTextHistory() throws IOException{
		
		List<Message<String, Integer>> messageList = new ArrayList<Message<String, Integer>>();
		ReadCSV readTextHistory = new ReadCSV("TextHistory.csv");
		try {
			while (true) {
				List<String> tuple = readTextHistory.next();
				if (tuple == null) {
					break;
				}
				
				
				Iterator<String> i = tuple.iterator();
				
				Message<String, Integer> message = new Message<String, Integer>(null, 0);
				
				message.setMessage(i.next());
				
				message.setPlayerAction(Integer.parseInt(i.next()));
				
				messageList.add(message);
				
			}
			
			System.out.println("textHistory loaded from CSV file");			
			return messageList;
		} finally {
			readTextHistory.close();
		}
	}
	
	public static Ability getAbilitybyString(ArrayList<Ability> abilities, String name) {
        for(int i = 0; i < abilities.size(); i++) {
            if(abilities.get(i).getName().equals(name)) {
                return abilities.get(i);
            }
        }
        return null;
    }


	public static Item getItembyString(ArrayList<Item> Item, String name) {
        for(int i = 0; i < Item.size(); i++) {
            if(Item.get(i).getName().equals(name)) {
                return Item.get(i);
            }
        }
        return null;
    }
}

