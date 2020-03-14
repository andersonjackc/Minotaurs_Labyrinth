package edu.ycp.cs320.Minotaurs_Labyrinth.controller;

import edu.ycp.cs320.Minotaurs_Labyrinth.model.Numbers;

public class MultiplyNumbersController {
	private Numbers model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(Numbers model) {
		this.model = model;
	}
	//ajax method not mine
	public Double multiply(Double first, Double second, Double third) {
		return first * second * third;
	}
	//sets all 3 VALID variables
	public void setAll(Double first, Double second, Double third) {
		model.setFirst(first);
		model.setSecond(second);
		model.setThird(third);
	}
	//calcs the product
	public Double MultiplyResult() {
		model.setMultiplyresult();
		return model.getResult();
	}
}
