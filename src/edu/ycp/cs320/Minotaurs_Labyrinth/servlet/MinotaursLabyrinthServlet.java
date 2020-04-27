package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Actor;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Player;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Room;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.IDatabase;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DerbyDatabase;
import edu.ycp.CS320_Minotaurs_Labyrinth.labyrinthdb.persist.DatabaseProvider;
import edu.ycp.cs320.Minotaurs_Labyrinth.controller.MinotaursLabyrinthController;
import edu.ycp.cs320.Minotaurs_Labyrinth.model.Minotaur;

public class MinotaursLabyrinthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Minotaurs_Labyrinth Servlet: doGet");	
		
		//model and controller setup
		Minotaur model = new Minotaur();
		MinotaursLabyrinthController controller = new MinotaursLabyrinthController();
		controller.setModel(model);
		
		//fills map 
		controller.initModel();
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Message<String, Integer>> outputStrings = (ArrayList<Message<String, Integer>>) db.findTextHistory();
		//set attribute name for jsp
		req.setAttribute("game", model);		
		req.setAttribute("outputstrings", outputStrings);
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Minotaurs_Labyrinth Servlet: doPost");
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Message<String, Integer>> outputStrings = (ArrayList<Message<String, Integer>>) db.findTextHistory();
		
		ArrayList<Player> testPlayer = (ArrayList<Player>) db.findAllPlayers();
		ArrayList<Enemy>  enemyList = (ArrayList<Enemy>) db.findAllEnemies();
		ArrayList<NPC> npcList = (ArrayList<NPC>) db.findAllNPCs();
		ArrayList<Item> itemList = (ArrayList<Item>) db.findAllItems();
		ArrayList<Room> roomList = (ArrayList<Room>) db.findAllRooms();
		Player dbPlayer = testPlayer.get(0);
		//model, controller and attribute for jsp setup
		Minotaur model = new Minotaur();
		MinotaursLabyrinthController controller = new MinotaursLabyrinthController();
		
		
		//initializing the map/player in the model, eventually will be removed
		HashMap<String, Actor> targets = new HashMap<String, Actor>();
		controller.setModel(model);
		req.setAttribute("game", model);
		req.setAttribute("outputstrings", outputStrings);
		targets.put("player", dbPlayer);

		
		for(Enemy enemy : enemyList) {
			targets.put(enemy.getName().toLowerCase(), enemy);
		}
		for(NPC npc : npcList) {
			targets.put(npc.getName().toLowerCase(), npc);
		}
		
		String inputVal = getString(req, "textbox");
		String[] inputs;
		Message<String, Integer> input = new Message<String, Integer>(inputVal, 1);
		outputStrings.add(input);
		inputVal = inputVal.toLowerCase();
		inputs = inputVal.split(" ");	
		
		if(dbPlayer.getStatus().equals("normal")) {
			if (req.getParameter("textbox") != null && inputs[0].equals("attack")){
				if(inputs.length <= 2 && inputs.length > 1 && targets.get(inputs[1])!=null && dbPlayer.getCurrentRoom().getRoomId() == targets.get(inputs[1]).getCurrentRoom().getRoomId() && !inputs[1].equals("player")) {
					String atkMsg = dbPlayer.basicAttack(targets.get(inputs[1]));
					if(atkMsg.equals(targets.get(inputs[1]).getName() + " is dead.")) {
						if(targets.get(inputs[1]).getName().equals("Villager")) {
							
							
						}else if(targets.get(inputs[1]).getName().equals("ogre")) {
							
						}
					}
					String enemyAtkMsg = targets.get(inputs[1]).basicAttack(dbPlayer);
					if(dbPlayer.getIsDead()) {
						
					}
					Message<String, Integer> msg = new Message<String, Integer>(atkMsg, 2);
					Message<String, Integer> msg2 = new Message<String, Integer>(enemyAtkMsg, 2);
					outputStrings.add(msg);
					outputStrings.add(msg2);
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					outputStrings.add(msg);
				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					outputStrings.add(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
					outputStrings.add(msg);
				}
				
			}
			
			//cast
			else if (req.getParameter("textbox") != null && inputs[0].equals("cast")){
				if(inputs.length <= 3 && inputs.length > 2 && targets.get(inputs[2]) != null && dbPlayer.getCurrentRoom().getRoomId() == targets.get(inputs[2]).getCurrentRoom().getRoomId() && containsAbility(dbPlayer.getAbilities(), inputs[1])) {
					String castMsg = dbPlayer.cast(targets.get(inputs[2]), getAbilitybyString(dbPlayer.getAbilities(), inputs[1]));
					if(castMsg.equals(targets.get(inputs[2]).getName() + " is dead.")) {
						if(targets.get(inputs[2]).getName().equals("Villager")) {
							
							
						}else if(targets.get(inputs[2]).getName().equals("ogre")) {
							
						}else if(targets.get(inputs[2]).getName().equals("player")) {
							
						}
					}
					if(!inputs[2].equals(dbPlayer.getName())) {
					String enemyAtkMsg = targets.get(inputs[2]).basicAttack(dbPlayer);
					if(dbPlayer.getIsDead()) {
						
					}
					Message<String, Integer> msg2 = new Message<String, Integer>(enemyAtkMsg, 2);
					outputStrings.add(msg2);
					}
					Message<String, Integer> msg = new Message<String, Integer>(castMsg, 3);
					outputStrings.add(msg);
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a spell or ability!", 0);
					outputStrings.add(msg);
				}else if(inputs.length<=2){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					outputStrings.add(msg);
				}else if(!containsAbility(dbPlayer.getAbilities(), inputs[1])) {
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid spell!", 0);
					outputStrings.add(msg);
				}else if(inputs.length > 3) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					outputStrings.add(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[2] + " is an invalid target!", 0);
					outputStrings.add(msg);
				}
			}
			
			//talk
			else if(req.getParameter("textbox") != null && inputs[0].equals("talk")) {
				if(inputs.length <= 2 && inputs.length > 1 && dbPlayer.getCurrentRoom().getRoomId() == targets.get(inputs[1]).getCurrentRoom().getRoomId()) {
					if(!(targets.get(inputs[1]).getIsDead()) && !(dbPlayer.getIsDead())) {
						String tmp = dbPlayer.talk(targets.get(inputs[1]));
						Message<String, Integer> talkMsg = new Message<String, Integer>(tmp, 0);
						outputStrings.add(talkMsg);
					}else if(!(dbPlayer.getIsDead())) {
						Message<String, Integer> msg = new Message<String, Integer>("You can't talk to " + targets.get(inputs[1]).getName() + " because it is dead.", 0);
						outputStrings.add(msg);
					}else if(dbPlayer.getIsDead()) {
						Message<String, Integer> msg = new Message<String, Integer>("You are dead!", 0);
						outputStrings.add(msg);
					}
				}
				else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					outputStrings.add(msg);
				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					outputStrings.add(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
					outputStrings.add(msg);
				}
			
			}
			
			//move
			else if (req.getParameter("textbox") != null && inputs[0].equals("move")){
				
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					String moveMsg = dbPlayer.move(inputs[1], roomList);
					Message<String, Integer> msg = new Message<String, Integer>(moveMsg, 0);
					outputStrings.add(msg);
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a direction!", 0);
					outputStrings.add(msg);
				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single direction!", 0);
					outputStrings.add(msg);
				}
				
			}
			
			//give error for invalid commands
			else if(req.getParameter("textbox") != null && !inputs[0].equals("attack") && !inputs[0].equals("talk") && !inputs[0].equals("cast") && !inputs[0].equals("move")){
				Message<String, Integer> msg = new Message<String, Integer>(inputs[0] + " is an invalid command!", 0);
				outputStrings.add(msg);
			}
		}
		
		//combat status
		else if(dbPlayer.getStatus().equals("combat")) {
			if (req.getParameter("textbox") != null && inputs[0].equals("attack")){
				if(inputs.length <= 2 && inputs.length > 1 && targets.get(inputs[1])!=null && dbPlayer.getCurrentRoom().getRoomId() == targets.get(inputs[1]).getCurrentRoom().getRoomId() && !inputs[1].equals("player")) {
					String atkMsg = dbPlayer.basicAttack(targets.get(inputs[1]));
					if(atkMsg.equals(targets.get(inputs[1]).getName() + " is dead.")) {
						if(targets.get(inputs[1]).getName().equals("Villager")) {
							
							
						}else if(targets.get(inputs[1]).getName().equals("ogre")) {
							
						}
					}
					String enemyAtkMsg = targets.get(inputs[1]).basicAttack(dbPlayer);
					if(dbPlayer.getIsDead()) {
						
					}
					Message<String, Integer> msg = new Message<String, Integer>(atkMsg, 2);
					Message<String, Integer> msg2 = new Message<String, Integer>(enemyAtkMsg, 2);
					outputStrings.add(msg);
					outputStrings.add(msg2);
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					outputStrings.add(msg);
				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					outputStrings.add(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
					outputStrings.add(msg);
				}
				
			}
			
			//cast
			else if (req.getParameter("textbox") != null && inputs[0].equals("cast")){
				if(inputs.length <= 3 && inputs.length > 2 && targets.get(inputs[2]) != null && dbPlayer.getCurrentRoom().getRoomId() == targets.get(inputs[2]).getCurrentRoom().getRoomId() && containsAbility(dbPlayer.getAbilities(), inputs[1])) {
					String castMsg = dbPlayer.cast(targets.get(inputs[2]), getAbilitybyString(dbPlayer.getAbilities(), inputs[1]));
					if(!inputs[2].equals(dbPlayer.getName())) {
					String enemyAtkMsg = targets.get(inputs[2]).basicAttack(dbPlayer);
					if(dbPlayer.getIsDead()) {
						
					}
					Message<String, Integer> msg2 = new Message<String, Integer>(enemyAtkMsg, 2);
					outputStrings.add(msg2);
					}
					Message<String, Integer> msg = new Message<String, Integer>(castMsg, 3);
					outputStrings.add(msg);
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a spell or ability!", 0);
					outputStrings.add(msg);
				}else if(inputs.length<=2){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					outputStrings.add(msg);
				}else if(!containsAbility(dbPlayer.getAbilities(), inputs[1])) {
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid spell!", 0);
					outputStrings.add(msg);
				}else if(inputs.length > 3) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					outputStrings.add(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[2] + " is an invalid target!", 0);
					outputStrings.add(msg);
				}
			}
			
			//run
			else if(req.getParameter("textbox") != null && inputs[0].equals("run")) {
				if(inputs.length == 1) {
					String tmp = dbPlayer.run();
					Message<String, Integer> runMsg = new Message<String, Integer>(tmp, 1);
					outputStrings.add(runMsg);
					Message<String, Integer> msg = new Message<String, Integer>("You ran away from combat.", 0);
					outputStrings.add(msg);
				}else {
					Message<String, Integer> msg = new Message<String, Integer>(inputVal + " is an invalid command.", 0);
					outputStrings.add(msg);
				}
			}else {
				Message<String, Integer> msg = new Message<String, Integer>("In order to " + inputs[0] + " you must run from combat first.", 0);
				outputStrings.add(msg);
			}
		}
		
		//talk status
		else if(dbPlayer.getStatus().equals("talking")) {
			//talk
			if(req.getParameter("textbox") != null && inputs[0].equals("talk")) {
				if(inputs.length <= 2 && inputs.length > 1 && dbPlayer.getCurrentRoom().getRoomId() == targets.get(inputs[1]).getCurrentRoom().getRoomId()) {
					if(!(targets.get(inputs[1]).getIsDead()) && !(dbPlayer.getIsDead())) {
						String tmp = dbPlayer.talk(targets.get(inputs[1]));
						Message<String, Integer> talkMsg = new Message<String, Integer>(tmp, 0);
						outputStrings.add(talkMsg);
					}else if(!(dbPlayer.getIsDead())) {
						Message<String, Integer> msg = new Message<String, Integer>("You can't talk to " + targets.get(inputs[1]).getName() + " because it is dead.", 0);
						outputStrings.add(msg);
					}else if(dbPlayer.getIsDead()) {
						Message<String, Integer> msg = new Message<String, Integer>("You are dead!", 0);
						outputStrings.add(msg);
					}
				}
				else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					outputStrings.add(msg);
				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					outputStrings.add(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
					outputStrings.add(msg);
				}
			
			}else if(req.getParameter("textbox") != null && inputs[0].equals("leave") && !dbPlayer.getIsDead()) {
				String tmp = dbPlayer.leave();
				Message<String, Integer> leaveMsg = new Message<String, Integer>(tmp, 1);
				outputStrings.add(leaveMsg);
					
			}else {
				Message<String, Integer> msg = new Message<String, Integer>("In order to " + inputs[0] + " you must leave the conversation first.", 0);
				outputStrings.add(msg);
			}
			
			
		}

		db.updateRooms(roomList);

		db.updatePlayer(dbPlayer);
		db.updateTextHistory(outputStrings);
		db.updateEnemies(enemyList);
		db.updateNPCs(npcList);

		//sets our outputstrings value, which is used to persist our past decisions
		req.setAttribute("outputstrings", outputStrings);
		
		//re-post
		req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
		
	}
	
	//helper method
	public Ability getAbilitybyString(ArrayList<Ability> abilities, String name) {
		for(int i = 0; i < abilities.size(); i++) {
			if(abilities.get(i).getName().equals(name)) {
				return abilities.get(i);
			}
		}
		return null;
	}
	
	public Boolean containsAbility(ArrayList<Ability> abilities, String name) {
		for(int i = 0; i < abilities.size(); i++) {
			if(abilities.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	// gets an Integer from the Posted form data, for the given attribute name
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	
	private String getString(HttpServletRequest req, String name) {
		return String.valueOf(req.getParameter(name));
	}
}
