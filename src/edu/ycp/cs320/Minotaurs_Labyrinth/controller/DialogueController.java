package edu.ycp.cs320.Minotaurs_Labyrinth.controller;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Dialogue;

public class DialogueController {
	
	private Dialogue model;
	
	public void setModel(Dialogue model) {
		this.model = model;
	}
	
	public void initModel() {
		model.initPlayers();
	}
}
