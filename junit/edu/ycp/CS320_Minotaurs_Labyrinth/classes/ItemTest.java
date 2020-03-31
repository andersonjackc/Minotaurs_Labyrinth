package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

private Item testItem;
private Item testHPPotion;
private Item testMaxHPPotion;
private Item testResPotion;
private Item testMaxResPotion;
private Item testAtkPotion;
private Item testDefPotion;
private Player testPlayer;
	
	@Before
	public void setUp() {
		
		testItem = new Item("test", 1, true, false, true, 10, "testItem", "test", null);
		testHPPotion = new Item("test", 1, true, false, true, 10, "testItem", "test", "HP");
		testMaxHPPotion = new Item("test", 1, true, false, true, 10, "testItem", "test", "maxHP");
		testResPotion = new Item("test", 1, true, false, true, 10, "testItem", "test", "resource");
		testMaxResPotion = new Item("test", 1, true, false, true, 10, "testItem", "test", "maxResource");
		testAtkPotion = new Item("test", 1, true, false, true, 10, "testItem", "test", "atk");
		testDefPotion = new Item("test", 1, true, false, true, 10, "testItem", "test", "def");

		testPlayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null, null, false);
	}
	
	@Test
	public void testDescriptionMethods() {
		
		assertEquals("test", testItem.getDescription());
	}
	
	@Test
	public void testEffectMethods() {
		
		assertEquals(1, testItem.getEffect());
	}
	
	@Test
	public void testFlammableMethods() {
		
		assertTrue(testItem.getFlammable());
	}
	
	@Test
	public void testLitMethods() {
		testItem.setLit(true);
		assertTrue(testItem.getLit());
	}
	
	@Test
	public void testThrowableMethods() {
		assertTrue(testItem.getThrowable());
	}
	
	@Test
	public void testNameMethods() {
		testItem.setName("test");
		assertEquals("test",testItem.getName());
	}
	
	@Test
	public void testVarietyMethods() {
		assertEquals("test", testItem.getVariety());
	}
	
	@Test
	public void testItemValue() {
		assertEquals(testItem.getValue(), 10);
	}
	
	@Test
	public void testItemName() {
		assertEquals(testItem.getName(), "testItem");
	}
	
	@Test
	public void testAddEffect() {
		testHPPotion.addEffect(testPlayer);
		assertEquals(101, testPlayer.getHP());
		testMaxHPPotion.addEffect(testPlayer);
		assertEquals(1001, testPlayer.getMaxHP());
		testResPotion.addEffect(testPlayer);
		assertEquals(51, testPlayer.getResource());
		testMaxResPotion.addEffect(testPlayer);
		assertEquals(201, testPlayer.getMaxResource());
		testAtkPotion.addEffect(testPlayer);
		assertEquals(11, testPlayer.getAtk());
		testDefPotion.addEffect(testPlayer);
		assertEquals(6, testPlayer.getDef());
	}
	

}
