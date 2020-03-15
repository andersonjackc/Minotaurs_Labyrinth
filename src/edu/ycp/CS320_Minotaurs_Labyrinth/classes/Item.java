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
	
	 throw new Exception("TODO ");
}

}
