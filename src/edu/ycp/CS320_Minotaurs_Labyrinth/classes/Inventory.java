package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import java.util.ArrayList;

public class Inventory {
	//attributes
	private int maxStorage, maxQuant;
	private ArrayList<Item> inventory;

	//methods
	public Inventory(int maxStorage, int maxQuant, ArrayList<Item> inventory) {
		
		this.maxStorage = maxStorage;
		this.maxQuant = maxQuant;
		this.inventory = inventory;
	}
	
	public int getMaxStorage() {
		return maxStorage;
	}
	
	public int getMaxQuant() {
		return maxQuant;
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void addItem(Item item) {
		if(inventory.size() == maxStorage) {
			// print some error saying inventory is full
		}
		else {
		inventory.add(item);
		}
	}
	
	public void removeItem(Item item) {
		inventory.remove(item);
	}
}