package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Account {
	//attributes
	private String email, password;
	private Boolean emailConfirm;
	
	//methods
	public Account() {
		
	}
	
	public void logIn(){
		throw new UnsupportedOperationException("TODO -  implement");
	}
	
	public void logOut() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	//getters
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Boolean getEmailConfirm() {
		return emailConfirm;
	}
	
	//setters
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmailConfirm(Boolean emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
	
	
}
