package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public abstract class Actor {
	
	protected int HP, resource, atk, def, gold, XP;
	
	protected Ability[] abilities;
	
	protected String status;
	
	public Actor(){
		
	}
	
	public abstract void basicAttack();
	public abstract void cast();
	
}
