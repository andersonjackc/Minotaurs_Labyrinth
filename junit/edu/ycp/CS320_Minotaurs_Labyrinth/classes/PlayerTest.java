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
	Item key = new Item("test", 1, true, false, true, 10, "key", "misc", "none");
	Obstacle obs = new Obstacle("test", "normal", key);
	Obstacle obs1 = new Obstacle("test", "jumping", key);
	Obstacle obs2 = new Obstacle("test", "crawling", key);
	Room room, room2, room3, room4;
	
	
	Inventory testInv = new Inventory(100, 100, Inv);
	

	
	Enemy testEnemy = new Enemy(0, 0, 0, 0, 0, 0, 0, 0, abilities, null, "testDialogue", 0, null, null, testInv, room, false);
	NPC testNPC = new NPC(1000, 100, 200, 50, 10, 5, 0, 0, null, null, "testDialogue", 100, "A test NPC", "test", null, room, false);
	Item testItem = new Item("A test item", 5, true, false, true, 50, "testItem", null, null);
	Item rope = new Item("rope", 0, true, false, true, 50, "rope", "misc", null);

	ArrayList<Item> Inventory = new ArrayList<Item>();
	Inventory testInventory = new Inventory(100, 100, Inventory);
	Item testPotion = new Item("test", 10, false, false, true, 5, "health potion", "potion", "HP");
	Item testTorch = new Item("test", 0, true, false, true, 1, "torch", "harmmisc", null);
	Gear testSword = new Gear(5, 0 , 0, "sword", false, "test", 0, false, false, true, 5, "sword", null);

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
		
	}
	
	
	@Test
	public void testCheckStats() {
		assertEquals(testPlayer.checkStats(), "You have: 100 HP, 50 Mana, 10 Attack, 5 Defense");
		
	}
	
	@Test
	public void testCheckNPC() {
		assertEquals(testPlayer.checkNPC(testNPC), "A test NPC");
	}
	
	@Test
	public void testCheckItem() {
		assertEquals(testPlayer.checkItem(testItem), "A test item");
	}
	
	@Test
	public void testCheckValue() {
		assertEquals(testPlayer.checkValue(testItem), "testItem is worth 50 Gold");
	}
	
	@Test
	public void testCheckInventory() {
		assertEquals(testPlayer.checkInventory(testPlayer.getInventory()), "Your inventory is empty!");
		testPlayer.getInventory().addItem(testItem);
		assertEquals(testPlayer.checkInventory(testPlayer.getInventory()), "testItem ");
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
		testPlayer.light(testTorch);
		assertTrue(testTorch.getLit());
	}
	
	@Test
	public void testEquip() {
		testRoomInv.addItem(testSword);
		int oldatk = testPlayer.getAtk();
		testPlayer.take(testSword, testRoomInv);
		testPlayer.equip(testSword);
		assertEquals(oldatk + testSword.getAtk(), testPlayer.getAtk());
	}
	
	@Test
	public void testUnequip() {
		testRoomInv.addItem(testSword);
		testPlayer.take(testSword, testRoomInv);
		testPlayer.equip(testSword);
		int oldatk = testPlayer.getAtk();
		testPlayer.unequip(testSword);
		assertEquals(oldatk - testSword.getAtk(), testPlayer.getAtk());
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
		testPlayer.drop(testItem);
		assertTrue(room.getInventory().getInventory().contains(testItem));
	}
	
	@Test
	public void testThroNPCItem() {
		testPlayer.throNPCItem(testNPC, testPotion);
		assertEquals(110, testNPC.getHP());
		testPlayer.throNPCItem(testNPC, key);
		assertEquals(90, testNPC.getAttitude());
		testPlayer.throNPCItem(testNPC, testTorch);
		assertEquals(80, testNPC.getAttitude());
		assertEquals(99, testNPC.getHP());
	}
	@Test
	public void testThroNPCGear() {
		testPlayer.throNPCGear(testNPC, testSword);
		assertEquals(95, testNPC.getHP());
		assertEquals(80, testNPC.getAttitude());
		
	}
	@Test
	public void testThroObs() {
		testRoomInv.addItem(rope);
		testPlayer.take(rope, testRoomInv);
		testPlayer.throObs(obs1, rope);
		assertEquals("normal", obs1.getStatus());
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
		testPlayer.move("east", allRooms);
		assertNotEquals(testPlayer.getCurrentRoom(), room3);
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
		assertEquals(90, testNPC.getHP());
		assertEquals("You did 10 to test, it now has 90 HP.", tmp);
		testNPC.setIsDead(true);
		tmp = testPlayer.basicAttack(testNPC);
		assertEquals("normal", testPlayer.getStatus());
		assertEquals("test is dead.", tmp);

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
	public void testCheckRoom() {
		assertEquals(testPlayer.getCurrentRoom().getDescription(),testPlayer.checkRoom(room));
	}
	
}
