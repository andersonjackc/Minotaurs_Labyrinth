package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Choice {
	//attributes
	int NPC_ID, choice_ID;
	String choice, response, status;
	
	//methods
	public Choice(int NPC_ID, int choice_ID, String choice, String response, String status) {
		this.NPC_ID = NPC_ID;
		this.choice_ID = choice_ID;
		this.choice = choice;
		this.response = response;
		this.status = status;
	}
	
	//getters
	public int getNPC_ID() {
		return this.NPC_ID;
	}
	
	public int getChoice_ID() {
		return this.choice_ID;
	}
	
	public String getChoice() {
		return this.choice;
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	//setters
	public void setNPC_ID(int NPC_ID) {
		this.NPC_ID = NPC_ID;
	}
	
	public void setChoice_ID(int choice_ID) {
		this.choice_ID = choice_ID;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
