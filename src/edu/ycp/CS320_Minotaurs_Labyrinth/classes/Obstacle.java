package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Obstacle {
	//attributes
	private String description, status;
	private Item requirement;

	//methods
	public Obstacle(String description, String status, Item requirement) {
		this.description = description;
		this.status = status;
		this.requirement = requirement;
	}
	
	public Boolean checkRequirement(Player player) {
		ItemComparator ic = new ItemComparator();
		for(Item item: player.getInventory().getInventory()) {
			if (ic.compare(item, requirement)==1) {
				return true;
			}
		}
		return false;
}
	
	public Boolean checkStatus(Player player) {
		if(player.getStatus().equals(status)) {
			return true;
		}
		return false;
	}
	
	//getters
	public String getDescription() {
		return description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Item getRequirement() {
		return requirement;
	}
	
	//setters
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setRequirement(Item requirement) {
		this.requirement = requirement;
	}
	
}
