package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.Comparator;

public class GearComparator implements Comparator<Gear>{

	
	public int compare(Gear g1, Gear g2) {
		if(g1.getFlammable() == g2.getFlammable() && g1.getLit() == g2.getLit() && g1.getThrowable() == g2.getThrowable() && g1.getAffectedStat().equals(g2.getAffectedStat()) && g1.getDescription().equals(g2.getDescription()) && g1.getEffect() == g2.getEffect() && g1.getName().equals(g2.getName()) && g1.getValue() == g2.getValue() && g1.getVariety().equals(g2.getVariety()) && g1.getEquipped() == g2.getEquipped() && g1.getAtk() == g2.getAtk() && g1.getDef() == g2.getDef() && g1.getHP() == g2.getHP()) {
			return 1;
		}
		else {
			return -1;
		}
				
	}

	
}
