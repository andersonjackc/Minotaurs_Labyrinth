package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Message<String, Int> {
	private int playerAction;
	private String message;
	
	public Message(String message, int playerAction) {
		this.message = message;
		this.playerAction = playerAction;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setPlayerAction(int setPlayerAction) {
		this.playerAction = setPlayerAction;
	}
	
	public int getPlayerAction() {
		return playerAction;
	}
}
