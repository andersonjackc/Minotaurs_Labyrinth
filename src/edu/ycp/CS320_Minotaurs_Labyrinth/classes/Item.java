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

	public String addEffect(Actor target)  {
		if(!target.getIsDead()) {
			if(getAffectedStat().equals("HP")) {
				target.setHP(target.getHP() + getEffect());
				if(target.getHP()<=0) {
					target.setIsDead(true);
				}
				return "You used " + getName() + " it did " + Math.abs(getEffect()) + " to " + target.getName() + "'s " +  getAffectedStat() + ", it now has " + target.getHP() + " " +  getAffectedStat() + ".";
			} 
			else if(getAffectedStat().equals("maxHP")) {
				target.setMaxHP(target.getMaxHP() + getEffect());
				return "You used " +  getName() + " it did " + Math.abs( getEffect()) + " to " + target.getName() + "'s " +  getAffectedStat() + ", it now has " + target.getMaxHP() + " " +  getAffectedStat() + ".";
			}
			else if(getAffectedStat().equals("resource")) {
				target.setResource(target.getResource() + getEffect());
				return "You used " +  getName() + " it did " + Math.abs( getEffect()) + " to " + target.getName() + "'s " +  getAffectedStat() + ", it now has " + target.getResource() + " " +  getAffectedStat()+ ".";
	
			}
			else if(getAffectedStat().equals("maxResource")) {
				target.setMaxResource(target.getMaxResource() + getEffect());
				return "You used " +  getName() + " it did " + Math.abs( getEffect()) + " to " + target.getName() + "'s " +  getAffectedStat() + ", it now has " + target.getMaxResource() + " " +  getAffectedStat()+ ".";
	
			}
			else if(getAffectedStat().equals("atk")) {
				target.setAtk(target.getAtk() + getEffect());
				return "You used " +  getName() + " it did " + Math.abs( getEffect()) + " to " + target.getName() + "'s " +  getAffectedStat() + ", it now has " + target.getAtk() + " " +  getAffectedStat()+ ".";
	
			}
			else if(getAffectedStat().equals("def")) {
				target.setDef(target.getDef() + getEffect());
				return "You used " +  getName() + " it did " + Math.abs( getEffect()) + " to " + target.getName() + "'s " +  getAffectedStat() + ", it now has " + target.getDef() + " " +  getAffectedStat()+ ".";
	
			}
			else if(getAffectedStat().equals("none")) {
				return "You used " +  getName() + " it did nothing.";
	
			}
		}
		return "You are dead!"; 
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public String getVariety() {
		return variety;
	}
	
	public String getAffectedStat() {
		return affectedstat;
	}
	
	public int getValue() {
		return value;
	}
	
	public Boolean getThrowable() {
		return throwable;
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
	
	//setters
	public void setAffectedStat(String affectedstat) {
		this.affectedstat = affectedstat;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setValue(int value) {
		this.value=value;
	}

	public void setLit(Boolean lit) {
		
		this.lit = lit;
		
	}
	
	public void setVariety(String variety) {
		this.variety = variety;
	}
	
	public void setThrowable(Boolean throwable) {
		this.throwable = throwable;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setEffect(int effect) {
		this.effect = effect;
	}
	
	public void setFlammable(Boolean flammable) {
		this.flammable = flammable;
	}
}
