package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item>{

	
	public int compare(Item i1, Item i2) {
		if(i1.getFlammable() == i2.getFlammable() && i1.getLit() == i2.getLit() && i1.getThrowable() == i2.getThrowable() && i1.getAffectedStat().equals(i2.getAffectedStat()) && i1.getDescription().equals(i2.getDescription()) && i1.getEffect() == i2.getEffect() && i1.getName().equals(i2.getName()) && i1.getValue() == i2.getValue() && i1.getVariety().equals(i2.getVariety())) {
			return 1;
		}
		else {
			return -1;
		}
				
	}

	
}
