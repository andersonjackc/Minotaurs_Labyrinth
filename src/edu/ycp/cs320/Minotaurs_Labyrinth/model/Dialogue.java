package edu.ycp.cs320.Minotaurs_Labyrinth.model;
import java.util.ArrayList;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
public class Dialogue {
	
	
	NPC villager;
	Player PlayerCharacter;
	String NPCDesc;
	String NPCResp;
	String PlayerResp;
	public void initPlayers() {
		//creates an instance of player
		ArrayList<Ability> PlayerAbilities = new ArrayList<Ability>();
		PlayerCharacter = new Player(1000, 20, 1000, 10, 2, 2, 50, 0, PlayerAbilities, "Normal");
		
		//creates an instance of enemy

		ArrayList<Ability> VillagerAbilities = new ArrayList<Ability>();
		villager = new NPC(10, 10, 0, 0, 1, 0, 0, 0, VillagerAbilities, "Villager", "Hello Traveler!", 0, "An old man with tattered clothing", "Villager");
		


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
		NPCResp = "The" + villager.getName() + "says" + villager.getDialogue();
	}
	
}

