package edu.ycp.cs320.Minotaurs_Labyrinth.model;
import java.util.ArrayList;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
public class Dialogue {
	
	
	NPC villager;
	Player PlayerCharacter;
	String NPCDesc;
	String NPCResp;
	String PlayerResp;
	String errorMessage, attackMessage, defendMessage;
	public void initPlayers() {
		//creates an instance of player
		ArrayList<Ability> PlayerAbilities = new ArrayList<Ability>();
		ArrayList<Item> items = new ArrayList<Item>();
		Inventory i = new Inventory(0, 0, items);
		PlayerCharacter = new Player(1000, 20, 1000, 10, 2, 2, 50, 0, PlayerAbilities, "Normal", i, null, false);
		
		//creates an instance of enemy

		ArrayList<Ability> VillagerAbilities = new ArrayList<Ability>();
		villager = new NPC(10, 10, 0, 0, 1, 0, 0, 0, VillagerAbilities, "Villager", "Hello Traveler!", 100, "An old man with tattered clothing", "Villager", i, null, false);
		


	}
	
	public String getNPCDesc() {
		NPCDesc = "You see before you " + villager.getDescription();
		return NPCDesc;
	}
	
	public String getPlayerResp() {
		
		return PlayerResp;
	}
	
	public String getNPCResp() {
		
		return NPCResp;
	}
	
	public void initResponses() {
		PlayerResp = "Greetings";
		NPCResp = "The " + villager.getName() + " says " + villager.getDialogue();
	}
	
	
	public void enemyAtk() {
		
		villager.basicAttack(PlayerCharacter);
		defendMessage = "Villager did " + villager.getAtk() + " You now have " + PlayerCharacter.getHP();
	
	}
	
	public void playerAtk() {
	
		PlayerCharacter.basicAttack(villager);
		attackMessage = "You did " + PlayerCharacter.getAtk() + " to " + villager.getName() + ", it now has " + villager.getHP() + " HP";
		
	}
	public String getAttackmessage() {
		return attackMessage;
	}
	public String getDefendmessage() {
		return defendMessage;
	}
	public void setPlayerHP(int HP) {
		PlayerCharacter.setHP(HP);
	}
	public int getPlayerHP() {
		return PlayerCharacter.getHP();
	}
	public void setEnemyHP(int HP) {
		villager.setHP(HP);
	}
	public int getEnemyHP() {
		return villager.getHP();
	}
	
	public String getError() {
		return errorMessage;
	}
	
	public void setError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

