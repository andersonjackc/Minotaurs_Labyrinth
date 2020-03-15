package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Enemy extends NPC {
	

	public Enemy(int maxHP, int HP, int maxResource, int resource, int atk, int def, int gold, int XP, ArrayList<Ability> abilities, String status, String dialogue, int attitude, String description, String name) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status, dialogue, attitude, description, name);
		
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
