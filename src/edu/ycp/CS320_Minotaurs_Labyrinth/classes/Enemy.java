package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Enemy extends NPC {
	
	//methods
	public Enemy(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status, String dialogue, int attitude, String description, String name, Inventory inventory, Room currentRoom, boolean isDead) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, dialogue, attitude, description, name, inventory, currentRoom, isDead);
		
	}

	public void rollForAction() throws Exception {
		throw new Exception("TODO ");
	}

	public String basicAttack(Actor target) {
		target.setHP((target.getHP() - getAtk())); 
		
		if(target.getHP()<=0 || target.getIsDead()) {
			target.setIsDead(true);
			
			return "You are dead.";
		}
		
		return this.name + " did " + this.getAtk() + " to " + target.getName() + ", you now have " + target.getHP() + " HP.";
	}
	
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
		
		public Room getCurrentRoom() {
			return currentRoom;
		}
		
		public Boolean getIsDead() {
			return isDead;
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
		
		public String getDialogue() {
			return dialogue;
		}

		public int getAttitude() {
			return attitude;
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
		
		public void setAttitude(int attitude) {
			this.attitude = attitude;
		}

		public void setAbilities(ArrayList<Ability> abilities) {
			this.abilities = abilities;
		}

		public void setStatus(String status) {
			this.status = status;
			
		}

		public void setInventory(Inventory inventory) {
			this.inventory = inventory;
			
		}
	
}
