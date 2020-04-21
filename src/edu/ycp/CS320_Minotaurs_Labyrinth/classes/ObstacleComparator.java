package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.Comparator;

public class ObstacleComparator implements Comparator<Obstacle>{

	
	public int compare(Obstacle obs1, Obstacle obs2) {
		ItemComparator IC = new ItemComparator();

		if(IC.compare(obs1.getRequirement(), obs2.getRequirement()) == 1 && obs1.getDescription().equals(obs2.getDescription()) && obs1.getStatus().equals(obs2.getStatus())) {
			return 1;		
		}
		else {
			return -1;		
		}
				
				
	}

	
}
