package edu.ycp.cs320.Minotaurs_Labyrinth.model;
import java.util.ArrayList;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.*;
public class Combat {
	int currentPlayerHP;
	int currentEnemyHP;
	String attackMessage;
	String defendMessage;
	Enemy ogre;
	Player PlayerCharacter;
	
	public void initPlayers() {
		//creates an instance of player
		ArrayList<Ability> PlayerAbilities = new ArrayList<Ability>();
		PlayerCharacter = new Player(1000, 20, 1000, 10, 2, 2, 50, 0, PlayerAbilities, "Normal");
		
		//creates an instance of enemy

		ArrayList<Ability> OgreAbilities = new ArrayList<Ability>();
		ogre = new Enemy(10, 10, 0, 0, 1, 0, 0, 0, OgreAbilities, "ogre", "Grr lets fight", 0, "A large ogre with a club, he has a leather tunic", "Ogre");

	}
	
	public void enemyAtk() {
		ogre.basicAttack(PlayerCharacter);
		defendMessage = "Ogre did " + ogre.getAtk() + " You now have " + PlayerCharacter.getHP();
		
	}
	
	public void playerAtk() {
		PlayerCharacter.basicAttack(ogre);
		attackMessage = "You did " + PlayerCharacter.getAtk() + " to " + ogre.getName() + ", it now has " + ogre.getHP() + " HP";
		
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
		ogre.setHP(HP);
	}
	public int getEnemyHP() {
		return ogre.getHP();
	}
}

