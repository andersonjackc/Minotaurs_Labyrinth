package edu.ycp.cs320.Minotaurs_Labyrinth.controller;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Numbers;

public class AddNumbersController {
	private Numbers model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	//this is just for ajax
	public Double add(Double first, Double second, Double third) {
		return first + second + third;
	}
	
	//sets all 3 VALID numbers into the model
	public void setAll(Double first, Double second, Double third) {
		model.setFirst(first);
		model.setSecond(second);
		model.setThird(third);
	}
	
	//calculates the result from the 3 VALID stored model variables and returns them
	public Double AddResult() {
		model.setAddResult();
		return model.getResult();
	}
}
