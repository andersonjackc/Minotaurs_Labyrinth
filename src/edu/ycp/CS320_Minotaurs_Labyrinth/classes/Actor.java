package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public abstract class Actor {
	
	protected static int maxHP;

	protected static int HP;

	protected static int maxResource;

	protected static int resource;

	protected static int atk;

	protected static int def;

	protected static int gold;

	protected static int XP;
	
	protected static ArrayList<Ability> abilities;
	
	protected static String status;
	
	public Actor(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status){
		Actor.maxHP = maxHP;
		Actor.HP = HP;
		Actor.maxResource = maxResource;
		Actor.resource = resource;
		Actor.atk = atk;
		Actor.def = def;
		Actor.gold = gold;
		Actor.XP = XP;
		Actor.abilities = abilities;
		Actor.status = status;
		
	}
	
	public abstract void basicAttack(Actor target);
	
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
		Actor.HP = HP;
	}
	
	public void setResource(int resource) {
		Actor.resource =  resource;
	}

	public void setAtk(int atk) {
		Actor.atk = atk;
	}
	
	public void setDef(int def) {
		Actor.def = def;
	}
	
	public void setGold(int gold) {
		Actor.gold = gold;
	}

	public void setXP(int XP) {
		Actor.XP = XP;
	}
}
