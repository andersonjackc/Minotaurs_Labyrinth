package edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.cs320.booksdb.model.BookAuthor;
import edu.ycp.cs320.booksdb.persist.ReadCSV;

public class InitialData {

	public static List<Ability> getAbilities() throws IOException{
		
		
		
		
	}
	
	
	public static List<Enemy> getEnemies() throws IOException {
		List<Enemy> enemyList = new ArrayList<Enemy>();
		ReadCSV readEnemies = new ReadCSV("Enemy.csv");
		try {
			while (true) {
				List<String> tuple = readEnemies.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Enemy enemy = new Enemy(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, false);
				
				enemy.setMaxHP(Integer.parseInt(i.next()));
				enemy.setHP(Integer.parseInt(i.next()));
				enemy.setMaxResource(Integer.parseInt(i.next()));
				enemy.setResource(Integer.parseInt(i.next()));
				enemy.setAtk(Integer.parseInt(i.next()));
				enemy.setDef(Integer.parseInt(i.next()));
				enemy.setGold(Integer.parseInt(i.next()));
				enemy.setXP(Integer.parseInt(i.next()));
				enemy.setAbilities();
				enemy.setStatus();
				enemy.setDialogue();
				enemy.setAttitude(Integer.parseInt(i.next()));
				enemy.setDescription();
				enemy.setName();
				enemy.setInventory();
				enemy.setCurrentRoom(i.next());
				if(Integer.parseInt(i.next())==0) {
					enemy.setIsDead(false);
				}else {
					enemy.setIsDead(true);
				}
			
				}
				
			
			System.out.println("enemyList loaded from CSV file");			
			return enemyList;
		} finally {
			readEnemies.close();
		}
	}
}

