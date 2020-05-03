package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

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
}
