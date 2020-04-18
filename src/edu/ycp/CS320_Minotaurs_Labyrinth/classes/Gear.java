package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Gear extends Item{
	//attributes
	private int atk, def, HP;
	private Boolean equipped;
	
	//methods
	public Gear(int atk, int def, int HP, String variety, Boolean equipped, String description, int effect, Boolean flammable, Boolean lit, Boolean throwable, int value, String name, String affectedstat) {
		super(description, effect, flammable, lit, throwable, value, name, variety, affectedstat);
		this.atk = atk;
		this.def = def;
		this.HP = HP;
		this.equipped = equipped;
		this.value = value;
		this.name = name;
	}
	
	//getters
	public int getAtk() {
		return atk;
	}
	
	public int getDef() {
		return def;
	}
	
	public int getHP() {
		return HP;
	}
	
	public String getVariety() {
		return variety;
	}
	
	public Boolean getEquipped() {
		return equipped;
	}
	
	//setters
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public void setEquipped(Boolean equipped) {
		this.equipped = equipped;
	}
	







}
