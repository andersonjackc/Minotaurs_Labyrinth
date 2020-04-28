package edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist;


import java.util.List;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
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
	
	//updates
	public Player updatePlayer(Player newPlayer);
		
	public List<Message<String, Integer>> updateTextHistory();
	
	public List<Enemy> updateEnemies(List<Enemy> enemyList);

	public List<NPC> updateNPCs(List<NPC> npcList);

	public List<Item> updateItems(List<Item> itemList);

	public List<Room> updateRooms(List<Room> roomList);
	
	public Obstacle updateObstacle(Obstacle newObstacle, int ObstacleID);
	
	//inserts
	public List<Message<String, Integer>> insertIntoTextHistory(Message<String, Integer> newMessage);


}
