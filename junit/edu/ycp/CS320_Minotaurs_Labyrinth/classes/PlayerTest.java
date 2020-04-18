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
	Ability testMaxHPSpell = new Ability("test", "test ability", "test", "maxHP", 5, 5);
	ArrayList<Ability> abilities = new ArrayList<Ability>();
	HashMap<String, Room> testRoomMap1;
	HashMap<String, Room> testRoomMap2;
	HashMap<String, Room> testRoomMap3;
	HashMap<String, Room> testRoomMap4;

	Item key = new Item("test", 1, true, false, true, 10, null, "misc", null);
	Obstacle obs = new Obstacle("test", "normal", key);
	Obstacle obs1 = new Obstacle("test", "jumping", key);
	Obstacle obs2 = new Obstacle("test", "crawling", key);
	Room room, room2, room3, room4;
	
	
	Inventory testInv = new Inventory(100, 100, Inv);
	

	
	
	NPC testNPC = new NPC(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null, 100, "A test NPC", "test", null, room, false);
	Item testItem = new Item("A test item", 5, true, false, true, 50, "testItem", null, null);
	Item rope = new Item("rope", 0, true, false, true, 50, "rope", "misc", null);

	ArrayList<Item> Inventory = new ArrayList<Item>();
	Inventory testInventory = new Inventory(100, 100, Inventory);
	Item testPotion = new Item("test", 10, false, false, true, 5, "health potion", "potion", "HP");
	Item testTorch = new Item("test", 0, true, false, true, 1, "torch", "harmmisc", null);
	Gear testSword = new Gear(5, 0 , 0, "sword", false, "test", 0, false, false, true, 5, "sword", null);

	@Before
	public void setUp() {
		testRoomMap1 = new HashMap<String, Room>();
		testRoomMap2 = new HashMap<String, Room>();
		testRoomMap3 = new HashMap<String, Room>();
		testRoomMap4 = new HashMap<String, Room>();
		room = new Room("A test room", testRoomInv, obs, testRoomMap1,  false, 1);
		room2 = new Room("A test room", testRoomInv, obs, testRoomMap2,  false, 1);
		room3 = new Room("A test room", testRoomInv, obs1, testRoomMap3,  false, 1);
		room4 = new Room("A test room", testRoomInv, obs2, testRoomMap4, false, 1);
		testRoomMap1.put("north", room2);
		testRoomMap1.put("east", room3);
		testRoomMap1.put("west", room4);
		testRoomMap2.put("south", room);
		abilities.add(testMaxHPSpell);
		testPlayer = new Player(1000, 100, 200, 50, 10, 5, 2, 3, abilities, "normal", testInv, room, false, "test");
		
		
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
		testPlayer.take(testTorch);
		testPlayer.light(testTorch);
		assertTrue(testTorch.getLit());
	}
	
	@Test
	public void testEquip() {
		int oldatk = testPlayer.getAtk();
		testPlayer.take(testSword);
		testPlayer.equip(testSword);
		assertEquals(oldatk + testSword.getAtk(), testPlayer.getAtk());
	}
	
	@Test
	public void testUnequip() {
		testPlayer.take(testSword);
		testPlayer.equip(testSword);
		int oldatk = testPlayer.getAtk();
		testPlayer.unequip(testSword);
		assertEquals(oldatk - testSword.getAtk(), testPlayer.getAtk());
	}
	
	@Test
	public void testUse() {
		testPlayer.take(testPotion);
		testPlayer.use(testPotion, testPlayer);
		assertEquals(110, testPlayer.getHP());
	}
	
	@Test
	public void testTake() {
		testPlayer.take(testItem);
		assertTrue(testPlayer.getInventory().getInventory().contains(testItem));
	}
	
	@Test
	public void testDrop() {
		testPlayer.take(testItem);
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
		testPlayer.take(rope);
		testPlayer.throObs(obs1, rope);
		assertEquals("normal", obs1.getStatus());
	}
	@Test
	public void testMove() {
		testPlayer.move("north");
		assertEquals(testPlayer.getCurrentRoom(), room2);
	}
	@Test
	public void testCrawl() {
		testPlayer.crawl("west");
		assertEquals(testPlayer.getCurrentRoom(), room4);
	}
	
	@Test
	public void testJump() {
		testPlayer.jump("east");
		assertEquals(testPlayer.getCurrentRoom(), room3);
		
	}
	
	@Test
	public void testObstacle() {
		testPlayer.move("east");
		assertNotEquals(testPlayer.getCurrentRoom(), room3);
	}
	@Test
	public void testTalk() {
		assertEquals(testNPC.getDialogue(), testPlayer.talk(testNPC));
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
	public void testCast() {
		testPlayer.cast(testPlayer, testMaxHPSpell);
		assertEquals(1005, testPlayer.getMaxHP());
		assertEquals(45, testPlayer.getResource());

	}
	
}
