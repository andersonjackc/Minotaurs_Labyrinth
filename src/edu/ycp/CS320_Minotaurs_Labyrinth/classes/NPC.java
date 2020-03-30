package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class NPC extends Actor {
	//attributes
	protected String dialogue;
	protected int attitude;
	protected String description;
	protected String name;
	
	//methods
	public NPC(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status, String dialogue, int attitude, String description, String name, Inventory inventory, Room currentRoom, boolean isDead) {
		
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, inventory, currentRoom, isDead);
		
		this.dialogue = dialogue;
		this.attitude = attitude;
		this.description = description;
		this.name = name;
	}
	
	public String getDialogue() {
		return dialogue;
	}

	public int getAttitude() {
		return attitude;
	}
	
	public void setAttitude(int attitude) {
		this.attitude = attitude;
	}
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void printDescription(){
		System.out.print(description);	
	}
	
	public void printResponses(){
		System.out.print(dialogue);	
	}
	
	@Override
	public void basicAttack(Actor target) {
		target.setHP(target.getHP() - getAtk()); 
		
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
	
	public Room getCurrentRoom() {
		return currentRoom;
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
}
