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
	
	public Boolean getEquipped() {
		return equipped;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVariety() {
		return variety;
	}
	
	public String getAffectedStat() {
		return affectedstat;
	}
	
	public int getValue() {
		return value;
	}
	
	public Boolean getThrowable() {
		return throwable;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getEffect() {
		return effect;
	}

	public Boolean getFlammable() {
		return flammable;
	}

	public Boolean getLit() {
		return lit;
	}
		
	//setters
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public void setDef(int def) {
		this.def =  def;
	}
	
	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public void setEquipped(Boolean equipped) {
		this.equipped = equipped;
	}
	
	public void setAffectedStat(String affectedstat) {
		this.affectedstat = affectedstat;
	}
		
	public void setName(String name) {
		this.name=name;
	}
		
	public void setValue(int value) {
		this.value=value;
	}

	public void setLit(Boolean lit) {
		
		this.lit = lit;
		
	}
	
	public void setVariety(String variety) {
		this.variety = variety;
	}
	
	public void setThrowable(Boolean throwable) {
		this.throwable = throwable;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		
	public void setEffect(int effect) {
		this.effect = effect;
	}
		
	public void setFlammable(Boolean flammable) {
		this.flammable = flammable;
	}
}
