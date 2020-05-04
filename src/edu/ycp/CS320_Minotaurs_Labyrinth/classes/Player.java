package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;
import java.util.HashMap;


public class Player extends Actor {
	
	//methods
	public Player(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP,
			ArrayList<Ability> abilities, String status, Inventory inventory, Room currentRoom, boolean isDead, String name) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, inventory, currentRoom, isDead, name);
		
	}
	
	public String crawl(String direction, ArrayList<Room> allRooms) {
		
		String tmpStatus = getStatus();
		
		setStatus("crawling");
		
		String tmp = this.move(direction, allRooms);
		
		setStatus(tmpStatus);
		
		return tmp;
	}
	
	public String jump(String direction, ArrayList<Room> allRooms) {
		
		String tmpStatus = getStatus();
		
		setStatus("jumping");
		
		String tmp = this.move(direction, allRooms);
		
		setStatus(tmpStatus);
		
		return tmp;
	}
	
	public String light(Item item, ArrayList<Item> iList) {
		int index = getListIndexbyItem(item, iList);
		if(item.getFlammable() && item.getLit() == false) {
			item.setLit(true);
			iList.set(index, item);
			return item.getName() + " is now on fire!";
		}else if(item.getFlammable() && item.getLit() == true) {
			item.setLit(true);
			iList.set(index, item);
			return item.getName() + " is already on fire!";
		}
		
		return "You can't light " + item.getName() + " on fire!";	
	}
	
	public String equip(Gear gear, ArrayList<Item> iList) {
		int index = getListIndexbyItem(gear, iList);
		if(!checkPlayerEquippedSlot(this.inventory.getInventory(), gear.getAffectedStat())) {
			if(getInventory().getInventory().contains(gear) && gear.getEquipped() == false) {
				gear.setEquipped(true);
				int atk = getAtk() + gear.getAtk();
				int def = getDef() + gear.getDef();
				setAtk(atk);
				setDef(def);
				iList.set(index, gear);
				return "You have equipped " + gear.getName() + "."; 
			}
		}else if(checkPlayerEquippedSlot(this.inventory.getInventory(), gear.getAffectedStat())) {
			return "You already have something equipped in that slot!";
			
		}
		return "You can't equip " + gear.getName() + "."; 
		
	}
	
	public String unequip(Gear gear, ArrayList<Item> iList) {
		int index = getListIndexbyItem(gear, iList);
		if(getInventory().getInventory().contains(gear) && gear.getEquipped() == true) {
			gear.setEquipped(false);
			int atk = getAtk() - gear.getAtk();
			int def = getDef() - gear.getDef();
			setAtk(atk);
			setDef(def);
			iList.set(index, gear);
			return "You have unequipped " + gear.getName() + "."; 
		}
		return "You can't unequip " + gear.getName() + "."; 
		
	}
	
	public String drop(Item item, Room room) {
		String tmp = this.inventory.removeItem(item);
		room.getInventory().addItem(item);
		return tmp;
	}
	
	public String run() {
		this.status = "normal";
		return "AAAAAAAHHHHHHHHHHHHHHHHHHH!";
	}
	
	public String take(Item item, Inventory inv) {
		inv.removeItem(item);
		return this.inventory.addItem(item);
		
	}
	
	public String talk(Actor target) {
		
		if(target.getClass() != this.getClass()) {
			NPC npc = (NPC) target; 
			return npc.getDialogue();
		}
		return "You can't talk to yourself!";
	}
	
	
	public String throObs(Obstacle target, Item item, Room room) {
		if(item.getThrowable() && !target.getName().equals("empty")) {
			if(target.getRequirement().getName().equals(item.getName())) {
				target.setStatus("normal");
				this.inventory.removeItem(item);
				return "You throw a " + item.getName() + " at the " + target.getName();
			}else if(!target.getRequirement().getName().equals(item.getName())) {
				this.inventory.removeItem(item);
				room.getInventory().addItem(item);
				return "You throw a " + item.getName() + " at the " + target.getName() + ", it had no effect";
			}
		}else if(!item.getThrowable()) {
			return "You can't throw " + item.getName();
		}
		return "There are no obstacles attached to this room.";
	}
	
	public String use(Item item, Actor target) {
		if(item.getVariety().equals("potion")) {
			this.inventory.removeItem(item);
		}
		return item.addEffect(target);
			
		
	}

	public int barter(NPC npc, Item item) {
		int itemVal=item.getValue();
		int priceOff = 0;
		if(npc.getAttitude()==100) {
			priceOff =(int) (itemVal*.3);
		}
		else if(npc.getAttitude()<100 && npc.getAttitude()>=80) {
			priceOff=(int) (itemVal*.2);
		}
		else if(npc.getAttitude()<80 && npc.getAttitude()>=60) {
			priceOff=(int) (itemVal*.1);
		}
		else {
			priceOff=(int)(itemVal*0);
		}
		return priceOff;
	}
	
	public String leave() {
		this.status = "normal";
		return "Goodbye!";
	}
	
	public String move(String direction, ArrayList<Room> allRooms) {
		
		HashMap<String, Integer> roomMap = this.currentRoom.getRoomMap();
		
		if(!this.isDead) {
			if(roomMap.containsKey(direction)){
				
				Room newRoom = getRoomByRoomId(roomMap.get(direction), allRooms);
				if(newRoom.getObstacle().checkStatus(this) || newRoom.getObstacle().getStatus().equals("normal") || newRoom.getObstacle().checkRequirement(this)){
					if(newRoom.getIsFound()==false) {
						newRoom.setIsFound(true);
					}
					this.currentRoom = newRoom;
					
				}else if(!newRoom.getObstacle().checkStatus(this)) {
					return "There is an obstacle in that direction! " + newRoom.getObstacle().getDescription();
				}
			}else {
				return "You can't move in that direction!";
			}
			
			String tempStr = this.currentRoom.getDescription();
			Inventory inventory = this.currentRoom.getInventory();
			for(Item item : inventory.getInventory()) {
				String resp = "There is a " + item.getName() + " in the room.";
				tempStr= tempStr + " " + resp;
			}
			return tempStr;
			
		}else {
			return "You are dead!";
		}
		
	}
	
	public String basicAttack(Actor target) {
		this.status = "combat";
		if(!this.isDead) {
			int atk = getAtk() - target.getDef();
			if(atk < 0) {
				atk = 0;
			}
			target.setHP((target.getHP() - atk)); 
			
			if(target.getHP()<=0 || target.getIsDead()) {
				target.setIsDead(true);
				this.status = "normal";
				return target.getName() + " is dead.";
				
			}
			
			return "You did " + this.getAtk() + " to " + target.getName() + ", it now has " + target.getHP() + " HP.";
		}
		return "";
	}

	public String cast(Actor target, Ability spell) {
		
		if(!this.isDead || spell.getName().equals("godmode")) {
			if(!target.getName().equals(this.name)) {
				this.status = "combat";
			}
			if(abilities.contains(spell) && spell.getCost() <= this.resource) {
				spell.addEffect(target);
				if(target.getHP() <= 0 || target.getIsDead()) {
					target.setIsDead(true);
					this.status = "normal";
					return target.getName() + " is dead.";
					
				}
				setResource(getResource() - spell.getCost());
				if(spell.getAffectedStat().equals("HP")) {
					return "You cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", it now has " + target.getHP() + " " + spell.getAffectedStat();
				  
				} 
				else if(spell.getAffectedStat().equals("maxHP")) {
					return "You cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", it now has " + target.getMaxHP() + " " + spell.getAffectedStat();
		
				}
				else if(spell.getAffectedStat().equals("resource")) {
					return "You cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", it now has " + target.getResource() + " " + spell.getAffectedStat();
		
				}
				else if(spell.getAffectedStat().equals("maxResource")) {
					return "You cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", it now has " + target.getMaxResource() + " " + spell.getAffectedStat();
		
				}
				else if(spell.getAffectedStat().equals("atk")) {
					return "You cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", it now has " + target.getAtk() + " " + spell.getAffectedStat();
		
				}
				else if(spell.getAffectedStat().equals("def")) {
					return "You cast " + spell.getName() + " it did " + Math.abs(spell.getEffect()) + " to " + target.getName() + "'s " + spell.getAffectedStat() + ", it now has " + target.getDef() + " " + spell.getAffectedStat();
		
				}
				else if(spell.getAffectedStat().equals("godmode")) {
					this.status = "normal";
					return "Godmode activated.";
				}
				}
			if(spell.getCost() > this.resource) {
				return "You don't have enough resource to cast " + spell.getName();
			}
		}
		return "You are dead!";
	}
	
	//helper
	public int getListIndexbyItem(Item i, ArrayList<Item> iList ) {
		ItemComparator IC = new ItemComparator();
		int count = 0;
		for(Item item : iList) {
			if(IC.compare(i, item) == 1) {
				return count;
			}
			count++;
		}
		return -1;
	}
	
	private Room getRoomByRoomId(Integer room_id, ArrayList<Room> allRooms) {
		for(Room room : allRooms) {
			if(room.getRoomId() == room_id) {
				return room;
			}
		}
		return null;
	}
	
	private Boolean checkPlayerEquippedSlot(ArrayList<Item> inv, String slot) {
		for(Item gear : inv) {
			if(gear.getAffectedStat().equals(slot)) {
				Gear tmpGear = (Gear) gear;
				if(tmpGear.getEquipped()) {
					return true;
				}
			}
		}
		return false;
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
	
	public String getName() {
		return name;
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
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setIsDead(Boolean isDead) {
		this.isDead = isDead;
	}

	public void setAbilities(ArrayList<Ability> abilities) {
		this.abilities = abilities;
		
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
}
