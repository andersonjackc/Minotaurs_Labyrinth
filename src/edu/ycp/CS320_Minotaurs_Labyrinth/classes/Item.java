package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Item {
//attributes
	//add throwable
protected String description, name;
protected int effect, value;
protected Boolean flammable, lit, throwable;

//methods
public Item(String description, int effect, Boolean flammable,  Boolean lit, Boolean throwable, int value, String name) {
	this.description = description;
	this.effect = effect;
	this.flammable = flammable;
	this.lit = lit;
	this.throwable = throwable;
	this.value = value;
	this.name=name;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name=name;
}
public int getValue() {
	return value;
}
public Boolean getThrowable() {
	return throwable;
}
public void setValue(int value) {
	this.value=value;
}
public String getDescription() {
	return description;
}

public int getEffect() {
	return effect;
}

public Boolean getFlammable() {
	return flammable;
}

public Boolean getLit() {
	return lit;
}

public void setLit(Boolean lit) {
	if(getFlammable()) {
	this.lit = lit;
	}
}

public void printDescription(){
	System.out.print(description);	
}

public void addEffect() throws Exception {
	
	 throw new Exception("TODO ");
}

}
