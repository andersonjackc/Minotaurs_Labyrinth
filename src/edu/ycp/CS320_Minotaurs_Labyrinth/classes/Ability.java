package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Ability {
	//attributes
	private String name, description, variety;
	private int effect, cost;
	
	//methods
	public Ability(String name, String description, String variety, int effect, int cost) {
		this.name = name;
		this.description = description;
		this.variety = variety;
		this.effect = effect;
		this.cost = cost;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getVariety() {
		return variety;
	}
	
	public int getEffect() {
		return effect;
	}
	
	public int getCost() {
		return cost;
	}





}
