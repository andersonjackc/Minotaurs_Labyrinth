package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.Comparator;

public class InventoryComparator implements Comparator<Inventory>{

	
	public int compare(Inventory inv1, Inventory inv2) {
		ItemListComparator ILC = new ItemListComparator();
		if(ILC.compare(inv1.getInventory(), inv2.getInventory()) == 1 && inv1.getMaxQuant() == inv2.getMaxQuant() && inv1.getMaxStorage() == inv2.getMaxStorage()) {
			return 1;
		}
		else {
			return -1;		
		}
				
				
	}

	
}
