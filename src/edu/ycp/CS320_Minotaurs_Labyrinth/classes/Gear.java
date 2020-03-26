package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Gear extends Item{
	//attributes
	private int atk, def, HP, value;
	private String variety, name;
	private Boolean equipped;
	
	//methods
	public Gear(int atk, int def, int HP, String variety, Boolean equipped, String description, int effect, Boolean flammable, Boolean lit, int value, String name) {
		super(description, effect, flammable, lit, value, name);
		this.atk = atk;
		this.def = def;
		this.HP = HP;
		this.variety = variety;
		this.equipped = equipped;
		this.value = value;
		this.name = name;
		}
	
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
	
	public void setEquipped(Boolean equipped) {
		this.equipped = equipped;
	}
	







}
