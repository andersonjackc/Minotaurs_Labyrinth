package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends NPC {
	
	//methods
	public Enemy(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status, String dialogue, int attitude, String description, String name, Inventory inventory, Room currentRoom, boolean isDead) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, dialogue, attitude, description, name, inventory, currentRoom, isDead);
		
	}

	public String rollForAction(Actor target) {
		
		Random rand = new Random();
		int action = rand.nextInt(2);
		if(action == 0) {
			return basicAttack(target);
		}
		else if(action == 1){
			if(this.abilities.size() > 0) {
				int spellchoice = rand.nextInt(this.abilities.size());
				if(this.resource >= this.abilities.get(spellchoice).getCost()) {	
					return cast(target, this.abilities.get(spellchoice));
				}
			}
			else {
				return basicAttack(target);
			}
		}
		return "";
		
	}

	public String basicAttack(Actor target) {
		if(!this.isDead) {
			int atk = getAtk() - target.getDef();
			if(atk < 0) {
				atk = 0;
			}
			target.setHP((target.getHP() - atk)); 
			
			if(target.getHP()<=0 || target.getIsDead()) {
				target.setIsDead(true);
				target.setStatus("normal");
				return "You are dead.";
			}
			
			return this.name + " did " + this.getAtk() + " to " + target.getName() + ", you now have " + target.getHP() + " HP.";
		}
		return "";
	}
	
	public String cast(Actor target, Ability spell) {
		if(!this.isDead|| spell.getName().equals("godmode")) {
		if(abilities.contains(spell) && spell.getCost() <= resource) {
			spell.addEffect(target);
			setResource(getResource()-spell.getCost());
			if(target.getHP()<=0 || target.getIsDead()) {
				target.setIsDead(true);
				return "You are dead.";
				
			}
			if(spell.getAffectedStat().equals("HP")) {
				return this.name + " cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", you now have " + target.getHP() + " " + spell.getAffectedStat();
		  
			} 
			else if(spell.getAffectedStat().equals("maxHP")) {
				return this.name + " cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", you now have " + target.getMaxHP() + " " + spell.getAffectedStat();

			}
			else if(spell.getAffectedStat().equals("resource")) {
				return this.name + " cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", you now have " + target.getResource() + " " + spell.getAffectedStat();

			}
			else if(spell.getAffectedStat().equals("maxResource")) {
				return this.name + " cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", you now have " + target.getMaxResource() + " " + spell.getAffectedStat();

			}
			else if(spell.getAffectedStat().equals("atk")) {
				return this.name + " cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", you now have " + target.getAtk() + " " + spell.getAffectedStat();

			}
			else if(spell.getAffectedStat().equals("def")) {
				return this.name + " cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", you now have " + target.getDef() + " " + spell.getAffectedStat();

			}
			else if(spell.getAffectedStat().equals("godmode")) {
				this.status = "normal";
				return "Godmode activated.";
			}

			
		}
		if(spell.getCost() > this.resource) {
			return this.getName() + " doesn't have enough resource to cast " + spell.getName();
		}
		}
		return "";
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
		
		public void setDialogue(String dialogue) {
			this.dialogue = dialogue;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	
}
