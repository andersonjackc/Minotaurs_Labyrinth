package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Choice {
	//attributes
	int NPC_ID, choice_ID;
	String response;
	
	//methods
	public Choice(int NPC_ID, int choice_ID, String response) {
		this.NPC_ID = NPC_ID;
		this.choice_ID = choice_ID;
		this.response = response;
	}
	
	//getters
	public int getNPC_ID() {
		return this.NPC_ID;
	}
	
	public int getChoice_ID() {
		return this.choice_ID;
	}
	
	public String getResponse() {
		return this.response;
	}
	
	//setters
	public void setNPC_ID(int NPC_ID) {
		this.NPC_ID = NPC_ID;
	}
	
	public void setChoice_ID(int choice_ID) {
		this.choice_ID = choice_ID;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
}
