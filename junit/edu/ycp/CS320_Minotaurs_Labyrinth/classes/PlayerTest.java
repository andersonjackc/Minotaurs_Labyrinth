package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	
	//Set up a test Player obj
	
	Player testPlayer;
	ArrayList<Item> Inv = new ArrayList<Item>();
	Inventory testRoomInv = new Inventory(100, 100, Inv);
	ArrayList<Item> Inv2 = new ArrayList<Item>();
	Inventory testRoomInv2 = new Inventory(100, 100, Inv2);
	Ability testMaxHPSpell = new Ability("test", "test ability", "test", "maxHP", 5, 5);
	Ability testHPSpell = new Ability("test", "test ability", "test", "HP", 5, 5);
	Ability testMaxResourceSpell = new Ability("test", "test ability", "test", "maxResource", 5, 5);
	Ability testResourceSpell = new Ability("test", "test ability", "test", "resource", 5, 5);
	Ability testAtkSpell = new Ability("test", "test ability", "test", "atk", 5, 5);
	Ability testDefSpell = new Ability("test", "test ability", "test", "def", 5, 5);
	Ability testGodmodeSpell = new Ability("godmode", "test ability", "godmode", "godmode", 5, 5);
	ArrayList<Ability> abilities = new ArrayList<Ability>();
	ArrayList<Ability> abilities2 = new ArrayList<Ability>();
	HashMap<String, Integer> testRoomMap1;
	HashMap<String, Integer> testRoomMap2;
	HashMap<String, Integer> testRoomMap3;
	HashMap<String, Integer> testRoomMap4;
	ArrayList<Room> allRooms;
	Item key = new Item("test", 1, false, false, true, 10, "key", "misc", "none");
	Item rope = new Item("rope", 0, true, false, true, 50, "rope", "misc", "none");
	Obstacle obs = new Obstacle("test", "normal", key, "empty");
	Obstacle obs1 = new Obstacle("test", "normal", rope, "hole");
	Obstacle obs2 = new Obstacle("test", "crawling", key, "door");
	Room room, room2, room3, room4;
	
	
	Inventory testInv = new Inventory(100, 100, Inv);
	

	
	Enemy testEnemy = new Enemy(0, 0, 0, 0, 0, 0, 0, 0, abilities, "test", "testDialogue", 0, "a test", "testEnemy", testInv, room, false);
	NPC testNPC = new NPC(1000, 100, 200, 50, 10, 5, 0, 0, null, null, "testDialogue", 100, "A test NPC", "test", null, room, false);
	Item testItem = new Item("A test item", 5, true, false, true, 50, "testItem", "test", "test");

	ArrayList<Item> Inventory = new ArrayList<Item>();
	Inventory testInventory = new Inventory(100, 100, Inventory);
	Item testPotion = new Item("test", 10, false, false, true, 5, "health potion", "potion", "HP");
	Item testTorch = new Item("test", 0, true, false, true, 1, "torch", "harmmisc", "none");
	Gear testSword = new Gear(5, 0 , 0, "sword", false, "test", 0, false, false, false, 5, "sword", "righthand");
	ArrayList<Item> allItems = new ArrayList<Item>();

	@Before
	public void setUp() {
		Inv2.add(testTorch);
		testRoomMap1 = new HashMap<String, Integer>();
		testRoomMap2 = new HashMap<String, Integer>();
		testRoomMap3 = new HashMap<String, Integer>();
		testRoomMap4 = new HashMap<String, Integer>();
		room = new Room("A test room", testRoomInv, obs, testRoomMap1,  false, 1);
		room2 = new Room("A test room", testRoomInv, obs, testRoomMap2,  false, 2);
		room3 = new Room("A test room", testRoomInv2, obs1, testRoomMap3,  false, 3);
		room4 = new Room("A test room", testRoomInv, obs2, testRoomMap4, false, 4);
		testRoomMap1.put("north", room2.getRoomId());
		testRoomMap1.put("east", room3.getRoomId());
		testRoomMap1.put("west", room4.getRoomId());
		testRoomMap2.put("south", room.getRoomId());
		abilities.add(testMaxHPSpell);
		abilities.add(testHPSpell);
		abilities.add(testMaxResourceSpell);
		abilities.add(testResourceSpell);
		abilities.add(testAtkSpell);
		abilities.add(testDefSpell);
		abilities.add(testGodmodeSpell);
		testPlayer = new Player(1000, 100, 200, 50, 10, 5, 2, 3, abilities, "normal", testInv, room, false, "test");
		allRooms = new ArrayList<Room>();
		allRooms.add(room);
		allRooms.add(room2);
		allRooms.add(room3);
		allRooms.add(room4);
		
		allItems.add(key);
		allItems.add(rope);
		allItems.add(testItem);
		allItems.add(testPotion);
		allItems.add(testSword);
		
	}
	
	
	
	@Test
	public void testBarter() {
		testNPC.setAttitude(100);
		assertEquals(testPlayer.barter(testNPC, testItem), 15);
		testNPC.setAttitude(80);
		assertEquals(testPlayer.barter(testNPC, testItem), 10);
		testNPC.setAttitude(60);
		assertEquals(testPlayer.barter(testNPC, testItem), 5);
		testNPC.setAttitude(40);
		assertEquals(testPlayer.barter(testNPC, testItem), 0);
	}
	
	@Test
	public void testLight() {
		testRoomInv.addItem(testTorch);
		testPlayer.take(testTorch, testRoomInv);
		testPlayer.light(testTorch, testPlayer.getInventory().getInventory());
		assertTrue(testTorch.getLit());
		String tmp = testPlayer.light(testTorch, testPlayer.getInventory().getInventory());
		assertEquals(testTorch.getName() + " is already on fire!", tmp);
		testPlayer.getInventory().addItem(key);
		tmp = testPlayer.light(key, testPlayer.getInventory().getInventory());
		assertEquals("You can't light " + key.getName() + " on fire!", tmp);
	}
	
	@Test
	public void testEquip() {
		testRoomInv.addItem(testSword);
		int oldatk = testPlayer.getAtk();
		testPlayer.take(testSword, testRoomInv);
		testPlayer.equip(testSword, testPlayer.getInventory().getInventory());
		assertEquals(oldatk + testSword.getAtk(), testPlayer.getAtk());
		String tmp = testPlayer.equip(testSword, testPlayer.getInventory().getInventory());
		assertEquals(testSword.getName() + " is already equipped.", tmp);
		Gear testSword2 = new Gear(5, 0 , 0, "sword2", false, "test2", 0, false, false, false, 5, "sword2", "righthand");
		testPlayer.getInventory().addItem(testSword2);
		tmp = testPlayer.equip(testSword2, testPlayer.getInventory().getInventory());
		assertEquals("You already have something equipped in that slot!", tmp);
		Gear test = new Gear(5, 0 , 0, "test", false, "test", 0, false, false, false, 5, "test", "test");
		tmp = testPlayer.equip(test, testPlayer.getInventory().getInventory());
		assertEquals("You can't equip " + test.getName() + ".", tmp);
		
		
	}
	
	@Test
	public void testUnequip() {
		testRoomInv.addItem(testSword);
		testPlayer.take(testSword, testRoomInv);
		testPlayer.equip(testSword, testPlayer.getInventory().getInventory());
		int oldatk = testPlayer.getAtk();
		testPlayer.unequip(testSword, testPlayer.getInventory().getInventory());
		assertEquals(oldatk - testSword.getAtk(), testPlayer.getAtk());
		String tmp = testPlayer.unequip(testSword, testPlayer.getInventory().getInventory());
		assertEquals("You can't unequip " + testSword.getName() + ".", tmp);
	}
	
	@Test
	public void testUse() {
		testRoomInv.addItem(testPotion);
		testPlayer.take(testPotion, testRoomInv);
		testPlayer.use(testPotion, testPlayer);
		assertEquals(110, testPlayer.getHP());
	}
	
	@Test
	public void testTake() {
		
		testPlayer.take(testTorch, testRoomInv2);
		assertTrue(testPlayer.getInventory().getInventory().contains(testTorch));
		assertEquals(testRoomInv2.getInventory().size(), 0);
	}
	
	@Test
	public void testDrop() {
		testRoomInv.addItem(testItem);
		testPlayer.take(testItem, testRoomInv);
		testPlayer.drop(testItem, testPlayer.getCurrentRoom());
		assertTrue(room.getInventory().getInventory().contains(testItem));
	}
	
	
	@Test
	public void testThroObs() {
		testRoomInv.addItem(rope);
		testPlayer.take(rope, testRoomInv);
		testPlayer.throObs(obs1, rope, testPlayer.getCurrentRoom());
		assertEquals("normal", obs1.getStatus());
		testPlayer.getInventory().addItem(rope);
		testPlayer.throObs(obs2, rope, testPlayer.getCurrentRoom());
		assertEquals("crawling", obs2.getStatus());
		testPlayer.getInventory().addItem(testSword);
		assertEquals("You can't throw " + testSword.getName(), testPlayer.throObs(obs2, testSword, testPlayer.getCurrentRoom()));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("north", 1);
		Room testRoom = new Room("test", testInv, obs, map, false, 10);
		testPlayer.setCurrentRoom(testRoom);
		assertEquals("There are no obstacles attached to this room.", testPlayer.throObs(obs, testPotion, testRoom));
	}
	@Test
	public void testMove() {
		String tmp = testPlayer.move("north", allRooms);
		assertEquals(testPlayer.getCurrentRoom(), room2);
		assertEquals(testPlayer.getCurrentRoom().getDescription(), tmp);
		testPlayer.move("south", allRooms);
		tmp = testPlayer.move("south", allRooms);
		assertEquals("You can't move in that direction!", tmp);
		testRoomInv.addItem(key);
		testPlayer.take(key, testRoomInv);
		tmp = testPlayer.move("east", allRooms);
		assertEquals(testPlayer.getCurrentRoom().getDescription() + " " + "There is a torch in the room.", tmp);
		testPlayer.setIsDead(true);
		tmp = testPlayer.move("north", allRooms);
		assertEquals("You are dead!", tmp);
	}
	@Test
	public void testCrawl() {
		testPlayer.crawl("west", allRooms);
		assertEquals(testPlayer.getCurrentRoom(), room4);
	}
	
	@Test
	public void testJump() {
		testPlayer.jump("east", allRooms);
		assertEquals(testPlayer.getCurrentRoom(), room3);
		
	}
	
	@Test
	public void testObstacle() {
		testPlayer.move("west", allRooms);
		assertNotEquals(testPlayer.getCurrentRoom(), room4);
	}
	@Test
	public void testTalk() {
		assertEquals("You can't talk to yourself!", testPlayer.talk(testPlayer));
		assertEquals(testNPC.getDialogue(), testPlayer.talk(testNPC));
		assertEquals(testEnemy.getDialogue(), testPlayer.talk(testEnemy));

	}
	
	@Test
	public void testGetMaxHP() {
		testPlayer.setMaxHP(1);
		assertEquals(testPlayer.getMaxHP(), 1);
	}
	@Test
	public void testGetHP() {
		testPlayer.setHP(1);
		assertEquals(testPlayer.getHP(), 1);
	}
	@Test
	public void testGetMaxResource() {
		testPlayer.setMaxResource(1);
		assertEquals(testPlayer.getMaxResource(), 1);
	}
	@Test
	public void testGetResource() {
		testPlayer.setResource(1);
		assertEquals(testPlayer.getResource(), 1);
	}
	@Test
	public void testGetAtk() {
		testPlayer.setAtk(1);
		assertEquals(testPlayer.getAtk(), 1);
	}
	@Test
	public void testGetDef() {
		testPlayer.setDef(1);
		assertEquals(testPlayer.getDef(), 1);
	}
	@Test
	public void testGetGold() {
		testPlayer.setGold(1);
		assertEquals(testPlayer.getGold(), 1);
	}
	@Test
	public void testGetXP() {
		testPlayer.setXP(1);
		assertEquals(testPlayer.getXP(), 1);
	}
	@Test
	public void testGetStatus() {
		testPlayer.setStatus("test");
		assertEquals(testPlayer.getStatus(), "test");
	}
	@Test
	public void testIsDeadMethods() {
		testPlayer.setIsDead(true);
		assertTrue(testPlayer.getIsDead());
	}
	@Test
	public void testNameMethods() {
		testPlayer.setName("test1");
		assertEquals("test1", testPlayer.getName());
	}
	@Test
	public void testAbilitiesMethods() {
		testPlayer.setAbilities(abilities2);
		assertEquals(abilities2, testPlayer.getAbilities());
	}
	@Test
	public void testInventoryMethods() {
		testPlayer.setInventory(testInv);
		assertEquals(testInv, testPlayer.getInventory());
	}
	@Test
	public void testCurrentRoomMethods() {
		testPlayer.setCurrentRoom(room2);
		assertEquals(room2, testPlayer.getCurrentRoom());
	}
	@Test
	public void testCast() {
		testPlayer.cast(testEnemy, testAtkSpell);
		assertEquals("normal", testPlayer.getStatus());
		String tmp = testPlayer.cast(testPlayer, testMaxHPSpell);
		assertEquals(1005, testPlayer.getMaxHP());
		assertEquals(45, testPlayer.getResource());
		assertEquals("You cast " + testMaxHPSpell.getName() + " it did " + Math.abs(testMaxHPSpell.getEffect()) + " to " + testPlayer.getName() + "'s " + testMaxHPSpell.getAffectedStat() + ", it now has " + testPlayer.getMaxHP() + " " + testMaxHPSpell.getAffectedStat(), tmp);
		testPlayer.cast(testPlayer, testHPSpell);
		assertEquals(105, testPlayer.getHP());
		testPlayer.cast(testPlayer, testMaxResourceSpell);
		assertEquals(205, testPlayer.getMaxResource());
		testPlayer.cast(testPlayer, testResourceSpell);
		assertEquals(35, testPlayer.getResource());
		testPlayer.cast(testPlayer, testAtkSpell);
		assertEquals(15, testPlayer.getAtk());
		testPlayer.cast(testPlayer, testDefSpell);
		assertEquals(10, testPlayer.getDef());
		testPlayer.setIsDead(true);
		tmp = testPlayer.cast(testPlayer, testGodmodeSpell);
		assertEquals(110, testPlayer.getHP());
		assertEquals(25, testPlayer.getResource());
		assertEquals(20, testPlayer.getAtk());
		assertFalse(testPlayer.getIsDead());
		assertEquals("Godmode activated.", tmp);
		testNPC.setIsDead(true);
		tmp = testPlayer.cast(testNPC, testAtkSpell);
		assertEquals(testNPC.getName() + " is dead.", tmp);
		testPlayer.setResource(0);
		tmp = testPlayer.cast(testNPC, testAtkSpell);
		assertEquals("You don't have enough resource to cast " + testAtkSpell.getName(), tmp);
		testPlayer.setResource(10);
		testPlayer.setIsDead(true);
		tmp = testPlayer.cast(testNPC, testHPSpell);
		assertEquals("You are dead!", tmp);



	}
	@Test
	public void testBasicAttack() {
		
		String tmp = testPlayer.basicAttack(testNPC);
		assertEquals("combat", testPlayer.getStatus());
		assertEquals(95, testNPC.getHP());
		assertEquals("You did 10 to test, it now has 95 HP.", tmp);
		testNPC.setIsDead(true);
		tmp = testPlayer.basicAttack(testNPC);
		assertEquals("normal", testPlayer.getStatus());
		assertEquals("test is dead.", tmp);
		testPlayer.setAtk(1);
		testPlayer.setDef(2);
		int hp = testPlayer.getHP();
		testPlayer.basicAttack(testPlayer);
		assertEquals(hp, testPlayer.getHP());
		testPlayer.setIsDead(true);
		assertEquals("", testPlayer.basicAttack(testEnemy));
		

	}
	@Test
	public void testRun() {
		String tmp = testPlayer.run();
		assertEquals("normal", testPlayer.getStatus());
		assertEquals("AAAAAAAHHHHHHHHHHHHHHHHHHH!", tmp);
	}
	@Test
	public void testLeave() {
		String tmp = testPlayer.leave();
		assertEquals("normal", testPlayer.getStatus());
		assertEquals("Goodbye!", tmp);
	}
	
	@Test
	public void testRoomByRoomId() {
		assertEquals(room4, testPlayer.getRoomByRoomId(4, allRooms));
		assertEquals("empty", testPlayer.getRoomByRoomId(5, allRooms).getDescription());
	}
	
	@Test
	public void testListIndexbyItem() {
		assertEquals(1, testPlayer.getListIndexbyItem(rope, allItems));
		assertEquals(-1, testPlayer.getListIndexbyItem(testTorch, allItems));
	}
	
	@Test
	public void testCheckPlayerEquippedSlot() {
		testPlayer.getCurrentRoom().getInventory().addItem(testSword);
		testPlayer.take(testSword, testPlayer.getInventory());
		testPlayer.equip(testSword, allItems);
		assertTrue(testPlayer.checkPlayerEquippedSlot(testPlayer.getInventory().getInventory(), "righthand"));
		testPlayer.unequip(testSword, allItems);
		assertFalse(testPlayer.checkPlayerEquippedSlot(testPlayer.getInventory().getInventory(), "righthand"));
	}
	
	
}
