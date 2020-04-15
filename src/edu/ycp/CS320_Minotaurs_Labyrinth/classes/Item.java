package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Item {
//attributes
	
protected String description, name, variety, affectedstat;
protected int effect, value;
protected Boolean flammable, lit, throwable;


//methods
public Item(String description, int effect, Boolean flammable,  Boolean lit, Boolean throwable, int value, String name, String variety, String affectedstat) {
	this.description = description;
	this.effect = effect;
	this.flammable = flammable;
	this.lit = lit;
	this.throwable = throwable;
	this.value = value;
	this.name=name;
	this.variety = variety;
	this.affectedstat = affectedstat;
}
public String getName() {
	return name;
}
public String getVariety() {
	return variety;
}
public String getAffectedStat() {
	return affectedstat;
}
public void setAffectedStat(String affectedstat) {
	this.affectedstat = affectedstat;
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
	if(getAffectedStat().equals("HP")) {
	target.setHP(target.getHP() + getEffect());
	if(target.getHP()<=0) {
		target.setIsDead(true);
	}
	} 
	else if(getAffectedStat().equals("maxHP")) {
		target.setMaxHP(target.getMaxHP() + getEffect());

	}
	else if(getAffectedStat().equals("resource")) {
		target.setResource(target.getResource() + getEffect());

	}
	else if(getAffectedStat().equals("maxResource")) {
		target.setMaxResource(target.getMaxResource() + getEffect());

	}
	else if(getAffectedStat().equals("atk")) {
		target.setAtk(target.getAtk() + getEffect());

	}
	else if(getAffectedStat().equals("def")) {
		target.setDef(target.getDef() + getEffect());

	}
}

}
