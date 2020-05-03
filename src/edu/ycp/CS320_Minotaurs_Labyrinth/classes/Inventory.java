
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
	
	public String addItem(Item item) {
		if(inventory.size() == maxStorage) {
			return "Your inventory is full";
		}
		else {
		inventory.add(item);
		return item.getName() + " has been added to inventory.";
		}
	}
	
	public String removeItem(Item item) {
		if(getInventory().contains(item)) {
			inventory.remove(item);
			return "You dropped " + item.getName();
		}
		return "You don't have a " + item.getName();
	}
	
	//getters
	public int getMaxStorage() {
		return maxStorage;
	}
	
	public int getMaxQuant() {
		return maxQuant;
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	//setters
	public void setMaxStorage(int maxStorage) {
		this.maxStorage = maxStorage;
	}
		
	public void setMaxQuant(int maxQuant) {
		this.maxQuant = maxQuant;
	}
		
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
}