package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public abstract class Actor {
	
	protected int maxHP, HP, maxResource, resource, atk, def, gold, XP;
	
	protected ArrayList<Ability> abilities;
	
	protected String status;
	
	public Actor(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status){
		this.maxHP = maxHP;
		this.HP = HP;
		this.maxResource = maxResource;
		this.resource = resource;
		this.atk = atk;
		this.def = def;
		this.gold = gold;
		this.XP = XP;
		this.abilities = abilities;
		this.status = status;
		
	}
	
	public abstract void basicAttack(Player target);
	
	public abstract void cast(Actor target, Ability spell);
	
	//getters
	public int getMaxHP() {
		return maxHP;
	}

	public int getHP() {
		return HP;
	}
	
	public int getMaxResource() {
		return maxResource;
	}
	
	public int getResource() {
		return resource;
	}

	public int getAtk() {
		return atk;
	}
	
	public int getDef() {
		return def;
	}
	
	public int getGold() {
		return gold;
	}

	public int getXP() {
		return XP;
	}
	
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}
	
	public String getStatus() {
		return status;
	}
		
	//setters
	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public void setResource(int resource) {
		this.resource =  resource;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setXP(int XP) {
		this.XP = XP;
	}
}
