package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

public class Ability {
	//attributes
	private String name, description, variety, affectedstat;
	private int effect, cost;
	
	//methods
	public Ability(String name, String description, String variety, String affectedstat, int effect, int cost) {
		this.name = name;
		this.description = description;
		this.variety = variety;
		this.affectedstat = affectedstat;
		this.effect = effect;
		this.cost = cost;
		

	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getVariety() {
		return variety;
	}
	
	public String getAffectedStat() {
		return affectedstat;
	}
	
	public int getEffect() {
		return effect;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void addEffect(Actor target) {
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
