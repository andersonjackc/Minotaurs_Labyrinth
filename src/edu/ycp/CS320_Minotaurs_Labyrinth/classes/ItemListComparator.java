package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class ItemListComparator implements Comparator<ArrayList<Item>>{

	
	public int compare(ArrayList<Item> i1, ArrayList<Item> i2) {
		int count = 0;
		ItemComparator IC = new ItemComparator();
		
		if(i1.size() == i2.size()) {
			for(Item i : i1) {
				
				if(!(IC.compare(i, i2.get(count)) == 1)) {
					break;
				}else {
					
				}
				
				count++;
			}
		}
		
		if(count == i1.size() && count == i2.size()) {
			return 1;
		}else {
			return -1;
		}
	}

	
}