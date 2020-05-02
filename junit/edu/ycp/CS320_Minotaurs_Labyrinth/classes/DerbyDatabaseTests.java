package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DatabaseProvider;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DerbyDatabase;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.IDatabase;


public class DerbyDatabaseTests {

	private IDatabase db = null;
	
	ArrayList<Player> Players = null;
	ArrayList<Enemy>   Enemies   = null;
	ArrayList<NPC> NPCs = null;
	ArrayList<Message<String, Integer>>   TextHistory   = null;
	ArrayList<Item> Item = null, ItemList = null;
	ArrayList<Room> Rooms = null;
	ArrayList<Obstacle> Obstacles = new ArrayList<Obstacle>();
	ArrayList<Inventory> Inventorys = new ArrayList<Inventory>();
	ArrayList<HashMap> Maps = new ArrayList<HashMap>();
	ArrayList<Pair<Integer, Integer>> Coords = new ArrayList<Pair<Integer, Integer>>();
	ArrayList<Pair<Integer, Integer>> Sizes = new ArrayList<Pair<Integer, Integer>>();
	ArrayList<Message<String, Integer>>   Choices   = null;
	ArrayList<String>   Responses   = new ArrayList<String>();

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAllPlayers() {
		System.out.println("\n*** Testing FindAllPlayers ***");
		Players = (ArrayList<Player>) db.findAllPlayers();
		
		if (Players.isEmpty()) {
			System.out.println("No players found in Labyrinth Database");
			fail("No players returned from Library DB");
		}
		
		else {			
			for (Player Plyr : Players) {
				
				System.out.println(Plyr.getAtk() + ", " + Plyr.getDef() + ", " + Plyr.getGold() + ", " + Plyr.getHP() + ", " + Plyr.getMaxHP() + ", " + Plyr.getMaxResource() + ", " + Plyr.getName() + ", " + Plyr.getResource() + ", " + Plyr.getStatus() + ", " + Plyr.getXP() + ", " + Plyr.getAbilities() + ", " + Plyr.getCurrentRoom() + ", " + Plyr.getInventory() + ", " + Plyr.getIsDead());
			}			
		}
		
	}
	
	@Test
	public void testFindAllRooms() {
		System.out.println("\n*** Testing FindAllRooms ***");
		Rooms = (ArrayList<Room>) db.findAllRooms();
		
		if (Rooms.isEmpty()) {
			System.out.println("No rooms found in Labyrinth Database");
			fail("No rooms returned from Library DB");
		}
		
		else {			
			for (Room room : Rooms) {
				
				System.out.println(room.getDescription() + ", " + room.getRoomId() + ", " + room.getInventory() + ", " + room.getIsFound() + ", " + room.getObstacle() + ", " + room.getRoomMap());
			}			
		}
	}
	
	@Test
	public void testFindAllEnemies() {
		System.out.println("\n*** Testing FindAllEnemies ***");
		Enemies = (ArrayList<Enemy>) db.findAllEnemies();
		
		if (Enemies.isEmpty()) {
			System.out.println("No Enemies found in Labyrinth Database");
			fail("No enemies returned from Library DB");
		}
		
		else {			
			for (Enemy e : Enemies) {
				
				System.out.println(e.getAtk() + ", " + e.getDef() + ", " + e.getGold() + ", " + e.getHP() + ", " + e.getMaxHP() + ", " + e.getMaxResource() + ", " + e.getName() + ", " + e.getResource() + ", " + e.getStatus() + ", " + e.getXP() + ", " + e.getAbilities() + ", " + e.getCurrentRoom() + ", " + e.getInventory() + ", " + e.getIsDead() + ", " + e.getDescription() + ", " + e.getDialogue() + ", " + e.getAttitude());
			}			
		}
		
	}
	
	@Test
	public void testFindAllNPCs() {
		System.out.println("\n*** Testing FindAllNPCs ***");
		NPCs = (ArrayList<NPC>) db.findAllNPCs();
		
		if (NPCs.isEmpty()) {
			System.out.println("No NPCs found in Labyrinth Database");
			fail("No NPCs returned from Library DB");
		}
		
		else {			
			for (NPC n : NPCs) {
				
				System.out.println(n.getAtk() + ", " + n.getDef() + ", " + n.getGold() + ", " + n.getHP() + ", " + n.getMaxHP() + ", " + n.getMaxResource() + ", " + n.getName() + ", " + n.getResource() + ", " + n.getStatus() + ", " + n.getXP() + ", " + n.getAbilities() + ", " + n.getCurrentRoom() + ", " + n.getInventory() + ", " + n.getIsDead() + ", " + n.getDescription() + ", " + n.getDialogue() + ", " + n.getAttitude());
			}			
		}
	}
	
	@Test
	public void testFindTextHistory() {
		System.out.println("\n*** Testing FindTextHistory ***");
		TextHistory = (ArrayList<Message<String, Integer>>) db.findTextHistory();
		if (TextHistory.isEmpty()) {
			System.out.println("No TextHistory found in Labyrinth Database");
			fail("No TextHistory returned from Library DB");
		}
		
		else {		
			for (Message<String, Integer> m : TextHistory) {
				
				System.out.print(m.getMessage() + ", ");
			}			
		}
		System.out.println();
	}
	
	@Test
	public void testFindItems() {
		System.out.println("\n*** Testing FindAllItems ***");
		Item = (ArrayList<Item>) db.findAllItems();
		if (Item.isEmpty()) {
			System.out.println("No Items found in Labyrinth Database");
			fail("No Items returned from Library DB");
		}
		
		else {		
			for (Item item : Item) {
				System.out.println(item.getDescription() + ", " + item.getEffect() + ", "
				+ item.getFlammable() + ", " + item.getLit() + ", " + item.getThrowable() + ", " + item.getValue() + ", " + item.getName() + ", " 
				+ item.getVariety() + ", " + item.getAffectedStat());

			}			
		}
	}
	
	@Test
	public void testFindItemList() {
		System.out.println("\n*** Testing FindItemList ***");
		ItemList = (ArrayList<Item>) db.findItemList(1);
		if (ItemList.isEmpty()) {
			System.out.println("No ItemLists found in Labyrinth Database");
			fail("No ItemLists returned from Library DB");
		}
		
		else {		
			for (Item item : ItemList) {
				System.out.println(item.getDescription() + ", " + item.getEffect() + ", "
				+ item.getFlammable() + ", " + item.getLit() + ", " + item.getThrowable() + ", " + item.getValue() + ", " + item.getName() + ", " 
				+ item.getVariety() + ", " + item.getAffectedStat());

			}			
		}
	}
	
	@Test
	public void testFindInventory() {
		System.out.println("\n*** Testing FindInventory ***");
		Inventorys.add(db.findInventory(1));
		if (Inventorys.isEmpty()) {
			System.out.println("No Inventorys found in Labyrinth Database");
			fail("No Inventorys returned from Library DB");
		}
		
		else {		
			for (Inventory inv : Inventorys) {
				System.out.println(inv.getMaxQuant() + ", " + inv.getMaxStorage() + ", " + inv.getInventory());
			}
		}
	}
	
	@Test
	public void testFindObstacle() {
		System.out.println("\n*** Testing FindObstacle ***");
		Obstacles.add(db.findObstacle(1));
		if (Obstacles.isEmpty()) {
			System.out.println("No Obstacles found in Labyrinth Database");
			fail("No Obstacles returned from Library DB");
		}
		
		else {		
			for (Obstacle obs : Obstacles) {
				System.out.println(obs.getDescription() + ", " + obs.getStatus() + ", " + obs.getRequirement().getName());
			}
		}
	}
	
	@Test
	public void testFindCoordinates() {
		System.out.println("\n*** Testing FindCoordinates ***");
		Coords.add(db.findCoordinates(1));
		if (Coords.isEmpty()) {
			System.out.println("No Coordinates found in Labyrinth Database");
			fail("No Coordinates returned from Library DB");
		}
		
		else {		
			for (Pair<Integer, Integer> coord : Coords) {
				System.out.println(coord.getLeft() + ", " + coord.getRight());
			}
		}
	}
	
	@Test
	public void testFindMap() {
		System.out.println("\n*** Testing FindMap ***");
		Maps.add(db.findMap(1));
		if (Maps.isEmpty()) {
			System.out.println("No Maps found in Labyrinth Database");
			fail("No Maps returned from Library DB");
		}
		
		else {		
			for (HashMap map : Maps) {
				System.out.println();
				for(Object key : map.keySet()) {
					System.out.print(map.get(key) + ", ");
				}
				System.out.println(map.keySet());
			}
		}
	}
	
	@Test
	public void testFindRoom() {
		System.out.println("\n*** Testing FindRoom ***");
		Rooms = new ArrayList<Room>();
		Rooms.add(db.findRoom(1));
		if (Rooms.isEmpty()) {
			System.out.println("No Rooms found in Labyrinth Database");
			fail("No Rooms returned from Library DB");
		}
		
		else {		
			for (Room room : Rooms) {
				System.out.println(room.getDescription() + ", " + room.getRoomId() + ", " + room.getInventory() + ", " + room.getIsFound() + ", " + room.getObstacle() + ", " + room.getRoomMap());
			}
		}
	}
	
	@Test
	public void testFindMapArraySize() {
		System.out.println("\n*** Testing FindMapArraySize ***");
		Sizes.add(db.findMapArraySize());
		if (Sizes.isEmpty()) {
			System.out.println("MapArraySize not found in Labyrinth Database");
			fail("No MapArraySize returned from Library DB");
		}
		
		else {		
			for (Pair<Integer, Integer> size : Sizes) {
				System.out.println(size.getLeft() + ", " + size.getRight());
			}
		}
	}
	
	@Test
	public void testFindChoicesForNPC() {
		System.out.println("\n*** Testing FindChoicesForNPC ***");
		int count = db.findTextHistoryCount();
		Choices = (ArrayList<Message<String, Integer>>) db.findChoicesForNPC("test");
		int count2 = db.findTextHistoryCount();

		if (Choices.isEmpty()) {
			System.out.println("No Choices found for test in Labyrinth Database");
			fail("No Choices returned from Library DB");
		}
		
		else {	
			for (Message<String, Integer> c : Choices) {
				
				System.out.print(c.getMessage() + ", ");
			}			
		}
		System.out.println();
		
		int counter = db.findTextHistoryIDbyString("test");
		int counter2 = counter + (count2 - count);
		

		for(int i = counter; i <= counter2; i++) {
			
			System.out.println(db.removeTextHistoryByID(i));
		}
		

		if(count != db.findTextHistoryCount()) {
			System.out.println("TextHistory was not reset correctly");
			fail("TextHistory was not reset correctly");
		}
	}
	
	@Test
	public void testFindResponse() {
		System.out.println("\n*** Testing FindResponse ***");
		Responses.add(db.findResponse(43, 1));
		if (Responses.isEmpty()) {
			System.out.println("No Response found in Labyrinth Database");
			fail("No Responses returned from Library DB");
		}
		
		else {		
			for (String r : Responses) {
				
				System.out.println(r);
			}			
		}
		db.removeTextHistoryByMessage("test1");
		db.removeTextHistoryByMessage("test4");
		
	}
	
	@Test
	public void testInsertTestHistory() {
		System.out.println("\n*** Testing insertTextHistory ***");

		Message<String, Integer> text     = new Message<String, Integer>("Hello there.", 0);
		ArrayList<Message<String, Integer>> textHist = new ArrayList<Message<String, Integer>>();
		textHist = (ArrayList<Message<String, Integer>>) db.findTextHistory();
		
				
		// insert new text into DB
		ArrayList<Message<String, Integer>> textHistory = new ArrayList<Message<String, Integer>>();
		db.insertIntoTextHistory(text);
		textHistory = (ArrayList<Message<String, Integer>>) db.findTextHistory();
		
		// check the return value - should be a textHistory size > 0
		if (textHistory.size() == textHist.size()){
				fail("Failed to insert new message <" + text + "> into textHistory table");
		}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
		else {
				System.out.println("New message (Text: " + textHistory.get(textHistory.size()-1).getMessage() + ") successfully added to textHistory table");
				
				// now delete Book (and its Author) from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been used
				String removeText = db.removeTextHistoryByMessage(textHistory.get(textHistory.size()-1).getMessage());
				textHistory = (ArrayList<Message<String, Integer>>) db.findTextHistory();
				

				if(textHistory.size() == textHist.size()) {
					System.out.println("New message " + removeText);
				}
				else {
					System.out.println("Failed to remove message <" + text + "> from textHistory table");
					fail("Failed to remove message <" + text + "> from textHistory table");
				}
			}
		
		
	}
	
	@Test
	public void testTextHistoryCount() {
		System.out.println("\n*** Testing FindTextHistoryCount ***");
		int count;
		count = db.findTextHistoryCount();
		if (count == 0) {
			System.out.println("TextHistoryCount not found in Labyrinth Database");
			fail("No Count returned from Library DB");
		}
		
		else {		
			
				System.out.println(count);
			
		}
	}
	
	@Test
	public void testFindTextHistoryIDbyString() {
		System.out.println("\n*** Testing FindTextHistoryIDbyString ***");
		int ID;
		ID = db.findTextHistoryIDbyString("You enter a small stone room there are four doorways at each cardinal direction.");
		if (ID == 0) {
			System.out.println("ID not found in Labyrinth Database");
			fail("No ID returned from Library DB");
		}
		
		else {		
			
				System.out.println(ID);
			
		}
	}
	
	
}
