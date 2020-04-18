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

		testPlayer = new Player(1000, 100, 200, 50, 10, 5, 0, 0, null, null, null, null, false, "test");
	}
	
	@Test
	public void testDescriptionMethods() {
		testItem.setDescription("test1");
		assertEquals("test1", testItem.getDescription());
	}
	
	@Test
	public void testEffectMethods() {
		testItem.setEffect(2);
		assertEquals(2, testItem.getEffect());
	}
	
	@Test
	public void testFlammableMethods() {
		testItem.setFlammable(false);
		assertFalse(testItem.getFlammable());
	}
	
	@Test
	public void testLitMethods() {
		testItem.setLit(true);
		assertTrue(testItem.getLit());
	}
	
	@Test
	public void testThrowableMethods() {
		testItem.setThrowable(false);
		assertFalse(testItem.getThrowable());
	}
	
	@Test
	public void testNameMethods() {
		testItem.setName("test");
		assertEquals("test",testItem.getName());
	}
	
	@Test
	public void testVarietyMethods() {
		testItem.setVariety("test1");
		assertEquals("test1", testItem.getVariety());
	}
	
	@Test
	public void testItemValue() {
		testItem.setValue(5);
		assertEquals(testItem.getValue(), 5);
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
