package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Enemy extends NPC {
	

	public Enemy(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status, String dialogue, int attitude, String description, String name, Inventory inventory, Room currentRoom, boolean isDead) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, dialogue, attitude, description, name, inventory, currentRoom, isDead);
		
	}

	public void rollForAction() throws Exception {
		throw new Exception("TODO ");
	}

	@Override
	public void basicAttack(Actor target) {
		target.setHP((target.getHP() - getAtk())); 
		
		if(target.getHP()<=0) {
			target.setIsDead(true);
		}
	}
	
	@Override
	public void cast(Actor target, Ability spell) {
		if(abilities.contains(spell) && spell.getCost() <= resource) {
			spell.addEffect(target);
			setResource(getResource()-spell.getCost());
			}
		
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
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public Boolean getIsDead() {
		return isDead;
	}
		
	//setters
	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	public void setResource(int resource) {
		this.resource =  resource;
	}
	
	public void setMaxResource(int maxResource) {
		this.maxResource = maxResource;
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

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public void setIsDead(Boolean isDead) {
		this.isDead = isDead;
	}
	
}
