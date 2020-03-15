package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Enemy extends NPC {
		
	int atk;
	int hp;
	
	public Enemy(String dialogue, int attitude, String description, String name, int atk, int hp) {
		super(dialogue, attitude, description, name);
		
	}

	public void rollForAction() throws Exception {
		throw new Exception("TODO ");
	}

	@Override
	public void basicAttack(Actor target) {
		target.setHP(target.getHP() - atk); 
		
	}
	
	@Override
	public void cast(Actor target, Ability spell) {
		
		
	}
	
	public int getHP() {
		return hp;
	}
	
	public int getAtk() {
		return atk;
	}
	
}
