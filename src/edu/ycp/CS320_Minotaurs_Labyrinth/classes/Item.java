package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Item {
//attributes
protected String description;
protected int effect;
protected Boolean flammable, lit;

//methods
public Item(String description, int effect, Boolean flammable,  Boolean lit) {
	this.description = description;
	this.effect = effect;
	this.flammable = flammable;
	this.lit = lit;
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
