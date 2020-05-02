package edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.AbilityComparator;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Choice;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Dialogue;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.InventoryComparator;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.ItemComparator;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.ObstacleComparator;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Pair;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DBUtil;


public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Documents/CS320/Minotaurs_Labyrinth/labyrinth.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	

	private void loadMessage(Message<String, Integer> message, ResultSet resultSet, int index) throws SQLException {
		message.setMessage(resultSet.getString(index++));
		message.setPlayerAction(resultSet.getInt(index++));
	}
	
	private void loadAbility(Ability ability, ResultSet resultSet, int index) throws SQLException {
		
		
		ability.setName(resultSet.getString(index++));
		
		ability.setDescription(resultSet.getString(index++));
		
		ability.setVariety(resultSet.getString(index++));
	
		ability.setAffectedStat(resultSet.getString(index++));
		
		ability.setEffect(resultSet.getInt(index++));
		
		ability.setCost(resultSet.getInt(index++));
	}
	
	private void loadRoom(Room room, ResultSet resultSet, int index, HashMap<String, Integer> roomMap, Obstacle obs, Inventory inv) throws SQLException {
		room.setRoomId(resultSet.getInt(index++));
		room.setDescription(resultSet.getString(index++));
		room.setInventory(inv);
		index++;
		room.setObstacle(obs);
		index++;
		room.setRoomMap(roomMap);
		index++;
		if(resultSet.getInt(index++)==1) {
			room.setIsFound(true);
		}else {
			room.setIsFound(false);
		}
	}
	

	private void loadPlayer(Player player, ResultSet resultSet, int index, ArrayList<Ability> abilities, Room currentRoom, Inventory inv) throws SQLException {

		player.setMaxHP(resultSet.getInt(index++));
		player.setHP(resultSet.getInt(index++));
		player.setMaxResource(resultSet.getInt(index++));
		player.setResource(resultSet.getInt(index++));
		player.setAtk(resultSet.getInt(index++));
		player.setDef(resultSet.getInt(index++));
		player.setGold(resultSet.getInt(index++));
		player.setXP(resultSet.getInt(index++));
		player.setAbilities(abilities);
		index++;
		player.setStatus(resultSet.getString(index++));
		player.setInventory(inv);
		index++;
		player.setCurrentRoom(currentRoom);
		index++;
		if(resultSet.getInt(index++)==1) {
			player.setIsDead(true);
		}
		else {
			player.setIsDead(false);
		}
		player.setName(resultSet.getString(index++));
		
	}
		
	private void loadEnemy(Enemy enemy, ResultSet resultSet, int index, ArrayList<Ability> abilities, Room currentRoom, Inventory inv) throws SQLException {

		enemy.setMaxHP(resultSet.getInt(index++));
		enemy.setHP(resultSet.getInt(index++));
		enemy.setMaxResource(resultSet.getInt(index++));
		enemy.setResource(resultSet.getInt(index++));
		enemy.setAtk(resultSet.getInt(index++));
		enemy.setDef(resultSet.getInt(index++));
		enemy.setGold(resultSet.getInt(index++));
		enemy.setXP(resultSet.getInt(index++));
		enemy.setAbilities(abilities);
		index++;
		enemy.setStatus(resultSet.getString(index++));
		enemy.setDialogue(resultSet.getString(index++));
		enemy.setAttitude(resultSet.getInt(index++));
		enemy.setDescription(resultSet.getString(index++));
		enemy.setName(resultSet.getString(index++));
		enemy.setInventory(inv);
		index++;
		enemy.setCurrentRoom(currentRoom);
		index++;
		if(resultSet.getInt(index++)==1) {
			enemy.setIsDead(true);
		}
		else {
			enemy.setIsDead(false);
		}
		
		
	}
	
	private void loadNPC(NPC NPC, ResultSet resultSet, int index, ArrayList<Ability> abilities, Room currentRoom, Inventory inv) throws SQLException {

		NPC.setMaxHP(resultSet.getInt(index++));
		NPC.setHP(resultSet.getInt(index++));
		NPC.setMaxResource(resultSet.getInt(index++));
		NPC.setResource(resultSet.getInt(index++));
		NPC.setAtk(resultSet.getInt(index++));
		NPC.setDef(resultSet.getInt(index++));
		NPC.setGold(resultSet.getInt(index++));
		NPC.setXP(resultSet.getInt(index++));
		NPC.setAbilities(abilities);
		index++;
		NPC.setStatus(resultSet.getString(index++));
		NPC.setDialogue(resultSet.getString(index++));
		NPC.setAttitude(resultSet.getInt(index++));
		NPC.setDescription(resultSet.getString(index++));
		NPC.setName(resultSet.getString(index++));
		NPC.setInventory(inv);
		index++;
		NPC.setCurrentRoom(currentRoom);
		index++;
		if(resultSet.getInt(index++)==1) {
			NPC.setIsDead(true);
		}
		else {
			NPC.setIsDead(false);
		}
		
		
	}
	
	private void loadObstacle(Obstacle obs, ResultSet resultSet, Item item, int index) throws SQLException {
		
		obs.setDescription(resultSet.getString(index++));
		obs.setStatus(resultSet.getString(index++));
		obs.setRequirement(item);
		index++;
		
	}

	private void loadItem(Item item, ResultSet resultSet, int index) throws SQLException {
		
		item.setDescription(resultSet.getString(index++));
		item.setEffect(resultSet.getInt(index++));
		
		if(resultSet.getInt(index++)==1) {
			item.setFlammable(true);
		}
		else {
			item.setFlammable(false);
		}
		
		if(resultSet.getInt(index++)==1) {
			item.setLit(true);
		}
		else {
			item.setLit(false);
		}
		
		if(resultSet.getInt(index++)==1) {
			item.setThrowable(true);
		}
		else {
			item.setThrowable(false);
		}
		
		item.setValue(resultSet.getInt(index++));
		
		item.setName(resultSet.getString(index++));
		
		item.setVariety(resultSet.getString(index++));
		
		item.setAffectedStat(resultSet.getString(index++));
		
		
	}
	
	private void loadInventory(Inventory inv, ResultSet resultSet, int index, ArrayList<Item> itemList) throws SQLException {

		inv.setMaxStorage(resultSet.getInt(index++));
		inv.setMaxQuant(resultSet.getInt(index++));
		inv.setInventory(itemList);
		index++;
		
	}

	/*private void loadDialogue(ResultSet resultSet, int index) throws SQLException {
		message.setMessage(resultSet.getString(index++));
		message.setPlayerAction(resultSet.getInt(index++));
	}
	
	private void loadChoices(ResultSet resultSet, int index) throws SQLException {
		message.setMessage(resultSet.getString(index++));
		message.setPlayerAction(resultSet.getInt(index++));
	}*/

	
	//  creates the tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt4 = null;	
				PreparedStatement stmt5 = null;	
				PreparedStatement stmt6 = null;	
				PreparedStatement stmt7 = null;	
				PreparedStatement stmt8 = null;	
				PreparedStatement stmt9 = null;	
				PreparedStatement stmt10 = null;	
				PreparedStatement stmt11 = null;	
				PreparedStatement stmt12 = null;	
				PreparedStatement stmt13 = null;	
				PreparedStatement stmt14 = null;	
				PreparedStatement stmt15 = null;	
				PreparedStatement stmt16 = null;	
				
			
				try {
					stmt1 = conn.prepareStatement(
						"create table ability (" +
						"	ability_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	name varchar(40)," +
						"	description varchar(7999)," +
						"	variety varchar(40)," +
						"	affectedStat varchar(40)," +
						"	effect integer," +
						"	cost integer" +
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Ability table created");
					
					stmt2 = conn.prepareStatement(
							"create table abilityList (" +
							"	abilityList_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	ability1 varchar(40)," +
							"	ability2 varchar(40)," +
							"	ability3 varchar(40)," +
							"	ability4 varchar(40)," +
							"	ability5 varchar(40)" +
							")"
					);
					stmt2.executeUpdate();
					
					System.out.println("abilityList table created");					
					
					
					
					stmt4 = conn.prepareStatement(
							"create table enemy (" +
							"	enemy_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	maxHP integer," +
							"	HP integer," +
							"	maxResource integer," +
							"	resource integer," +
							"	atk integer," +
							"	def integer," +
							"	gold integer," +
							"	XP integer," +
							"	abilities integer," +
							"	status varchar(40)," +
							"	dialogue varchar(7999)," +
							"	attitude integer," +
							"	description varchar(7999)," +
							"	name varchar(40)," +
							"	inventory integer," +
							"	currentRoom integer," +
							"	isDead integer" +
							")"
					);
					stmt4.executeUpdate();
					
					System.out.println("enemy table created");
					
					stmt5 = conn.prepareStatement(
							"create table gear (" +
							"	gear_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	atk integer," +
							"	def integer," +
							"	HP integer," +
							"	variety varchar(40)," +
							"	equipped integer," +
							"	description varchar(7999)," +
							"	effect integer," +
							"	flammable integer," +
							"	lit integer," +
							"	throwable integer," +
							"	value integer," +
							"	name varchar(40)," +
							"	affectedStat varchar(40)" +
							")"
					);
					stmt5.executeUpdate();
					
					System.out.println("gear table created");
					
					stmt6 = conn.prepareStatement(
							"create table inventory (" +
							"	inventory_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	maxStorage integer," +
							"	maxQuant integer," +
							"	inventory integer" +
							")"
					);
					stmt6.executeUpdate();
					
					System.out.println("inventory table created");
					
					stmt7 = conn.prepareStatement(
							"create table item (" +
							"	item integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	description varchar(7999)," +
							"	effect integer," +
							"	flammable integer," +
							"	lit integer," +
							"	throwable integer," +
							"	value integer," +
							"	name varchar(40)," +
							"	variety varchar(40)," +
							"	affectedStat varchar(40)" +
							")"
					);
					stmt7.executeUpdate();
					
					System.out.println("item table created");
					
					stmt8 = conn.prepareStatement(
							"create table itemList (" +
							"	itemList_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	item1 varchar(40)," +
							"	item2 varchar(40)," +
							"	item3 varchar(40)," +
							"	item4 varchar(40)," +
							"	item5 varchar(40)," +
							"	item6 varchar(40)," +
							"	item7 varchar(40)," +
							"	item8 varchar(40)," +
							"	item9 varchar(40)," +
							"	item10 varchar(40)," +
							"	item11 varchar(40)," +
							"	item12 varchar(40)," +
							"	item13 varchar(40)," +
							"	item14 varchar(40)," +
							"	item15 varchar(40)," +
							"	item16 varchar(40)," +
							"	item17 varchar(40)," +
							"	item18 varchar(40)," +
							"	item19 varchar(40)," +
							"	item20 varchar(40)," +
							"	item21 varchar(40)," +
							"	item22 varchar(40)," +
							"	item23 varchar(40)," +
							"	item24 varchar(40)," +
							"	item25 varchar(40)," +
							"	item26 varchar(40)," +
							"	item27 varchar(40)," +
							"	item28 varchar(40)," +
							"	item29 varchar(40)," +
							"	item30 varchar(40)," +
							"	item31 varchar(40)," +
							"	item32 varchar(40)," +
							"	item33 varchar(40)," +
							"	item34 varchar(40)," +
							"	item35 varchar(40)," +
							"	item36 varchar(40)," +
							"	item37 varchar(40)," +
							"	item38 varchar(40)," +
							"	item39 varchar(40)," +
							"	item40 varchar(40)," +
							"	item41 varchar(40)," +
							"	item42 varchar(40)," +
							"	item43 varchar(40)," +
							"	item44 varchar(40)," +
							"	item45 varchar(40)," +
							"	item46 varchar(40)," +
							"	item47 varchar(40)," +
							"	item48 varchar(40)," +
							"	item49 varchar(40)," +
							"	item50 varchar(40)" +
							")"
					);
					stmt8.executeUpdate();
					
					System.out.println("itemList table created");
					
					stmt9 = conn.prepareStatement(
							"create table NPC (" +
							"	NPC_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	maxHP integer," +
							"	HP integer," +
							"	maxResource integer," +
							"	resource integer," +
							"	atk integer," +
							"	def integer," +
							"	gold integer," +
							"	XP integer," +
							"	abilities integer," +
							"	status varchar(40)," +
							"	dialogue varchar(7999)," +
							"	attitude integer," +
							"	description varchar(7999)," +
							"	name varchar(40)," +
							"	inventory integer," +
							"	currentRoom integer," +
							"	isDead integer" +
							")"
					);
					stmt9.executeUpdate();
					
					System.out.println("NPC table created");
					
					stmt10 = conn.prepareStatement(
							"create table obstacle (" +
							"	obstacle_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	description varchar(7999)," +
							"	status varchar(40)," +
							"	requirement integer" +
							")"
					);
					stmt10.executeUpdate();
					
					System.out.println("obstacle table created");
								
					
					stmt11 = conn.prepareStatement(
							"create table player (" +
							"	player_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	maxHP integer," +
							"	HP integer," +
							"	maxResource integer," +
							"	resource integer," +
							"	atk integer," +
							"	def integer," +
							"	gold integer," +
							"	XP integer," +
							"	abilities integer," +
							"	status varchar(40)," +
							"	inventory integer," +
							"	currentRoom integer," +
							"	isDead integer," +
							"	name varchar(40)" +
							")"
					);
					stmt11.executeUpdate();
					
					System.out.println("player table created");
					
					stmt12 = conn.prepareStatement(
							"create table room (" +
							"	room_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	description varchar(7999)," +
							"	inventory integer," +
							"	obstacle integer," +
							"	roomMap integer," +
							"	isFound integer" +
							")"
					);
					stmt12.executeUpdate();
					
					System.out.println("room table created");
					
					stmt13 = conn.prepareStatement(
							"create table roomMap (" +
							"	roomId integer," +
							"	direction varchar(40)," +
							"	mappedRoomId integer," +
							"   xCoord integer," +
							"   yCoord integer" +
							")"
					);
					stmt13.executeUpdate();
					
					System.out.println("roomMap table created");
					
					stmt14 = conn.prepareStatement(
							"create table textHistory (" +
							"	textHistory_id integer primary key " +
							"	generated always as identity (start with 1, increment by 1), " +
							"	message varchar(7999)," +
							"	playerAction integer" +
							")"
					);
					stmt14.executeUpdate();
					
					System.out.println("textHistory table created");
					
					stmt15 = conn.prepareStatement(
							"create table dialogue (" +
							"	dialogue_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +	
							"	npc_id integer," +
							"	question varchar(7999)," +
							"	choice1_id integer," +
							"	choice2_id integer," +
							"	choice3_id integer" +
							")"
						);	
						stmt15.executeUpdate();
						
						System.out.println("Dialogue table created");
						
						stmt16 = conn.prepareStatement(
								"create table choices (" +
								"	npc_id integer," +									
								"	choice_id integer," +
								"	choice varchar(7999)," +
								"	response varchar(7999)," +
								"	status varchar(7999)" +
								")"
							);	
							stmt16.executeUpdate();
							
							System.out.println("Choices table created");
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
					DBUtil.closeQuietly(stmt10);
					DBUtil.closeQuietly(stmt11);
					DBUtil.closeQuietly(stmt12);
					DBUtil.closeQuietly(stmt13);
					DBUtil.closeQuietly(stmt14);
					DBUtil.closeQuietly(stmt15);
					DBUtil.closeQuietly(stmt16);
					
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Ability> Abilities;
				List<ArrayList<Ability>> AbilitiesList;
				List<Inventory> InventoryList;
				List<Item> Items;
				List<ArrayList<Item>> ItemList;
				List<Room> Rooms;
				List<Obstacle> Obstacles;
				List<Enemy> Enemies;
				List<Player> Players;
				List<NPC> NPCs;
				List<Message<String, Integer>> Messages;
				List<Dialogue> Dialogues;
				List<Choice> Choices;
				
				try {
					
					Abilities      = InitialData.getAbilities();
					AbilitiesList  = InitialData.getAbilitiesList();
					InventoryList  = InitialData.getInventory();
					Items          = InitialData.getItems();
				    ItemList       = InitialData.getItemList();
					Rooms          = InitialData.getRooms();
					Obstacles      = InitialData.getObstacles();
					Enemies        = InitialData.getEnemies();
					Players        = InitialData.getPlayers();
					NPCs           = InitialData.getNPCs();
					Messages       = InitialData.getTextHistory();
					Dialogues      = InitialData.getDialogues();
					Choices        = InitialData.getChoices();
					
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAbilities   = null;
				PreparedStatement insertAbilitiesList   = null;
				PreparedStatement insertInventoryList   = null;
				PreparedStatement insertItems   = null;
				PreparedStatement insertItemList   = null;
				PreparedStatement insertRooms   = null;
				PreparedStatement insertObstacles   = null;
				PreparedStatement insertMaps   = null;
				PreparedStatement insertEnemies   = null;
				PreparedStatement insertPlayers   = null;
				PreparedStatement insertNPCs   = null;
				PreparedStatement insertMessages = null;
				PreparedStatement insertDialogue = null;
				PreparedStatement insertChoices = null;


				try {
					
					insertAbilities = conn.prepareStatement("insert into ability (name, description, variety, affectedStat, "
							+ "effect, cost) values (?, ?, ?, ?, ?, ?)");
					for (Ability ability : Abilities) {
						insertAbilities.setString(1, ability.getName());
						insertAbilities.setString(2, ability.getDescription());
						insertAbilities.setString(3, ability.getVariety());
						insertAbilities.setString(4, ability.getAffectedStat());
						insertAbilities.setInt(5, ability.getEffect());
						insertAbilities.setInt(6, ability.getCost());
						insertAbilities.addBatch();
					}
					insertAbilities.executeBatch();
					
					System.out.println("Abilities table populated");
					

					
					insertAbilitiesList = conn.prepareStatement("insert into abilitylist (ability1, ability2, ability3, "
							+ "ability4, ability5) values (?, ?, ?, ?, ?)");
					for (ArrayList<Ability> ability : AbilitiesList) {
						for(int i=1; i<=ability.size(); i++) {
						insertAbilitiesList.setString(i, ability.get(i-1).getName());
						}
						insertAbilitiesList.addBatch();

					}
					insertAbilitiesList.executeBatch();					
					
					
					System.out.println("AbilitiesList table populated");					
					
					
					insertPlayers = conn.prepareStatement("insert into player (maxHP, HP, maxResource, resource, "
							+ "atk, def, gold, XP, abilities, status, "
							+ "inventory, currentRoom, isDead, name) values "
							+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Player player : Players) {
						insertPlayers.setInt(1, player.getMaxHP());
						insertPlayers.setInt(2, player.getHP());
						insertPlayers.setInt(3, player.getMaxResource());
						insertPlayers.setInt(4, player.getResource());
						insertPlayers.setInt(5, player.getAtk());
						insertPlayers.setInt(6, player.getDef());
						insertPlayers.setInt(7, player.getGold());
						insertPlayers.setInt(8, player.getXP());
						insertPlayers.setInt(9, AbilityIDbyList(player.getAbilities(), AbilitiesList));
						insertPlayers.setString(10, player.getStatus());
						insertPlayers.setInt(11, InventoryIDbyList(player.getInventory(), InventoryList));
						insertPlayers.setInt(12, player.getCurrentRoom().getRoomId());
						if(player.getIsDead()) {
						insertPlayers.setInt(13, 1);
						}else{
						insertPlayers.setInt(13, 0);
						}
						insertPlayers.setString(14, player.getName());
						insertPlayers.addBatch();
					}
					insertPlayers.executeBatch();	
					
					System.out.println("Players table populated");	
					
					insertEnemies = conn.prepareStatement("insert into enemy (maxHP, HP, maxResource, resource, "
							+ "atk, def, gold, XP, abilities, "
							+ "status, dialogue, attitude, description, "
							+ "name, inventory, currentRoom, isDead) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Enemy enemy : Enemies) {
						insertEnemies.setInt(1, enemy.getMaxHP());
						insertEnemies.setInt(2, enemy.getHP());
						insertEnemies.setInt(3, enemy.getMaxResource());
						insertEnemies.setInt(4, enemy.getResource());
						insertEnemies.setInt(5, enemy.getAtk());
						insertEnemies.setInt(6, enemy.getDef());
						insertEnemies.setInt(7, enemy.getGold());
						insertEnemies.setInt(8, enemy.getXP());
						insertEnemies.setInt(9, AbilityIDbyList(enemy.getAbilities(), AbilitiesList));
						insertEnemies.setString(10, enemy.getStatus());
						insertEnemies.setString(11, enemy.getDialogue());
						insertEnemies.setInt(12, enemy.getAttitude());
						insertEnemies.setString(13, enemy.getDescription());
						insertEnemies.setString(14, enemy.getName());
						insertEnemies.setInt(15, InventoryIDbyList(enemy.getInventory(), InventoryList));
						insertEnemies.setInt(16, enemy.getCurrentRoom().getRoomId());
						if(enemy.getIsDead()) {
						insertEnemies.setInt(17, 1);
						}else{
						insertEnemies.setInt(17, 0);
						}
						insertEnemies.setString(14, enemy.getName());
						insertEnemies.addBatch();
					}
					insertEnemies.executeBatch();
					
					System.out.println("Enemies table populated");		
					
					insertNPCs = conn.prepareStatement("insert into npc (maxHP, HP, maxResource, resource, "
							+ "atk, def, gold, XP, abilities, "
							+ "status, dialogue, attitude, description, "
							+ "name, inventory, currentRoom, isDead) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					
					for (NPC npc : NPCs) {
						insertNPCs.setInt(1, npc.getMaxHP());
						insertNPCs.setInt(2, npc.getHP());
						insertNPCs.setInt(3, npc.getMaxResource());
						insertNPCs.setInt(4, npc.getResource());
						insertNPCs.setInt(5, npc.getAtk());
						insertNPCs.setInt(6, npc.getDef());
						insertNPCs.setInt(7, npc.getGold());
						insertNPCs.setInt(8, npc.getXP());
						insertNPCs.setInt(9, AbilityIDbyList(npc.getAbilities(), AbilitiesList));
						insertNPCs.setString(10, npc.getStatus());
						insertNPCs.setString(11, npc.getDialogue());
						insertNPCs.setInt(12, npc.getAttitude());
						insertNPCs.setString(13, npc.getDescription());
						insertNPCs.setString(14, npc.getName());
						insertNPCs.setInt(15, InventoryIDbyList(npc.getInventory(), InventoryList));
						insertNPCs.setInt(16, npc.getCurrentRoom().getRoomId());
						if(npc.getIsDead()) {
						insertNPCs.setInt(17, 1);
						}else{
						insertNPCs.setInt(17, 0);
						}
						insertNPCs.setString(14, npc.getName());
						insertNPCs.addBatch();
					}
					insertNPCs.executeBatch();
					
					System.out.println("NPC table populated");		
					
					
					insertItems = conn.prepareStatement("insert into item (description, effect, flammable, lit, throwable, "
							+ "value, name, variety, affectedStat) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : Items) {
						insertItems.setString(1, item.getDescription());
						insertItems.setInt(2, item.getEffect());
						if(item.getFlammable()) {
							insertItems.setInt(3, 1);
						}else{
							insertItems.setInt(3, 0);
						}
						
						if(item.getLit()) {
							insertItems.setInt(4, 1);
						}else{
							insertItems.setInt(4, 0);
						}
						
						if(item.getThrowable()) {
							insertItems.setInt(5, 1);
						}else{
							insertItems.setInt(5, 0);
						}
						
						insertItems.setInt(6, item.getValue());
						insertItems.setString(7, item.getName());
						insertItems.setString(8, item.getVariety());
						insertItems.setString(9, item.getAffectedStat());
						insertItems.addBatch();
					}
					insertItems.executeBatch();	
					
					System.out.println("NPC table populated");	
					
					insertItemList = conn.prepareStatement("insert into itemlist (item1, "
							+ "item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, "
							+ "item13, item14, item15, item16, item17, item18, item19, item20, item21, item22, "
							+ "item23, item24, item25, item26, item27, item28, item29, item30, item31, item32, item33, item34, "
							+ "item35, item36, item37, item38, item39, item40, item41, item42, item43, item44, item45, "
							+ "item46, item47, item48, item49, item50) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
							+ "?, ?, ?, ?, ?, ?, ?)");
					for (List<Item> items : ItemList) {
						for(int i=1; i<=items.size(); i++) {
							insertItemList.setString(i, items.get(i-1).getName());
						}
						insertItemList.addBatch();
					}
					insertItemList.executeBatch();
					
					System.out.println("ItemList table populated");
					
					
					insertRooms = conn.prepareStatement("insert into room (description, inventory, obstacle, "
							+ "roomMap, isFound) values (?, ?, ?, ?, ?)");
					
					for (Room room : Rooms) {
						insertRooms.setString(1, room.getDescription());
						insertRooms.setInt(2, InventoryIDbyList(room.getInventory(), InventoryList));
						insertRooms.setInt(3, ObstacleIDbyList(room.getObstacle(), Obstacles));
						insertRooms.setInt(4, room.getRoomId());
						if(room.getIsFound()) {
							insertRooms.setInt(5, 1);
						}else {
							insertRooms.setInt(5, 0);
						}
						insertRooms.addBatch();
					}
					insertRooms.executeBatch();
					
					System.out.println("Rooms table populated");
					
					insertObstacles = conn.prepareStatement("insert into obstacle (description, status, "
							+ "requirement) values (?, ?, ?)");
					for (Obstacle obstacle : Obstacles) {
						insertObstacles.setString(1, obstacle.getDescription());
						insertObstacles.setString(2, obstacle.getStatus());
						insertObstacles.setInt(3, ItemIDbyList(obstacle.getRequirement(), Items));

						insertObstacles.addBatch();
					}
					insertObstacles.executeBatch();
					
					System.out.println("Obstacles table populated");
					
					insertMaps = conn.prepareStatement("insert into roomMap (roomId, direction, mappedRoomId, xCoord, yCoord) values (?, ?, ?, ?, ?)");
					int counter = 0, xCoord = 0, yCoord = 0;
					HashMap<Integer, Pair<Integer, Integer>> coordMap = new HashMap<Integer, Pair<Integer, Integer>>();
					coordMap.put(1, new Pair<Integer, Integer>(xCoord, yCoord));
					for(Room room : Rooms) {
						counter++;
						Set<String> keys = room.getRoomMap().keySet();
						ArrayList<String> strings = new ArrayList<String>();
						
						for(String s : keys) {
							strings.add(s);
						}
						
						for(int i=0; i<=strings.size()-1; i++) {
							insertMaps.setInt(1, counter);
							insertMaps.setString(2, strings.get(i));
							insertMaps.setInt(3, room.getRoomMap().get(strings.get(i)));
							
							xCoord = coordMap.get(room.getRoomId()).getLeft();
							yCoord = coordMap.get(room.getRoomId()).getRight();
							
							if(strings.get(i).equals("north")) {
								yCoord--;
							}else if(strings.get(i).equals("south")) {
								yCoord++;
							}else if(strings.get(i).equals("east")) {
								xCoord++;
							}else if(strings.get(i).equals("west")) {
								xCoord--;
							}
							
							coordMap.put(room.getRoomMap().get(strings.get(i)), new Pair<Integer, Integer>(xCoord, yCoord));
							
							insertMaps.setInt(4, xCoord);
							insertMaps.setInt(5, yCoord);
							
							insertMaps.addBatch();
						}
					
					}
					insertMaps.executeBatch();

					System.out.println("Maps table populated");
					
					insertMessages = conn.prepareStatement("insert into textHistory (message, playerAction) values (?, ?)");
					for (Message<String,Integer> message : Messages) {
						insertMessages.setString(1, message.getMessage());
						insertMessages.setInt(2, message.getPlayerAction());
						insertMessages.addBatch();
					}
					insertMessages.executeBatch();
					
					System.out.println("TextHistory table populated");
					
					insertInventoryList = conn.prepareStatement("insert into inventory (maxstorage, maxquant, inventory) "
							+ " values (?, ?, ?)");
					
					for (Inventory inv : InventoryList) {
						
						insertInventoryList.setInt(1, inv.getMaxStorage());
						insertInventoryList.setInt(2, inv.getMaxQuant());
						insertInventoryList.setInt(3, InventoryIDbyList(inv, InventoryList));
						
						insertInventoryList.addBatch();
					}
					insertInventoryList.executeBatch();
					
					System.out.println("Inventory table populated");
					
					insertDialogue = conn.prepareStatement("insert into Dialogue (npc_id, question, choice1_id, choice2_id, choice3_id) values (?, ?, ?, ?, ?)");
					for (Dialogue dialogue : Dialogues) {
						insertDialogue.setInt(1, dialogue.getNPC_ID());
						insertDialogue.setString(2, dialogue.getQuestion());
						insertDialogue.setInt(3, dialogue.getChoice1());
						insertDialogue.setInt(4, dialogue.getChoice2());
						insertDialogue.setInt(5, dialogue.getChoice3());
						insertDialogue.addBatch();
						
					}
					insertDialogue.executeBatch();
					
					System.out.println("Dialogue table populated");
					
					insertChoices = conn.prepareStatement("insert into Choices (npc_id, choice_id, choice, response, status) values (?, ?, ?, ?, ?)");
					for (Choice choice : Choices) {
						insertChoices.setInt(1, choice.getNPC_ID());
						insertChoices.setInt(2, choice.getChoice_ID());	
						insertChoices.setString(3, choice.getChoice());
						insertChoices.setString(4, choice.getResponse());
						insertChoices.setString(5, choice.getStatus());
						insertChoices.addBatch();
					}
					insertChoices.executeBatch();
					
					System.out.println("Choices table populated");
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertAbilities);
					DBUtil.closeQuietly(insertAbilitiesList);
					DBUtil.closeQuietly(insertInventoryList);	
					DBUtil.closeQuietly(insertItems);
					DBUtil.closeQuietly(insertItemList);
					DBUtil.closeQuietly(insertRooms);
					DBUtil.closeQuietly(insertObstacles);
					DBUtil.closeQuietly(insertMaps);
					DBUtil.closeQuietly(insertEnemies);
					DBUtil.closeQuietly(insertPlayers);
					DBUtil.closeQuietly(insertNPCs);
					DBUtil.closeQuietly(insertDialogue);
					DBUtil.closeQuietly(insertChoices);
					
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Labyrinth DB successfully initialized!");
	}
	
	public static int AbilityIDbyList(ArrayList<Ability> Inner, List<ArrayList<Ability>> Outer) {
		int count=0;
		AbilityComparator ac = new AbilityComparator();
		for(ArrayList<Ability> arr : Outer) {
			count++;
			if (ac.compare(arr, Inner)==1) {
				return count;
			}
		}
		return -1;
	}
	public static int InventoryIDbyList(Inventory Inner, List<Inventory> Outer) {
		int count=0;
		InventoryComparator ic = new InventoryComparator();
		for(Inventory inv : Outer) {
			count++;
			if (ic.compare(inv, Inner) == 1) {
				return count;
			}
		}
		return -1;
	}
	public static int ObstacleIDbyList(Obstacle Inner, List<Obstacle> Outer) {
		int count=0;
		ObstacleComparator oc = new ObstacleComparator();
		for(Obstacle obst : Outer) {
			count++;
			if (oc.compare(obst, Inner) == 1) {
				return count;
			}
		}
		return -1;
	}
	public static int ItemIDbyList(Item Inner, List<Item> Outer) {
		int count=0;
		ItemComparator ic = new ItemComparator();
		for(Item item : Outer) {
			count++;
			if (ic.compare(item, Inner)==1) {
				return count;
			}
		}
		return -1;
	}

	@Override
	public List<Player> findAllPlayers() {
		return executeTransaction(new Transaction<List<Player>>() {
			@Override
			public List<Player> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet4 = null;
				ResultSet resultSet5 = null;
				
				try {
					stmt = conn.prepareStatement(
							"select player.* " +
							"  from  player " 
					);
					resultSet = stmt.executeQuery();
					
					List<Player> result = new ArrayList<Player>();
					
					Boolean found = false;
					while (resultSet.next()) {
						
						found = true;
						
						stmt2 = conn.prepareStatement(
								"select abilityList.* " +
								"  from  abilityList " +
								"  where abilityList.abilityList_id = ?"
						);
						
						stmt2.setInt(1, resultSet.getInt(10));
						
						resultSet2 = stmt2.executeQuery();
						
						ArrayList<Ability> tempAbilList = new ArrayList<Ability>();
						
						resultSet2.next();
						for (int i=2; i<=6; i++) {
							if(!resultSet2.getString(i).equals("filler")) {
								stmt3 = conn.prepareStatement(
										"select ability.* " +
										"  from  ability " +
										"  where ability.name = ?"
								);
							
								
								stmt3.setString(1, resultSet2.getString(i));
								
								resultSet3 = stmt3.executeQuery();
								
								Ability abil = new Ability(null, null, null, null, 0, 0);
								
								resultSet3.next();
								
								loadAbility(abil, resultSet3, 2);
								
								tempAbilList.add(abil);
							}
						}
						
						
						stmt4 = conn.prepareStatement(
								"select room.* " +
								"  from  room " +
								"  where room.room_id = ?"
						);
						
						stmt4.setInt(1, resultSet.getInt(13));
						
						resultSet4 = stmt4.executeQuery();
						
						stmt5 = conn.prepareStatement(
								"select roomMap.* " +
								"  from  roomMap " +
								"  where roomMap.roomId = ?"
						);
						
						resultSet4.next();
						stmt5.setInt(1, resultSet4.getInt(5));
						
						resultSet5 = stmt5.executeQuery();
						
						HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
						while(resultSet5.next()) {
							tmpMap.put(resultSet5.getString(2), resultSet5.getInt(3));
						}
						
						Room room = new Room(null, null, null, null, false, 0);
						Obstacle obs = findObstacle(resultSet4.getInt(4));
						Inventory inv = findInventory(resultSet4.getInt(3));
						loadRoom(room, resultSet4, 1, tmpMap, obs, inv);
					
						Inventory invForPlayer = findInventory(resultSet.getInt(12));
						Player player = new Player(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, false, null);
						loadPlayer(player, resultSet, 2, tempAbilList, room, invForPlayer);
						

						result.add(player);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No players were found!");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(resultSet4);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					
				}
			}
		});
	}
	

	@Override
	public List<Room> findAllRooms() {
		return executeTransaction(new Transaction<List<Room>>() {
			@Override
			public List<Room> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				
				
				try {
					stmt = conn.prepareStatement(
							"select room.* " +
							"  from  room " 
					);
					resultSet = stmt.executeQuery();
					
					List<Room> result = new ArrayList<Room>();
					
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						
						Room room = new Room(null, null, null, null, false, 0);
						
						
						stmt2 = conn.prepareStatement(
								"select roomMap.* " +
								"  from  roomMap " +
								"  where roomMap.roomId = ?"
						);
						
						
						stmt2.setInt(1, resultSet.getInt(5));
						
						resultSet2 = stmt2.executeQuery();
						
						
						
						HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
						while(resultSet2.next()) {
							tmpMap.put(resultSet2.getString(2), resultSet2.getInt(3));
						}
						
											
						Obstacle obs = findObstacle(resultSet.getInt(4));
						Inventory inv = findInventory(resultSet.getInt(3));
						//needs to take in inventory and obstacle
						loadRoom(room, resultSet, 1, tmpMap, obs, inv);
						
						result.add(room);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No rooms were found!");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
					
				}
			}

			
		});
	}
	
	@Override
	public List<Item> findAllItems() {
		return executeTransaction(new Transaction<List<Item>>() {
			@Override
			public List<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select item.* " +
							"  from  item " 
					);
					
					
					List<Item> result = new ArrayList<Item>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Item item = new Item(null, 0, found, found, found, 0, null, null, null);
						loadItem(item, resultSet, 2);
						

						result.add(item);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No items were found!");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	
	@Override
	public List<Enemy> findAllEnemies() {
		return executeTransaction(new Transaction<List<Enemy>>() {
			@Override
			public List<Enemy> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet4 = null;
				ResultSet resultSet5 = null;
				
				try {
					stmt = conn.prepareStatement(
							"select enemy.* " +
							"  from  enemy " 
					);
					resultSet = stmt.executeQuery();
					
					List<Enemy> result = new ArrayList<Enemy>();
					
					Boolean found = false;
					while (resultSet.next()) {
						
						found = true;
						
						stmt2 = conn.prepareStatement(
								"select abilityList.* " +
								"  from  abilityList " +
								"  where abilityList.abilityList_id = ?"
						);
						
						stmt2.setInt(1, resultSet.getInt(10));
						
						resultSet2 = stmt2.executeQuery();
						
						ArrayList<Ability> tempAbilList = new ArrayList<Ability>();
						
						resultSet2.next();
						for (int i=2; i<=6; i++) {
							if(!resultSet2.getString(i).equals("filler")) {
								stmt3 = conn.prepareStatement(
										"select ability.* " +
										"  from  ability " +
										"  where ability.name = ?"
								);
							
								
								stmt3.setString(1, resultSet2.getString(i));
								
								resultSet3 = stmt3.executeQuery();
								
								Ability abil = new Ability(null, null, null, null, 0, 0);
								
								resultSet3.next();
								
								loadAbility(abil, resultSet3, 2);
								
								tempAbilList.add(abil);
							}
						}
						
						
						stmt4 = conn.prepareStatement(
								"select room.* " +
								"  from  room " +
								"  where room.room_id = ?"
						);
						
						stmt4.setInt(1, resultSet.getInt(17));
						
						resultSet4 = stmt4.executeQuery();
						
						stmt5 = conn.prepareStatement(
								"select roomMap.* " +
								"  from  roomMap " +
								"  where roomMap.roomId = ?"
						);
						
						resultSet4.next();
						stmt5.setInt(1, resultSet4.getInt(5));
						
						resultSet5 = stmt5.executeQuery();
						
						HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
						while(resultSet5.next()) {
							tmpMap.put(resultSet5.getString(2), resultSet5.getInt(3));
						}
						
						Room room = new Room(null, null, null, null, false, 0);
						Obstacle obs = findObstacle(resultSet4.getInt(4));
						Inventory inv = findInventory(resultSet4.getInt(3));
						loadRoom(room, resultSet4, 1, tmpMap, obs, inv);
					
						Inventory invForEnemy = findInventory(resultSet.getInt(16));
						Enemy enemy = new Enemy(0, 0, 0, 0, 0, 0, 0, 0, tempAbilList, null, null, 0, null, null, null, room, found);
						loadEnemy(enemy, resultSet, 2, tempAbilList, room, invForEnemy);
						

						result.add(enemy);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No enemies were found!");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(resultSet4);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					
				}
			}
		});
	}

	@Override
	public List<NPC> findAllNPCs() {
		return executeTransaction(new Transaction<List<NPC>>() {
			@Override
			public List<NPC> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet4 = null;
				ResultSet resultSet5 = null;
				
				try {
					stmt = conn.prepareStatement(
							"select NPC.* " +
							"  from  NPC " 
					);
					resultSet = stmt.executeQuery();
					
					List<NPC> result = new ArrayList<NPC>();
					
					Boolean found = false;
					while (resultSet.next()) {
						
						found = true;
						
						stmt2 = conn.prepareStatement(
								"select abilityList.* " +
								"  from  abilityList " +
								"  where abilityList.abilityList_id = ?"
						);
						
						stmt2.setInt(1, resultSet.getInt(10));
						
						resultSet2 = stmt2.executeQuery();
						
						ArrayList<Ability> tempAbilList = new ArrayList<Ability>();
						
						resultSet2.next();
						for (int i=2; i<=6; i++) {
							if(!resultSet2.getString(i).equals("filler")) {
								stmt3 = conn.prepareStatement(
										"select ability.* " +
										"  from  ability " +
										"  where ability.name = ?"
								);
							
								
								stmt3.setString(1, resultSet2.getString(i));
								
								resultSet3 = stmt3.executeQuery();
								
								Ability abil = new Ability(null, null, null, null, 0, 0);
								
								resultSet3.next();
								
								loadAbility(abil, resultSet3, 2);
								
								tempAbilList.add(abil);
							}
						}
						
						
						stmt4 = conn.prepareStatement(
								"select room.* " +
								"  from  room " +
								"  where room.room_id = ?"
						);
						
						stmt4.setInt(1, resultSet.getInt(17));
						
						resultSet4 = stmt4.executeQuery();
						
						stmt5 = conn.prepareStatement(
								"select roomMap.* " +
								"  from  roomMap " +
								"  where roomMap.roomId = ?"
						);
						
						resultSet4.next();
						stmt5.setInt(1, resultSet4.getInt(5));
						
						resultSet5 = stmt5.executeQuery();
						
						HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
						while(resultSet5.next()) {
							tmpMap.put(resultSet5.getString(2), resultSet5.getInt(3));
						}
						
						Room room = new Room(null, null, null, null, false, 0);
						Obstacle obs = findObstacle(resultSet4.getInt(4));
						Inventory inv = findInventory(resultSet4.getInt(3));
						
						loadRoom(room, resultSet4, 1, tmpMap, obs, inv);
					
						Inventory invForNPC = findInventory(resultSet.getInt(16));
						NPC NPC = new NPC(0, 0, 0, 0, 0, 0, 0, 0, tempAbilList, null, null, 0, null, null, null, room, found);
						loadNPC(NPC, resultSet, 2, tempAbilList, room, invForNPC);
						

						result.add(NPC);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No players were found!");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(resultSet4);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					
				}
			}
		});
	}

	@Override
	public List<Message<String, Integer>> findTextHistory() {
		return executeTransaction(new Transaction<List<Message<String, Integer>>>() {
			@Override
			public List<Message<String, Integer>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select textHistory.* " +
							"  from  textHistory " 
					);
					
					
					List<Message<String, Integer>> result = new ArrayList<Message<String, Integer>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Message<String, Integer> message = new Message<String, Integer>(null, 0);
						loadMessage(message, resultSet, 2);
						

						result.add(message);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No messages were found!");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Player updatePlayer(Player newPlayer) {
		return executeTransaction(new Transaction<Player>() {
			@Override
			public Player execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"update player " +
							" set  maxHP = ?,  HP = ?,  maxResource = ?,  resource = ?,  atk = ?,  " +
							"   def = ?,  gold = ?,  xp = ?,   status = ?,   " +
							"  currentRoom = ?,  isDead = ?,  name = ?"
					);
					
					stmt.setInt(1, newPlayer.getMaxHP());
					stmt.setInt(2, newPlayer.getHP());
					stmt.setInt(3, newPlayer.getMaxResource());
					stmt.setInt(4, newPlayer.getResource());
					stmt.setInt(5, newPlayer.getAtk());
					stmt.setInt(6, newPlayer.getDef());
					stmt.setInt(7, newPlayer.getGold());
					stmt.setInt(8, newPlayer.getXP());
					
					
					stmt.setString(9, newPlayer.getStatus());
					
					stmt.setInt(10, newPlayer.getCurrentRoom().getRoomId());
					
					
					if(newPlayer.getIsDead()) {
						stmt.setInt(11, 1);
					}else {
						stmt.setInt(11, 0);
					}
					
					stmt.setString(12, newPlayer.getName());
					
					
					stmt.executeUpdate();
					
					return null;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Obstacle updateObstacle(Obstacle newObstacle, int ObsticleID) {
		return executeTransaction(new Transaction<Obstacle>() {
			@Override
			public Obstacle execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"update obstacle " +
							" set  status = ?" +
							" where obstacle.obstacle_id = ?"
					);
					stmt.setString(1, newObstacle.getStatus());
					stmt.setInt(2, ObsticleID);
					stmt.executeUpdate();
					return null;
					} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<Item> updateItems(List<Item> itemList) {
		return executeTransaction(new Transaction<List<Item>>() {
			@Override
			public List<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					int count=1;
					for(Item item : itemList) {
						stmt = conn.prepareStatement(
								"update item " +
								" set  description = ?,  effect = ?,  flammable = ?,  lit = ?,  throwable = ?,  " +
								"   value = ?,  name = ?,  variety = ?,   affectedStat = ? " +
								" where item.item = ?"

						);
						
						stmt.setString(1, item.getDescription());
						stmt.setInt(2, item.getEffect());
						if(item.getFlammable()) {
							stmt.setInt(3, 1);
						}else {
							stmt.setInt(3, 0);
						}
						if(item.getLit()) {
							stmt.setInt(4, 1);
						}else {
							stmt.setInt(4, 0);
						}
						if(item.getThrowable()) {
							stmt.setInt(5, 1);
						}else {
							stmt.setInt(5, 0);
						}
						stmt.setInt(6, item.getValue());
						stmt.setString(7, item.getName());
						stmt.setString(8, item.getVariety());
						stmt.setString(9, item.getAffectedStat());
						stmt.setInt(10, count++);

						stmt.executeUpdate();
					}
					return null;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<Enemy> updateEnemies(List<Enemy> enemyList) {
		return executeTransaction(new Transaction<List<Enemy>>() {
			@Override
			public List<Enemy> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					int count=1;
					for(Enemy enemy : enemyList) {
						stmt = conn.prepareStatement(
								"update enemy " +
								" set  maxHP = ?,  HP = ?,  maxResource = ?,  resource = ?,  atk = ?,  " +
								"   def = ?,  gold = ?,  xp = ?,   status = ?, dialogue = ?, attitude = ?,   " +
								" description = ?, currentRoom = ?,  isDead = ?,  name = ? " +
								" where enemy_id = ?"
						);
						
						stmt.setInt(1, enemy.getMaxHP());
						stmt.setInt(2, enemy.getHP());
						stmt.setInt(3, enemy.getMaxResource());
						stmt.setInt(4, enemy.getResource());
						stmt.setInt(5, enemy.getAtk());
						stmt.setInt(6, enemy.getDef());
						stmt.setInt(7, enemy.getGold());
						stmt.setInt(8, enemy.getXP());
						stmt.setString(9, enemy.getStatus());
						stmt.setString(10, enemy.getDialogue());
						stmt.setInt(11, enemy.getAttitude());
						stmt.setString(12, enemy.getDescription());
						stmt.setInt(13, enemy.getCurrentRoom().getRoomId());
						if(enemy.getIsDead()) {
							stmt.setInt(14, 1);
						}else {
							stmt.setInt(14, 0);
						}
						stmt.setString(15, enemy.getName());
						stmt.setInt(16, count++);
						stmt.executeUpdate();
					}
					return null;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<Room> updateRooms(List<Room> roomList) {
		return executeTransaction(new Transaction<List<Room>>() {
			@Override
			public List<Room> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;

				ResultSet resultSet = null;
				
				try {
					for(Room room : roomList) {
						stmt = conn.prepareStatement(
								"update room " +
								" set  description = ?, isFound = ?  " +
								" where room_id = ?"
						);
						stmt.setString(1, room.getDescription());
						if(room.getIsFound()) {
							stmt.setInt(2, 1);
						}else {
							stmt.setInt(2, 0);
						}
						stmt.setInt(3, room.getRoomId());
						//have to update the obstacle
						stmt2 = conn.prepareStatement(
								"select obstacle.obstacle_id " +
								"  from  obstacle" +
								"  where obstacle.description = ?"
						);
						stmt2.setString(1, room.getObstacle().getDescription());
						resultSet = stmt2.executeQuery();
						resultSet.next();
						updateObstacle(room.getObstacle(), resultSet.getInt(1));
						//have to update the inventory
						//not yet completed
						stmt.executeUpdate();
					}
					return null;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<NPC> updateNPCs(List<NPC> npcList) {
		return executeTransaction(new Transaction<List<NPC>>() {
			@Override
			public List<NPC> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						int count=1;
						for(NPC NPC : npcList) {
							stmt = conn.prepareStatement(
									"update npc " +
									" set  maxHP = ?,  HP = ?,  maxResource = ?,  resource = ?,  atk = ?,  " +
									"   def = ?,  gold = ?,  xp = ?,   status = ?, dialogue = ?, attitude = ?,   " +
									" description = ?, currentRoom = ?,  isDead = ?,  name = ? " +
									" where NPC_id = ?"
							);
							
							stmt.setInt(1, NPC.getMaxHP());
							stmt.setInt(2, NPC.getHP());
							stmt.setInt(3, NPC.getMaxResource());
							stmt.setInt(4, NPC.getResource());
							stmt.setInt(5, NPC.getAtk());
							stmt.setInt(6, NPC.getDef());
							stmt.setInt(7, NPC.getGold());
							stmt.setInt(8, NPC.getXP());
							stmt.setString(9, NPC.getStatus());
							stmt.setString(10, NPC.getDialogue());
							stmt.setInt(11, NPC.getAttitude());
							stmt.setString(12, NPC.getDescription());
							stmt.setInt(13, NPC.getCurrentRoom().getRoomId());
							if(NPC.getIsDead()) {
								stmt.setInt(14, 1);
							}else {
								stmt.setInt(14, 0);
							}
							stmt.setString(15, NPC.getName());
							stmt.setInt(16, count++);
							stmt.executeUpdate();
						}
						return null;
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
	}

	@Override
	public Obstacle findObstacle(int ObstacleId) {
		return executeTransaction(new Transaction<Obstacle>() {
			@Override
			public Obstacle execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				
				try {
					
					
					
					stmt = conn.prepareStatement(
							"select obstacle.* " +
							"  from  obstacle " +
							"  where obstacle.obstacle_id = ?"
					);
					
					stmt.setInt(1, ObstacleId);
					
					resultSet = stmt.executeQuery();
					resultSet.next();
					
					stmt2 = conn.prepareStatement(
							"select item.* " +
							"  from  item " +
							"  where item.item = ?"
					);
					
					stmt2.setInt(1, resultSet.getInt(4));
					
					resultSet2 = stmt2.executeQuery();
					resultSet2.next();
					
					Item item = new Item(null, 0, false, false, false, 0, null, null, null);
					loadItem(item, resultSet2, 2);
					
					
					Obstacle obs = new Obstacle(null, null, null);
					loadObstacle(obs, resultSet, item, 2);
						
					
					return obs;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
				}
			}

			
		});
	}

	@Override
	public Inventory findInventory(int InventoryId) {
		return executeTransaction(new Transaction<Inventory>() {
			@Override
			public Inventory execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				
				try {
					
					
					
					stmt = conn.prepareStatement(
							"select inventory.* " +
							"  from  inventory " +
							"  where inventory.inventory_id = ?"
					);
					
					stmt.setInt(1, InventoryId);
					
					resultSet = stmt.executeQuery();
					resultSet.next();
					
					
					

					ArrayList<Item> itemList = (ArrayList<Item>) findItemList(resultSet.getInt(4));
					
					
					Inventory inv = new Inventory(0, 0, null);
					loadInventory(inv, resultSet, 2, itemList);
						
					
					return inv;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
				}
			}

			
		});
	}

	@Override
	public List<Item> findItemList(int itemListId) {
		return executeTransaction(new Transaction<List<Item>>() {
			@Override
			public List<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				
				try {
					ArrayList<Item> itemList = new ArrayList<Item>();
					
					
					stmt = conn.prepareStatement(
							"select itemList.* " +
							"  from  itemList " +
							"  where itemList.itemList_id = ?"
					);
					
					stmt.setInt(1, itemListId);
					
					resultSet = stmt.executeQuery();
					
					resultSet.next();
					
	
					for(int i=2; i<=51; i++) {
						
						if(!resultSet.getString(i).contentEquals("filler")) {
							
							stmt2 = conn.prepareStatement(
									"select item.* " +
									"  from  item " +
									"  where item.name = ?"
							);
							
							stmt2.setString(1, resultSet.getString(i));
							
							resultSet2 = stmt2.executeQuery();
							resultSet2.next();
							

							Item item = new Item(null, 0, null, null, null, 0, null, null, null);
							loadItem(item, resultSet2, 2);
							
							itemList.add(item);
							
						}
						
						
					}
					
					
					return itemList;
					
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
				}
			}

			
		});
	}

	@Override
	public List<Message<String, Integer>> insertIntoTextHistory(Message<String, Integer> newMessage) {
		return executeTransaction(new Transaction<List<Message<String,Integer>>>() {
			@Override
			public List<Message<String, Integer>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				
				
				try {
					stmt = conn.prepareStatement(
							"insert into textHistory (message, playerAction) values (?, ?)");
										
					stmt.setString(1, newMessage.getMessage());
					stmt.setInt(2, newMessage.getPlayerAction());
					stmt.executeUpdate();

					
					return null;
				} finally {
					
					DBUtil.closeQuietly(stmt);
					
				}
			}
		});
	}

	@Override
	public List<Message<String, Integer>> updateTextHistory() {
		return executeTransaction(new Transaction<List<Message<String,Integer>>>() {
			@Override
			public List<Message<String, Integer>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				PreparedStatement stmt3 = null;
				
				
				try {
					
					stmt = conn.prepareStatement(
							"select count(*) from textHistory");
										
					
					resultSet = stmt.executeQuery();
					resultSet.next();
					int size = resultSet.getInt(1);
					
					while(size>30) {
						
						
						
						stmt2 = conn.prepareStatement(
								"select textHistory.textHistory_id from textHistory ");
						
						resultSet2 = stmt2.executeQuery();
						
						stmt3 = conn.prepareStatement(
								"delete from textHistory where textHistory_id = ? ");
						
						resultSet2.next();
						
						stmt3.setInt(1, resultSet2.getInt(1));
						
						stmt3.executeUpdate();
					
					
						resultSet = stmt.executeQuery();
						resultSet.next();
						size = resultSet.getInt(1);
						
						
					}
					
					return null;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
					
					DBUtil.closeQuietly(stmt3);
					
				}
			}
		});
	}

	@Override
	public Room findRoom(int roomId) {
		return executeTransaction(new Transaction<Room>() {
			@Override
			public Room execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select room.* " +
							"  from  room " +
							"  where room.room_id = ?"
					);
					
					stmt.setInt(1, roomId);
					
					resultSet = stmt.executeQuery();
					
					resultSet.next();
					
					Room newRoom = new Room(null, null, null, null, false, 0);
					
					HashMap<String, Integer> newMap = new HashMap<String, Integer>();
					
					newMap = findMap(roomId);
					
					Obstacle obs = new Obstacle(null, null, null);
					obs = findObstacle(resultSet.getInt(4));
					
					Inventory inv = new Inventory(0, 0, null);
					inv = findInventory(resultSet.getInt(3));
					
					loadRoom(newRoom, resultSet, 1, newMap, obs, inv);
					
					
					
					return newRoom;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					
					
				}
			}
		});
	}

	@Override
	public Pair<Integer, Integer> findCoordinates(int roomId) {
		return executeTransaction(new Transaction<Pair<Integer, Integer>>() {
			@Override
			public Pair<Integer, Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				PreparedStatement stmt3 = null;
				ResultSet resultSet3 = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select min(xCoord) from roomMap");
										
					resultSet = stmt.executeQuery();
					resultSet.next();
					
					stmt2 = conn.prepareStatement(
							"select min(yCoord) from roomMap");
					resultSet2 = stmt2.executeQuery();
					resultSet2.next();
					
					stmt3 = conn.prepareStatement(
							"select xCoord, yCoord from roomMap where mappedRoomid = ?");
						
					stmt3.setInt(1,  roomId);
					
					resultSet3 = stmt3.executeQuery();
					resultSet3.next();
					
					Pair<Integer, Integer> coordPair = new Pair<Integer, Integer>(resultSet3.getInt(1) + Math.abs(resultSet.getInt(1)), resultSet3.getInt(2) + Math.abs(resultSet2.getInt(1)));
					
					
					return coordPair;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);
					
				}
			}
		});
	}

	@Override
	public HashMap<String, Integer> findMap(int roomId) {
		
		return executeTransaction(new Transaction<HashMap<String, Integer>>() {
			@Override
			public HashMap<String, Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				
				
				try {
					
					stmt = conn.prepareStatement(
							"select roomMap.* " +
							"  from  roomMap " +
							"  where roomMap.roomId = ?"
					);
					
					
					stmt.setInt(1, roomId);
					
					resultSet = stmt.executeQuery();
					
					HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
					while(resultSet.next()) {
						tmpMap.put(resultSet.getString(2), resultSet.getInt(3));
					}
					
					return tmpMap;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
			
				}
			}
		});
		
		
	}
	
	@Override
	public Pair<Integer, Integer> findMapArraySize() {
		return executeTransaction(new Transaction<Pair<Integer, Integer>>() {
			@Override
			public Pair<Integer, Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				PreparedStatement stmt3 = null;
				ResultSet resultSet3 = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select min(xCoord) from roomMap");
										
					resultSet = stmt.executeQuery();
					resultSet.next();
					
					stmt2 = conn.prepareStatement(
							"select min(yCoord) from roomMap");
					resultSet2 = stmt2.executeQuery();
					resultSet2.next();
					
					stmt3 = conn.prepareStatement(
							"select max(xCoord), max(yCoord) from roomMap");
						
					resultSet3 = stmt3.executeQuery();
					resultSet3.next();
					
					Pair<Integer, Integer> coordPair = new Pair<Integer, Integer>(resultSet3.getInt(1) + Math.abs(resultSet.getInt(1))+1, resultSet3.getInt(2) + Math.abs(resultSet2.getInt(1))+1);
					
					
					return coordPair;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);
					
				}
			}
		});
	}
	
	@Override
	public String removeTextHistoryByMessage(String message) {
		return executeTransaction(new Transaction<String>() {
			@Override
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				
				
				
				try {
					
					stmt = conn.prepareStatement(
							"delete from textHistory " +
									"  where message = ? "
					);
					
					
					stmt.setString(1, message);
					
					stmt.executeUpdate();
					
					String removed = message + " has been removed";
					
					return removed;
				} finally {
					DBUtil.closeQuietly(stmt);
					
			
				}
			}
		});
	}
	
	@Override
	public String removeTextHistoryByID(int ID) {
		return executeTransaction(new Transaction<String>() {
			@Override
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				
				
				
				try {
					
					stmt = conn.prepareStatement(
							"delete from textHistory " +
									"  where texthistory_id = ? "
					);
					
					
					stmt.setInt(1, ID);
					
					stmt.executeUpdate();
					
					String removed = "removed";
					
					return removed;
				} finally {
					DBUtil.closeQuietly(stmt);
					
			
				}
			}
		});
	}

	@Override
	public List<Message<String, Integer>> findChoicesForNPC(String npcName) {
		return executeTransaction(new Transaction<List<Message<String, Integer>>>() {
			@Override
			public List<Message<String, Integer>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				PreparedStatement stmt3 = null;
				ResultSet resultSet3 = null;
				List<Message<String, Integer>> list = new ArrayList<Message<String, Integer>>();
				
				try {
					
					stmt = conn.prepareStatement(
							"select npc_id from npc where npc.name = ?");
								
					stmt.setString(1, npcName);
					
					resultSet = stmt.executeQuery();
					resultSet.next();
					
					
					stmt2 = conn.prepareStatement(
							"select question from dialogue where npc_id = ?");
					
					stmt2.setInt(1,  resultSet.getInt(1));
					
					resultSet2 = stmt2.executeQuery();
					resultSet2.next();
					
					Message<String, Integer> msg = new Message<String, Integer>(resultSet2.getString(1), 0);
					
					insertIntoTextHistory(msg);
					list.add(msg);
					
					stmt3 = conn.prepareStatement(
							"select choice from choices where npc_id = ?");
					stmt3.setInt(1,  resultSet.getInt(1));
					
					resultSet3 = stmt3.executeQuery();
					
					int count = 1;
					while(resultSet3.next()) {
						String string = resultSet3.getString(1);
						insertIntoTextHistory(new Message<String, Integer>("Option " + count + ". " + string, 1));
						list.add(new Message<String, Integer>(string, 1));
						count++;
					}
					
					return list;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);
					
					
				}
			}
		});
	}

	@Override
	public String findResponse(int roomID, int playerChoice) {
		return executeTransaction(new Transaction<String>() {
			@Override
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				try {
					stmt = conn.prepareStatement(
							"select npc_id from npc where npc.currentRoom = ?");
								
					stmt.setInt(1, roomID);
					
					resultSet = stmt.executeQuery();
					resultSet.next();
					
					stmt2 = conn.prepareStatement(
							"select choice, response, status from choices where npc_id = ? and choice_id = ?");
					
					stmt2.setInt(1, resultSet.getInt(1));
					stmt2.setInt(2, playerChoice);
					
					resultSet2 = stmt2.executeQuery();
					resultSet2.next();
					
					

					insertIntoTextHistory(new Message<String, Integer>(resultSet2.getString(1), 1));
					insertIntoTextHistory(new Message<String, Integer>(resultSet2.getString(2), 0));

					
					
					return resultSet2.getString(3);
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
					
					
				}
			}
		});
	}
	
	@Override
	public Integer findTextHistoryCount() {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Integer count;
				
				
				try {
					
					stmt = conn.prepareStatement(
							"select count(*) from textHistory");
										
					
					resultSet = stmt.executeQuery();
					resultSet.next();
					count = resultSet.getInt(1);
					
					return count;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			}
		});
	}
	
	@Override
	public Integer findTextHistoryIDbyString(String message) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Integer ID;
				
				
				try {
					
					stmt = conn.prepareStatement(
							"select texthistory_id from textHistory where message = ?");
										
					stmt.setString(1, message);
					resultSet = stmt.executeQuery();
					resultSet.next();
					ID = resultSet.getInt(1);
					
					return ID;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			}
		});
	}
	
	@Override
	public Integer findTextHistoryMax() {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Integer max;
				
				
				try {
					
					stmt = conn.prepareStatement(
							"select max(texthistory_id) from textHistory");
										
					
					resultSet = stmt.executeQuery();
					resultSet.next();
					max = resultSet.getInt(1);
					
					return max;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			}
		});
	}
	
}
