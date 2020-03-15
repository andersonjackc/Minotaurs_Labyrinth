package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Enemy extends NPC {
	
	//methods
	public Enemy(String dialogue, int attitude, String description, String name) {
		super(dialogue, attitude, description, name);
		
	}
	
	public void rollForAction() throws Exception {
		throw new Exception("TODO ");
	}

	@Override
	public void basicAttack(Actor target) {
		target.setHP(target.getHP() - getAtk()); 
		
	}
	
	@Override
	public void cast(Actor target, Ability spell) {
		
		
	}
	
}
