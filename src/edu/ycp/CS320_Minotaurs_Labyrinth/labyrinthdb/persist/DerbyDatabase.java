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

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.BookAuthor;
import edu.ycp.cs320.booksdb.model.Pair;

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
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/CS320-2019-LibraryExample-DB/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// retrieves Author information from query result set
	private void loadAuthor(Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	
	// retrieves Book information from query result set
	private void loadBook(Book book, ResultSet resultSet, int index) throws SQLException {
		book.setBookId(resultSet.getInt(index++));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		book.setTitle(resultSet.getString(index++));
		book.setIsbn(resultSet.getString(index++));
		book.setPublished(resultSet.getInt(index++));
	}
	
	// retrieves WrittenBy information from query result set
	private void loadBookAuthors(BookAuthor bookAuthor, ResultSet resultSet, int index) throws SQLException {
		bookAuthor.setBookId(resultSet.getInt(index++));
		bookAuthor.setAuthorId(resultSet.getInt(index++));
	}
	
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
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
							"	ability1 integer," +
							"	ability2 integer," +
							"	ability3 integer," +
							"	ability4 integer," +
							"	ability5 integer," +
							")"
					);
					stmt2.executeUpdate();
					
					System.out.println("abilityList table created");					
					
					stmt3 = conn.prepareStatement(
							"create table account (" +
							"	account_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	username varchar(40)," +
							"	password varchar(40)" +
							")"
					);
					stmt3.executeUpdate();
					
					System.out.println("account table created");
					
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
							"	item1 integer," +
							"	item2 integer," +
							"	item3 integer," +
							"	item4 integer," +
							"	item5 integer," +
							"	item6 integer," +
							"	item7 integer," +
							"	item8 integer," +
							"	item9 integer," +
							"	item10 integer," +
							"	item11 integer," +
							"	item12 integer," +
							"	item13 integer," +
							"	item14 integer," +
							"	item15 integer," +
							"	item16 integer," +
							"	item17 integer," +
							"	item18 integer," +
							"	item19 integer," +
							"	item20 integer," +
							"	item21 integer," +
							"	item22 integer," +
							"	item23 integer," +
							"	item24 integer," +
							"	item25 integer," +
							"	item26 integer," +
							"	item27 integer," +
							"	item28 integer," +
							"	item29 integer," +
							"	item30 integer," +
							"	item31 integer," +
							"	item32 integer," +
							"	item33 integer," +
							"	item34 integer," +
							"	item35 integer," +
							"	item36 integer," +
							"	item37 integer," +
							"	item38 integer," +
							"	item39 integer," +
							"	item40 integer," +
							"	item41 integer," +
							"	item42 integer," +
							"	item43 integer," +
							"	item44 integer," +
							"	item45 integer," +
							"	item46 integer," +
							"	item47 integer," +
							"	item48 integer," +
							"	item49 integer," +
							"	item50 integer," +
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
							"	isDead integer," +
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
							"	roomMap integer," +
							"	isFound integer," +
							"	roomMap integer" +
							")"
					);
					stmt12.executeUpdate();
					
					System.out.println("room table created");
					
					stmt13 = conn.prepareStatement(
							"create table roomMap (" +
							"	roomId integer," +
							"	direction varchar(40)," +
							"	mappedRoomId integer" +
							")"
					);
					stmt13.executeUpdate();
					
					System.out.println("roomMap table created");
					
					stmt14 = conn.prepareStatement(
							"create table textHistory (" +
							"	message varchar(7999)," +
							"	playerAction integer" +
							")"
					);
					stmt14.executeUpdate();
					
					System.out.println("textHistory table created");
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
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
				List<HashMap<String, Room>> Maps;
				List<Enemy> Enemies;
				List<Player> Players;
				List<NPC> NPCs;
				
				try {
					
					Abilities      = InitialData.getAbilities();
					AbilitiesList  = InitialData.getAbilitiesList();
					InventoryList  = InitialData.getInventory();
					Items          = InitialData.getItems();
					ItemList       = InitialData.getItemList();
					Rooms          = InitialData.getRooms();
					Obstacles      = InitialData.getObstacles();
					Maps           = InitialData.getMaps();
					Enemies        = InitialData.getEnemies();
					Players        = InitialData.getPlayers();
					NPCs           = InitialData.getNPCs();
					
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

				try {
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					insertAbilities = conn.prepareStatement("insert into ability (name, description, variety, affectedstat, effect, cost) values (?, ?, ?, ?, ?, ?)");
					for (Ability ability : Abilities) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
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
					
					// must completely populate Books table before populating BookAuthors table because of primary keys
					insertAbilitiesList = conn.prepareStatement("insert into abilitieslist (ability1, ability2, ability3, ability4, ability5) values (?, ?, ?, ?, ?)");
					for (ArrayList<Ability> ability : AbilitiesList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
//						insertBook.setInt(1, book.getAuthorId());	// this is now in the BookAuthors table
						for(int i=0; i<=5; i++) {
						insertAbilitiesList.setString(i, ability.get(i).getName());
						}
					}
					insertAbilitiesList.executeBatch();
					
					System.out.println("AbilitiesList table populated");					
					
					// must wait until all Books and all Authors are inserted into tables before creating BookAuthor table
					// since this table consists entirely of foreign keys, with constraints applied
					insertPlayers = conn.prepareStatement("insert into players (maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, inventory, currentRoom, isDead, name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Player player : Players) {
						insertPlayers.setInt(1, player.getMaxHP());
						insertPlayers.setInt(2, player.getHP());
						insertPlayers.setInt(3, player.getMaxResource());
						insertPlayers.setInt(4, player.getResource());
						insertPlayers.setInt(5, player.getAtk());
						insertPlayers.setInt(6, player.getDef());
						insertPlayers.setInt(7, player.getGold());
						insertPlayers.setInt(8, player.getXP());
						insertPlayers.setInt(9, player.getAbilities());
						insertPlayers.setString(10, player.getStatus());
						insertPlayers.setString(11, player.getInventory());
						insertPlayers.setString(12, player.getCurrentRoom());
						insertPlayers.setBoolean(13, player.getIsDead());
						insertPlayers.setString(14, player.getName());
					}
					insertPlayers.executeBatch();	
					
					System.out.println("Players table populated");	
					
					insertEnemies = conn.prepareStatement("insert into players (maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, inventory, currentRoom, isDead, name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Enemy enemy : Enemies) {
						insertEnemies.setInt(1, enemy.getMaxHP());
						insertEnemies.setInt(2, enemy.getHP());
						insertEnemies.setInt(3, enemy.getMaxResource());
						insertEnemies.setInt(4, enemy.getResource());
						insertEnemies.setInt(5, enemy.getAtk());
						insertEnemies.setInt(6, enemy.getDef());
						insertEnemies.setInt(7, enemy.getGold());
						insertEnemies.setInt(8, enemy.getXP());
						insertEnemies.setInt(9, enemy.getAbilities());
						insertEnemies.setString(10, enemy.getStatus());
						insertEnemies.setString(11, enemy.getInventory());
						insertEnemies.setString(12, enemy.getCurrentRoom());
						insertEnemies.setBoolean(13, enemy.getIsDead());
						insertEnemies.setString(14, enemy.getName());
					}
					insertEnemies.executeBatch();	
					
					System.out.println("Enemies table populated");		
					
					insertNPCs = conn.prepareStatement("insert into players (maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, inventory, currentRoom, isDead, name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (NPC npc : NPCs) {
						insertNPCs.setInt(1, npc.getMaxHP());
						insertNPCs.setInt(2, npc.getHP());
						insertNPCs.setInt(3, npc.getMaxResource());
						insertNPCs.setInt(4, npc.getResource());
						insertNPCs.setInt(5, npc.getAtk());
						insertNPCs.setInt(6, npc.getDef());
						insertNPCs.setInt(7, npc.getGold());
						insertNPCs.setInt(8, npc.getXP());
						insertNPCs.setInt(9, npc.getAbilities());
						insertNPCs.setString(10, npc.getStatus());
						insertNPCs.setString(11, npc.getInventory());
						insertNPCs.setString(12, npc.getCurrentRoom());
						insertNPCs.setBoolean(13, npc.getIsDead());
						insertNPCs.setString(14, npc.getName());
					}
					insertEnemies.executeBatch();	
					
					System.out.println("NPC table populated");		
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertBook);
					DBUtil.closeQuietly(insertAuthor);
					DBUtil.closeQuietly(insertBookAuthor);					
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
		
		System.out.println("Library DB successfully initialized!");
	}
}
