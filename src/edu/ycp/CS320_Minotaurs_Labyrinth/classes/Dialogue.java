package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Dialogue {
	//attributes
	String question;
	int NPC_ID, choice1_ID, choice2_ID, choice3_ID;

	//methods
	public Dialogue(String question, int NPC_ID, int choice1_ID, int choice2_ID, int choice3_ID) {
		this.question = question;
		this.NPC_ID = NPC_ID;
		this.choice1_ID = choice1_ID;
		this.choice2_ID = choice2_ID;
		this.choice3_ID = choice3_ID;
	}
	
	//getters
	public String getQuestion() {
		return this.question;
	}
	
	public int getNPC_ID() {
		return this.NPC_ID;
	}
	
	public int getChoice1() {
		return this.choice1_ID;
	}
	
	public int getChoice2() {
		return this.choice2_ID;
	}
	
	public int getChoice3() {
		return this.choice3_ID;
	}
	
	//setters
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setNPC_ID(int NPC_ID) {
		this.NPC_ID = NPC_ID;
	}
	
	public void setChoice1(int choice1_ID) {
		this.choice1_ID = choice1_ID;
	}
	
	public void setChoice2(int choice2_ID) {
		this.choice2_ID = choice2_ID;
	}
	
	public void setChoice3(int choice3_ID) {
		this.choice3_ID = choice3_ID;
	}
}
