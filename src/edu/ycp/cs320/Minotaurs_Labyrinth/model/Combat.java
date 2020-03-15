package edu.ycp.cs320.Minotaurs_Labyrinth.model;
import java.util.ArrayList;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.*;
public class Combat {
	int currentPlayerHP;
	int currentEnemyHP;
	
	Enemy ogre;
	Player PlayerCharacter;
	
	public void initPlayers() {
	//creates an instance of player
	ArrayList<Ability> PlayerAbilities = new ArrayList<Ability>();
	Player PlayerCharacter = new Player(1000, 20, 1000, 10, 2, 2, 50, 0, PlayerAbilities, "Normal");
	
	//creates an instance of enemy
	Enemy ogre = new Enemy("Grr lets fight", 0, "A large ogre with a club, he has a leather tunic", "Ogre", 1, 20);
	}
	
	public void enemyAtk() {
		ogre.basicAttack(PlayerCharacter);
	}
	
	public void playerAtk() {
		PlayerCharacter.basicAttack(ogre);
	}
}