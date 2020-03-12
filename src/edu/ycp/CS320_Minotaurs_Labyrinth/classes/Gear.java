package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Gear extends Item{
	//attributes
	private int atk, def, HP;
	private String variety;
	
	//methods
	public Gear(int atk, int def, int HP, String variety, String description, int effect) {
		super(description, effect);
		this.atk = atk;
		this.def = def;
		this.HP = HP;
		this.variety = variety;
		}

}
