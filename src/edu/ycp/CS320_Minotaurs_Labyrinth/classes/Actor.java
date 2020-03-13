package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public abstract class Actor {
	
	protected int HP, resource, atk, def, gold, XP;
	
	protected ArrayList<Ability> abilities;
	
	protected String status;
	
	public Actor(){
		
	}
	
	public abstract void basicAttack();
	public abstract void cast();
	
}
