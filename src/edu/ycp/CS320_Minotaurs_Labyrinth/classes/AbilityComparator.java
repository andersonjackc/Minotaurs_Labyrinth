package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class AbilityComparator implements Comparator<ArrayList<Ability>>{

	
	public int compare(ArrayList<Ability> a1, ArrayList<Ability> a2) {
		int count = 0;
		
		if(a1.size() == a2.size()) {
			for(Ability a : a1) {
				
				if(!(a.getAffectedStat().equals(a2.get(count).getAffectedStat()) && a.getCost() == a2.get(count).getCost() && a.getDescription().equals(a2.get(count).getDescription()) && a.getEffect() == a2.get(count).getEffect() && a.getName().equals(a2.get(count).getName()) && a.getVariety().equals(a2.get(count).getVariety()))) {
					break;
				}else {
					
				}
				
				count++;
			}
		}
		
		if(count == a1.size() && count == a2.size()) {
			return 1;
		}else {
			return -1;
		}
	}

	
}
