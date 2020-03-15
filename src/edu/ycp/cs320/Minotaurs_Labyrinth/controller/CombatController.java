package edu.ycp.cs320.Minotaurs_Labyrinth.controller;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Combat;

public class CombatController {
	
	private Combat model;
	
	public void setModel(Combat model) {
		this.model = model;
	}
	
	public void initModel() {
		model.initPlayers();
	}
}
