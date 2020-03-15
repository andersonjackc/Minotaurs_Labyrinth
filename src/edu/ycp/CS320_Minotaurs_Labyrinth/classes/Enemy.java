package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Enemy extends NPC {
		
	int atk;
	int hp;
	
	public Enemy(String dialogue, int attitude, String description, String name, int atk, int hp) {
		super(dialogue, attitude, description, name);
		this.atk = atk;
		this.hp = hp;
	}

	public void rollForAction() throws Exception {
		throw new Exception("TODO ");
	}

	@Override
	public void basicAttack(Actor target) {
		target.setHP((target.getHP() - getAtk())); 
		
	}
	
	@Override
	public void cast(Actor target, Ability spell) {
		
		
	}
	
	
	
}
