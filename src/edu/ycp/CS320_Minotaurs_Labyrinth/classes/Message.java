package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Message<String, Int> {
	private int playerAction;
	private String message;
	
	public Message(String message, int playerAction) {
		this.message = message;
		this.playerAction = playerAction;
	}
	
	//getters
	public int getPlayerAction() {
		return playerAction;
	}
	
	public String getMessage() {
		return message;
	}
	
	//setters
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setPlayerAction(int setPlayerAction) {
		this.playerAction = setPlayerAction;
	}
	
	
}
