package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Player extends Actor {
	
	
	public Player(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP,
			ArrayList<Ability> abilities, String status, Inventory inventory) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, inventory);
		
	}
	
	
	public void crawl() {
		// TODO Auto-generated method stub
		
	}
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	public void light(Item item) {
		if(getInventory().getInventory().contains(item) && item.getFlammable() && item.getLit() == false) {
			item.setLit(true);
		}
		
	}
	public void equip(Gear gear) {
		if(getInventory().getInventory().contains(gear) && gear.getEquipped() == false) {
			gear.setEquipped(true);
			int atk = getAtk() + gear.getAtk();
			int def = getDef() + gear.getDef();
			setAtk(atk);
			setDef(def);
		}
		
	}
	public void unequip(Gear gear) {
		if(getInventory().getInventory().contains(gear) && gear.getEquipped() == true) {
			gear.setEquipped(false);
			int atk = getAtk() - gear.getAtk();
			int def = getDef() - gear.getDef();
			setAtk(atk);
			setDef(def);
		}
		
	}
	public void drop(Item item) {
		getInventory().removeItem(item);
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public void take(Item item) {
		getInventory().addItem(item);
		
	}
	public void talk() {
		// TODO Auto-generated method stub
		
	}
	public void thro() {
		// TODO Auto-generated method stub
		
	}
	public void use(Item item, Actor target) {
		if(getInventory().getInventory().contains(item)) {
			target.setHP(target.getHP()+item.getEffect());
		}
		
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
		String Message = "You have: " + this.HP + " HP, " + this.resource + " Mana, " + this.atk + " Attack, " 
				+ this.def + " Defense";
		return Message;
	}
	public String checkNPC(NPC npc) {
		String Message = npc.getDescription();
		return Message;
	}
	
	public String checkValue(Item item) {
		String Message = item.getName() + " is worth " + item.getValue() + " Gold";
		return Message;
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
			
		public Inventory getInventory() {
			return inventory;
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
