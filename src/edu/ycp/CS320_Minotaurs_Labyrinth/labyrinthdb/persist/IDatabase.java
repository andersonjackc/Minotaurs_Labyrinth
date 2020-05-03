package edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist;


import java.util.HashMap;
import java.util.List;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Pair;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;

public interface IDatabase {

	//finds
	public List<Player> findAllPlayers();
	
	public List<Room> findAllRooms();
	
	public List<Enemy> findAllEnemies();
	
	public List<NPC> findAllNPCs();

	public List<Item> findAllItems();
	
	public List<Message<String, Integer>> findTextHistory();
	
	public Obstacle findObstacle(int ObstacleId);
	
	public Inventory findInventory(int InventoryId);
	
	public List<Item> findItemList(int itemListId);
	
	public HashMap<String, Integer> findMap(int roomId);
	
	public Room findRoom(int roomId);
	
	public Pair<Integer, Integer> findMapArraySize();
	
	public Pair<Integer, Integer> findCoordinates(int roomId);
	
	public List<Message<String, Integer>> findChoicesForNPC(String npcName);
	
	public String findResponse(int roomID, int playerChoice);
	
	public Integer findTextHistoryCount();
	
	public Integer findTextHistoryMax();
	
	public Integer findTextHistoryIDbyString(String message);
	
	public List<Ability> findAllAbilities();
	
	//updates
	public Player updatePlayer(Player newPlayer);
		
	public List<Message<String, Integer>> updateTextHistory();
	
	public List<Enemy> updateEnemies(List<Enemy> enemyList);

	public List<NPC> updateNPCs(List<NPC> npcList);

	public List<Item> updateItems(List<Item> itemList);

	public List<Room> updateRooms(List<Room> roomList);
	
	public Obstacle updateObstacle(Obstacle newObstacle, int ObstacleID);
	
	public Inventory updateInventory(Inventory inv, int invID);
	
	public List<Item> updateItemList(List<Item> iList, int iListID);
	
	public List<Ability> updateAbilityList(List<Ability> iList, int iListID);	
	
	//inserts
	public List<Message<String, Integer>> insertIntoTextHistory(Message<String, Integer> newMessage);

	//removes
	public String removeTextHistoryByMessage(String message);
	
	public String removeTextHistoryByID(int ID);
	
	//restart
	public Message<String, Integer> restartGame();
}
