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

		public abstract int getHP(); 
		
		public abstract int getMaxResource(); 
		
		public abstract int getResource();

		public abstract int getAtk();
		
		public abstract int getDef();
		
		public abstract int getGold();

		public abstract int getXP();
		
		public abstract ArrayList<Ability> getAbilities();
		
		public abstract String getStatus();
			
		//setters
		public abstract void setHP(int HP);
		
		public abstract void setResource(int resource);

		public abstract void setAtk(int atk);
		
		public abstract void setDef(int def);
		
		public abstract void setGold(int gold);

		public abstract void setXP(int XP);
}
