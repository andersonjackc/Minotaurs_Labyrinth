package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Item {
//attributes
protected String description;
protected int effect;

//methods
public Item(String description, int effect) {
	this.description = description;
	this.effect = effect;
}

public String getDescription() {
	return description;
}

public int getEffect() {
	return effect;
}

public void printDescription(){
	System.out.print(description);	
}

public void addEffect() throws Exception {
	//this needs some kind of logic and or us thinking about how we want to apply the effect of items
	//for example if its a potion that heals versus a potion that buffs attack
	 throw new Exception("TODO");
}

}
