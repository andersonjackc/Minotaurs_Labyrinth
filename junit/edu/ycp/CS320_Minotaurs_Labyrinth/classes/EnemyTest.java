package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnemyTest {

private Enemy testEnemy;
	
	@Before
	public void setUp() {
		
		testEnemy = new Enemy("test", 10, "test", "test");
		testEnemy.maxHP = 5;
		testEnemy.maxResource = 5;
		Ability fireball;
	}
	@Test
	public void testMaxHPMethods() {
		
		assertEquals(5, testEnemy.getMaxHP());
		
	}
	@Test
	public void testHPMethods() {
		testEnemy.setHP(5);
		assertEquals(5, testEnemy.getHP());
	}
	@Test
	public void testMaxResourceMethods() {
		
		assertEquals(5, testEnemy.getMaxResource());
		
	}
	@Test
	public void testResourceMethods() {
		testEnemy.setResource(5);
		assertEquals(5, testEnemy.getResource());
	}
	@Test
	public void testAtkMethods() {
		testEnemy.setAtk(5);
		assertEquals(5, testEnemy.getAtk());
	}
	@Test
	public void testDefMethods() {
		testEnemy.setDef(5);
		assertEquals(5, testEnemy.getDef());
	}
	@Test
	public void testGoldMethods() {
		testEnemy.setGold(5);
		assertEquals(5, testEnemy.getGold());
	}
	@Test
	public void testXPMethods() {
		testEnemy.setXP(5);
		assertEquals(5, testEnemy.getXP());
	}
	@Test
	public void testAbilitiesMethods() {
		fail("Not yet implemented");
	}
	@Test
	public void testDialogueMethods() {
		testEnemy.dialogue = "test";
		assertEquals("test", testEnemy.getDialogue());
	}
	@Test
	public void testAttitudeMethods() {
		testEnemy.setAttitude(5);
		assertEquals(5, testEnemy.getAttitude());
	}
	@Test
	public void testDescriptionMethods() {
		testEnemy.description = "test";
		assertEquals("test", testEnemy.getDescription());
	}
	@Test
	public void testNameMethods() {
		testEnemy.name = "test";
		assertEquals("test", testEnemy.getName());
	}
	@Test
	public void testPrintDescription(){
		fail("Not yet implemented");	
	}
	@Test
	public void testPrintResponses(){
		fail("Not yet implemented");	
	}
	@Test
	public void testBasicAttack() {
		testEnemy.setAtk(1);
		testEnemy.setHP(5);
		testEnemy.basicAttack(testEnemy); 
		assertEquals(4, testEnemy.getHP());
	}
	@Test
	public void testCast() {
		fail("Not yet implemented");
		
	}
	@Test
	public void testRollForAction() {
		fail("Not yet implemented");
	}
}
