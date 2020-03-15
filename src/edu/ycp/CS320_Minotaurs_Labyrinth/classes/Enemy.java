package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Enemy extends NPC {
	

	public Enemy(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status, String dialogue, int attitude, String description, String name) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, dialogue, attitude, description, name);
		
	}

	public void rollForAction() throws Exception {
		throw new Exception("TODO ");
	}

	@Override
	public void basicAttack(Actor target) {
		target.setHP((target.getHP() - getAtk())); 
		
	}
	
	@Override
	public void cast(Actor target, Ability spell) {
		
		
	}
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
		Enemy.HP = HP;
	}
	
	public void setResource(int resource) {
		Enemy.resource =  resource;
	}

	public void setAtk(int atk) {
		Enemy.atk = atk;
	}
	
	public void setDef(int def) {
		Enemy.def = def;
	}
	
	public void setGold(int gold) {
		Enemy.gold = gold;
	}

	public void setXP(int XP) {
		Enemy.XP = XP;
	}
	
	
}
