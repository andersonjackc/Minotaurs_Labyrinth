package edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.cs320.booksdb.persist.ReadCSV;

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
				List<Ability> tmpList = getAbilities();
				while(i.hasNext()) {
					
					abilities.add(tmpList.get(Integer.parseInt(i.next())-1));
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
				List<Item> tmpList = getItems();
				while(i.hasNext()) {
					
					items.add(tmpList.get(Integer.parseInt(i.next())-1));
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
				
				room.setDescription(i.next());
				
				List<Inventory> tmpList = getInventory();
				room.setInventory(tmpList.get(Integer.parseInt(i.next())-1));
				
				List<Obstacle> tmpList2 = getObstacles();
				room.setObstacle(tmpList2.get(Integer.parseInt(i.next())-1));
				
				List<HashMap<String, Room>> tmpList3 = getMaps();
				
				room.setRoomMap(tmpList3.get(Integer.parseInt(i.next())-1));
				
				if(Integer.parseInt(i.next())==0) {
					room.setIsFound(false);
				}else {
					room.setIsFound(true);	
				}
				
				room.setRoomId(Integer.parseInt(i.next()));
				
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
	
	public static List<HashMap<String, Room>> getMaps() throws IOException{
		
		List<HashMap<String, Room>> mapList = new ArrayList<HashMap<String, Room>>();
		ReadCSV readMaps = new ReadCSV("RoomMap.csv");
		try {
			while (true) {
				List<String> tuple = readMaps.next();
				if (tuple == null) {
					break;
				}
				
				List<Room> roomList = getRooms();
				Iterator<String> i = tuple.iterator();
				
				if(mapList.size() == Integer.parseInt(i.next())) {
					HashMap<String, Room> prevMap = mapList.get(mapList.size()-1);
					prevMap.put(i.next(), roomList.get(Integer.parseInt(i.next())-1));
				}else {
					HashMap<String, Room> roomMap = mapList.get(mapList.size()-1);
					roomMap.put(i.next(), roomList.get(Integer.parseInt(i.next())-1));
					mapList.add(roomMap);
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
}

