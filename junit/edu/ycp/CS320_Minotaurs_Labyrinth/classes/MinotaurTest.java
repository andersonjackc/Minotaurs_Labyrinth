package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;

import org.junit.Test;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Minotaur;

public class MinotaurTest {

	String mapString = "mapString";
	Minotaur model = new Minotaur();
	@Test
	public void testMapStringMethods() {
		model.setMapString(mapString);
		assertEquals(mapString, model.getMapString());
	}

	@Test
	public void testAtkMethods() {
		model.setAtk(1);
		assertEquals(1, model.getAtk());
	}
	
	@Test
	public void testDefMethods() {
		model.setDef(2);
		assertEquals(2, model.getDef());
	}
	
	@Test
	public void testHPMethods() {
		model.setHP(2);
		assertEquals(2, model.getHP());
	}
	
	@Test
	public void testResourceMethods() {
		model.setResource(2);
		assertEquals(2, model.getResource());
	}

	@Test
	public void testGoldMethods() {
		model.setGold(2);
		assertEquals(2, model.getGold());
	}
	
	@Test
	public void testXPMethods() {
		model.setXP(2);
		assertEquals(2, model.getXP());
	}
	
	@Test
	public void testTorqu3StringMethods() {
		model.setTorqu3String("test");
		assertEquals("test", model.getTorqu3String());
	}
	
	@Test
	public void testdjHakeMethods() {
		model.setdjHake("test");
		assertEquals("test", model.getdjHake());
	}
	
	@Test
	public void testLevelUpMethods() {
		Ability test = new Ability("test", "test", "test", "test", 1, 1);
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		ArrayList<Ability> abilityList = new ArrayList<Ability>();
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilityList.add(test);
		abilities.add(test);
		Player testPlayer = new Player(0, 0, 0, 0, 0, 0, 0, 0, abilities, "normal", null, null, false, "player");
		testPlayer.setXP(10);
		model.levelUp(testPlayer, testPlayer.getXP(), abilityList);
		assertEquals(2, testPlayer.getAbilities().size());
		testPlayer.setXP(20);
		model.levelUp(testPlayer, testPlayer.getXP(), abilityList);
		assertEquals(3, testPlayer.getAbilities().size());
		testPlayer.setXP(30);
		model.levelUp(testPlayer, testPlayer.getXP(), abilityList);
		assertEquals(4, testPlayer.getAbilities().size());
		testPlayer.setXP(40);
		model.levelUp(testPlayer, testPlayer.getXP(), abilityList);
		assertEquals(5, testPlayer.getAbilities().size());
		testPlayer.setXP(0);
		String tmp = model.levelUp(testPlayer, testPlayer.getXP(), abilityList);
		assertEquals("", tmp);
	}
	
	@Test
	public void testTorqu3CounterMethods() {
		model.setTorqu3Counter(1);
		assertEquals(1, model.getTorqu3Counter());
	}
	
	
}
