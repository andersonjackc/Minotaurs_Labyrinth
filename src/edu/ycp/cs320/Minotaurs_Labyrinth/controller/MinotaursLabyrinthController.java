package edu.ycp.cs320.Minotaurs_Labyrinth.controller;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Minotaur;

public class MinotaursLabyrinthController {
	
	private Minotaur model;
	
	public void setModel(Minotaur model) {
		this.model = model;
	}
	//commit
	//fills the map with 0 for empty, 1 for player,  3 for unmovable
	public void initModel() {
		model.initMap();
		model.initPlayer();
	}
}
