package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class NPC extends Actor {
	//attributes
	protected String dialogue;
	protected int attitude;
	protected String description;
	protected String name;
	
	//methods
	public NPC(String dialogue, int attitude, String description, String name) {
		super(maxHP, HP, maxResource, resource, atk, def, gold, XP, abilities, status);
		this.dialogue = dialogue;
		this.attitude = attitude;
		this.description = description;
		this.name = name;
	}
	
	public String getDialogue() {
		return dialogue;
	}

	public int getAttitude() {
		return attitude;
	}
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}
	
	public void printDescription(){
		System.out.print(description);	
	}
	
	public void printResponses(){
		System.out.print(dialogue);	
	}
	
	@Override
	public void basicAttack(Player target) {
		target.setHP(target.getHP() - getAtk()); 
		
	}
	
	@Override
	public void cast(Actor target, Ability spell) {
		
		
	}
}
