package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Account {
	private String email, password;
	private Boolean emailConfirm;
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEmailConfirm() {
		return emailConfirm;
	}
	
	public void setEmailConfirm(Boolean emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
	
	public void logIn(){
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	public void logOut() {
		throw new UnsupportedOperationException("TODO - implement");
	}
}
