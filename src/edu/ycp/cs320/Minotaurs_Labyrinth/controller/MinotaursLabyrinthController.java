package edu.ycp.cs320.Minotaurs_Labyrinth.controller;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Minotaur;

public class MinotaursLabyrinthController {
	
	private Minotaur model;
	
	public void setModel(Minotaur model) {
		this.model = model;
	}
	
	public void initModel() {
		model.initMap();
		model.initPlayer();
	}
}
