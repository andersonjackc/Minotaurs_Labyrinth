package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.Comparator;
import java.util.List;

public class ItemListComparator implements Comparator<List<Item>>{

	
	public int compare(List<Item> list, List<Item> inner) {
		int count = 0;
		ItemComparator IC = new ItemComparator();
		
		if(list.size() == inner.size()) {
			for(Item i : list) {
				
				if(!(IC.compare(i, inner.get(count)) == 1)) {
					break;
				}else {
					
				}
				
				count++;
			}
		}
		
		if(count == list.size() && count == inner.size()) {
			return 1;
		}else {
			return -1;
		}
	}

	
}