package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ycp.cs320.Minotaurs_Labyrinth.controller.MinotaursLabyrinthController;
import edu.ycp.cs320.Minotaurs_Labyrinth.model.Minotaur;

public class MinotaursLabyrinthControllerTest {

	Minotaur model = new Minotaur();
	MinotaursLabyrinthController MLC = new MinotaursLabyrinthController();
	@Test
	public void testControllerMethods() {
		MLC.setModel(model);
		assertEquals(model, MLC.getModel());
	}

}
