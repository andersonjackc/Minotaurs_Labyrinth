package edu.ycp.cs320.Minotaurs_Labyrinth.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Actor;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Gear;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Inventory;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Obstacle;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DatabaseProvider;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DerbyDatabase;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.IDatabase;

public class Minotaur {
	// :(
	String mapString, torqu3String,  djhake;
	int atk, def, hp, resource, gold, xp, torqu3Counter;
	
	public void setMapString(String mapString) {
		this.mapString = mapString;
	}
	
	public String getMapString() {
		return mapString;
	}
	
	public void setTorqu3String(String torqu3String) {
		this.torqu3String = torqu3String;
	}
	
	public String getTorqu3String() {
		return torqu3String;
	}
	public void setdjHake(String djhake) {
		this.djhake = djhake;
	}
	
	public String getdjHake() {
		return djhake;
	}
	public void setTorqu3Counter(int torqu3Counter) {
		this.torqu3Counter=torqu3Counter;
	}
	public int getTorqu3Counter() {
		return torqu3Counter;
	}
	public void setAtk(int atk) {
		this.atk=atk;
	}
	public int getAtk() {
		return atk;
	}
	public void setDef(int def) {
		this.def=def;
	}
	public int getDef() {
		return def;
	}
	public void setHP(int hp) {
		this.hp=hp;
	}
	public int getHP() {
		return hp;
	}
	public void setResource(int Resource) {
		this.resource=Resource;
	}
	public int getResource() {
		return resource;
	}
	public void setGold(int gold) {
		this.gold=gold;
	}
	public int getGold() {
		return gold;
	}
	public void setXP(int xp) {
		this.xp=xp;
	}
	public int getXP() {
		return xp;
	}
	
	//leveling
	public String levelUp(Player player, int PlayerXP, ArrayList<Ability> abilityList) {
		if(player.getXP()>=10 && player.getAbilities().size() <= 1) {
			player.getAbilities().add(abilityList.get(1));
			player.setAtk(player.getAtk()+2);
			player.setDef(player.getDef()+1);
			player.setHP(player.getHP()+5);
			player.setResource(player.getResource()+5);
			return "Level up! You gained the " + player.getAbilities().get(1).getName() + " spell!";
		}
		if(player.getXP()>=20 && player.getAbilities().size() <= 2) {
			player.getAbilities().add(abilityList.get(0));
			player.setAtk(player.getAtk()+2);
			player.setDef(player.getDef()+1);
			player.setHP(player.getHP()+5);
			player.setResource(player.getResource()+5);
			return "Level up! You gained the " + player.getAbilities().get(2).getName() + " spell!";

		}
		if(player.getXP()>=30 && player.getAbilities().size() <= 3) {
			player.getAbilities().add(abilityList.get(6));
			player.setAtk(player.getAtk()+2);
			player.setDef(player.getDef()+1);
			player.setHP(player.getHP()+5);
			player.setResource(player.getResource()+5);
			return "Level up! You gained the " + player.getAbilities().get(3).getName() + " spell!";

		}
		if(player.getXP()>=40 && player.getAbilities().size() <= 4) {
			player.getAbilities().add(abilityList.get(7));
			player.setAtk(player.getAtk()+2);
			player.setDef(player.getDef()+1);
			player.setHP(player.getHP()+5);
			player.setResource(player.getResource()+5);
			return "Level up! You gained the " + player.getAbilities().get(4).getName() + " spell!";
		}
		return "";
	}
}