package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Item {
//attributes
	//add throwable
protected String description, name, variety;
protected int effect, value;
protected Boolean flammable, lit, throwable;


//methods
public Item(String description, int effect, Boolean flammable,  Boolean lit, Boolean throwable, int value, String name, String variety) {
	this.description = description;
	this.effect = effect;
	this.flammable = flammable;
	this.lit = lit;
	this.throwable = throwable;
	this.value = value;
	this.name=name;
	this.variety = variety;
}
public String getName() {
	return name;
}
public String getVariety() {
	return variety;
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

public void addEffect(Actor target)  {
	target.setHP(target.getHP() + getEffect());
	 
}

}
