package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.Comparator;

public class AbilityComparator implements Comparator<Ability>{

	
	public int compare(Ability a1, Ability a2) {
		if(a1.getAffectedStat().equals(a2.getAffectedStat()) && a1.getCost() == a2.getCost() && a1.getDescription().equals(a2.getDescription()) && a1.getEffect() == a2.getEffect() && a1.getName().equals(a2.getName()) && a1.getVariety().equals(a2.getVariety())) {
			return 1;
		}
		else {
			return -1;	
		}
		
	}

}
