package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

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

}
