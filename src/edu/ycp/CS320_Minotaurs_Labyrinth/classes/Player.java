package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Player extends Actor {
	
	
	public Player(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP,
			ArrayList<Ability> abilities, String status) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status);
		
	}
	
	
	public void crawl() {
		// TODO Auto-generated method stub
		
	}
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	public void light() {
		// TODO Auto-generated method stub
		
	}
	public void equip() {
		// TODO Auto-generated method stub
		
	}
	public void unequip() {
		// TODO Auto-generated method stub
		
	}
	public void drop() {
		// TODO Auto-generated method stub
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public void take() {
		// TODO Auto-generated method stub
		
	}
	public void talk() {
		// TODO Auto-generated method stub
		
	}
	public void thro() {
		// TODO Auto-generated method stub
		
	}
	public void use() {
		// TODO Auto-generated method stub
		
	}
	public void check() {
		// TODO Auto-generated method stub	
	}
	public void barter() {
		// TODO Auto-generated method stub
		
	}
	public void leave() {
		// TODO Auto-generated method stub
		
	}
	public void move() {
		// TODO Auto-generated method stub
		
	}
	public String checkMap() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO - implement");

	}
	public String checkStats() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO - implement");

	}
	public String checkNPC() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO - implement");

	}
	public String checkValue() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO - implement");

	}
	public String checkInventory() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO - implement");

	}

	@Override
	public void basicAttack(Actor target) {
		target.setHP((target.getHP() - getAtk())); 
		
	}

	@Override
	public void cast(Actor target, Ability spell) {
		// TODO Auto-generated method stub
		
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
		Player.HP = HP;
	}
	
	public void setResource(int resource) {
		Player.resource =  resource;
	}

	public void setAtk(int atk) {
		Player.atk = atk;
	}
	
	public void setDef(int def) {
		Player.def = def;
	}
	
	public void setGold(int gold) {
		Player.gold = gold;
	}

	public void setXP(int XP) {
		Player.XP = XP;
	}



}
