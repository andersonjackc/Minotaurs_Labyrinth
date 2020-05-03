package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.activation.CommandMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Ability;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Actor;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Gear;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Item;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.NPC;
import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Pair;
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
		//controller.initModel();
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Message<String, Integer>> outputStrings = (ArrayList<Message<String, Integer>>) db.findTextHistory();
		Pair<Integer, Integer> arraySize = db.findMapArraySize();
		ArrayList<Player> testPlayer = (ArrayList<Player>) db.findAllPlayers();
		Player dbPlayer = testPlayer.get(0);
		ArrayList<Room> roomList = (ArrayList<Room>) db.findAllRooms();
		model.setAtk(dbPlayer.getAtk());
		model.setDef(dbPlayer.getDef());
		model.setHP(dbPlayer.getHP());
		model.setResource(dbPlayer.getResource());
		model.setXP(dbPlayer.getXP());
		model.setGold(dbPlayer.getGold());

		String[][] mapArray = new String[arraySize.getRight()][arraySize.getLeft()];
		String mapString = "";
		for(int i = 0; i < mapArray.length; i++) {
			for(int j = 0; j < mapArray[0].length; j++) {
				mapArray[i][j] = "<span class=closedSquare>■</span>";
			}
		}
		
		for(Room room : roomList) {
			
			Pair<Integer, Integer> coordPair = db.findCoordinates(room.getRoomId());
			
			if(room.getIsFound()) {
				mapArray[coordPair.getRight()][coordPair.getLeft()] = "□";
			}
			
			if(room.getRoomId() == dbPlayer.getCurrentRoom().getRoomId()) {
				mapArray[coordPair.getRight()][coordPair.getLeft()] = "<span class=circle>●</span>";
			}
			
		}
	
		
		for(int i = 0; i < mapArray.length; i++) {
			for(int j = 0; j < mapArray[0].length; j++) {
				mapString += mapArray[i][j];
			}
			mapString += ",";
		}
		model.setMapString(mapString);
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
		
		
		ArrayList<Player> testPlayer = (ArrayList<Player>) db.findAllPlayers();
		ArrayList<Enemy>  enemyList = (ArrayList<Enemy>) db.findAllEnemies();
		ArrayList<NPC> npcList = (ArrayList<NPC>) db.findAllNPCs();
		ArrayList<Item> itemList = (ArrayList<Item>) db.findAllItems();
		ArrayList<Room> roomList = (ArrayList<Room>) db.findAllRooms();
		
		HashMap<String, String> commandMap = new HashMap<String, String>();
		commandMap.put("attack", "-allows you to fight other creatures in the Labyrinth.");
		commandMap.put("talk", "-allows you to talk to other creatures.");
		commandMap.put("move", "-allows you to move between rooms.");
		commandMap.put("jump", "-allows you to jump over obstacles.");
		commandMap.put("crawl", "-allows you to crawl under obstacles.");
		commandMap.put("cast", "-allows you to cast spells or abilities.");
		commandMap.put("leave", "-allows you to leave conversations.");
		commandMap.put("run", "-allows you to flee from a fight.");
		commandMap.put("throw", "-allows you to fight other creatures in the Labyrinth.");
		commandMap.put("take", "-allows you to pick an item up.");
		commandMap.put("drop", "-allows you to drop an item.");
		commandMap.put("barter", "-allows you to barter with npc's.");
		commandMap.put("light", "-allows you to light items on fire.");
		commandMap.put("equip", "-allows you to equip gear.");
		commandMap.put("unequip", "-allows you to unequip gear.");
		commandMap.put("use", "-allows you to use items.");
		commandMap.put("check item", "-allows you to examine an item.");
		commandMap.put("check inventory", "-allows you to check the contents of your inventory.");
		
		
		Player dbPlayer = testPlayer.get(0);
		//model, controller and attribute for jsp setup
		Minotaur model = new Minotaur();
		MinotaursLabyrinthController controller = new MinotaursLabyrinthController();
		
		model.setAtk(dbPlayer.getAtk());
		model.setDef(dbPlayer.getDef());
		model.setHP(dbPlayer.getHP());
		model.setResource(dbPlayer.getResource());
		model.setXP(dbPlayer.getXP());
		model.setGold(dbPlayer.getGold());
		
		Pair<Integer, Integer> arraySize = db.findMapArraySize();
		
		String[][] mapArray = new String[arraySize.getRight()][arraySize.getLeft()];
		
		for(int i = 0; i < mapArray.length; i++) {
			for(int j = 0; j < mapArray[0].length; j++) {
				mapArray[i][j] = "<span class=closedSquare>■</span>";
			}
		}
		
		
		
		//initializing the map/player in the model, eventually will be removed
		HashMap<String, Actor> targets = new HashMap<String, Actor>();
		controller.setModel(model);
		req.setAttribute("game", model);
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
		db.insertIntoTextHistory(input);
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
					db.insertIntoTextHistory(msg);
					db.insertIntoTextHistory(msg2);
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					db.insertIntoTextHistory(msg);
				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					db.insertIntoTextHistory(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
					db.insertIntoTextHistory(msg);

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
					db.insertIntoTextHistory(msg2);

					}
					Message<String, Integer> msg = new Message<String, Integer>(castMsg, 3);
					db.insertIntoTextHistory(msg);
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a spell or ability!", 0);
					db.insertIntoTextHistory(msg);
				}else if(inputs.length<=2){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					db.insertIntoTextHistory(msg);
				}else if(!containsAbility(dbPlayer.getAbilities(), inputs[1])) {
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid spell!", 0);
					db.insertIntoTextHistory(msg);
				}else if(inputs.length > 3) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					db.insertIntoTextHistory(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[2] + " is an invalid target!", 0);
					db.insertIntoTextHistory(msg);

				}
			}
			
			//talk
			else if(req.getParameter("textbox") != null && inputs[0].equals("talk")) {
				if(inputs.length <= 2 && inputs.length > 1 && targets.containsKey(inputs[1]) && dbPlayer.getCurrentRoom().getRoomId() == targets.get(inputs[1]).getCurrentRoom().getRoomId()) {
					if(!(targets.get(inputs[1]).getIsDead()) && !(dbPlayer.getIsDead())) {
						db.findChoicesForNPC(inputs[1]);
						dbPlayer.setStatus("talking");
					}else if(!(dbPlayer.getIsDead())) {
						Message<String, Integer> msg = new Message<String, Integer>("You can't talk to " + targets.get(inputs[1]).getName() + " because it is dead.", 0);
						db.insertIntoTextHistory(msg);
					}else if(dbPlayer.getIsDead()) {
						Message<String, Integer> msg = new Message<String, Integer>("You are dead!", 0);
						db.insertIntoTextHistory(msg);
					}
				}
				else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					db.insertIntoTextHistory(msg);
				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					db.insertIntoTextHistory(msg);
				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
					db.insertIntoTextHistory(msg);
				}
			
			}
			
			//move
			else if (req.getParameter("textbox") != null && (inputs[0].equals("move") || inputs[0].equals("jump") || inputs[0].equals("crawl"))){
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					String moveMsg = "";
					if(inputs[0].equals("move")) {
						moveMsg = dbPlayer.move(inputs[1], roomList);
						moveMsg+= " " + getNPCbyRoomID(dbPlayer.getCurrentRoom().getRoomId(), targets).getDescription();
					}else if(inputs[0].equals("jump")) {
						moveMsg = dbPlayer.jump(inputs[1], roomList);
						moveMsg+= " " + getNPCbyRoomID(dbPlayer.getCurrentRoom().getRoomId(), targets).getDescription();
					}else if(inputs[0].equals("crawl")) {
						moveMsg = dbPlayer.crawl(inputs[1], roomList);
						moveMsg+= " " + getNPCbyRoomID(dbPlayer.getCurrentRoom().getRoomId(), targets).getDescription();
					}
					Message<String, Integer> msg = new Message<String, Integer>(moveMsg, 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a direction!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single direction!", 0);
					db.insertIntoTextHistory(msg);

				}
				
			}
			
			//help
			else if(req.getParameter("textbox") != null && inputs[0].equals("help")) {
				for(String command : commandMap.keySet()) {
					Message<String, Integer> msg = new Message<String, Integer>(command + commandMap.get(command), 0);
					db.insertIntoTextHistory(msg);
				}
			}
			
			//torqu3
			else if(req.getParameter("textbox") != null && inputs[0].equals("torqu3")) {
				model.setTorqu3String("<img class=Vroom src=Vroom.png>");
			}
			
			//throw
			else if(req.getParameter("textbox") != null && inputs[0].equals("torqu3")) {
				model.setTorqu3String("<img class=Vroom src=Vroom.png>");
			}
			
			//take
			else if(req.getParameter("textbox") != null && inputs[0].equals("take")) {
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					if(containsItem(inputs[1], dbPlayer.getCurrentRoom().getInventory().getInventory())) {
						String takeMsg = dbPlayer.take(getItembyName(inputs[1], dbPlayer.getCurrentRoom().getInventory().getInventory()), dbPlayer.getCurrentRoom().getInventory());
						Message<String, Integer> msg = new Message<String, Integer>(takeMsg, 0);
						db.insertIntoTextHistory(msg);
					}else {
						Message<String, Integer> msg = new Message<String, Integer>("You can't take " + inputs[1] + ".", 0);
						db.insertIntoTextHistory(msg);
					}
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify an item!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single item!", 0);
					db.insertIntoTextHistory(msg);

				}
			}
			
			//drop
			else if(req.getParameter("textbox") != null && inputs[0].equals("drop")) {
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					if(containsItem(inputs[1], dbPlayer.getInventory().getInventory())) {
						String takeMsg = dbPlayer.drop(getItembyName(inputs[1], dbPlayer.getCurrentRoom().getInventory().getInventory()));
						Message<String, Integer> msg = new Message<String, Integer>(takeMsg, 0);
						db.insertIntoTextHistory(msg);
					}else {
						Message<String, Integer> msg = new Message<String, Integer>("You can't drop " + inputs[1] + ".", 0);
						db.insertIntoTextHistory(msg);
					}
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify an item!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single item!", 0);
					db.insertIntoTextHistory(msg);

				}
			}
			
			//barter
			else if(req.getParameter("textbox") != null && inputs[0].equals("barter")) {
				model.setTorqu3String("<img class=Vroom src=Vroom.png>");
			}
			
			//light
			else if(req.getParameter("textbox") != null && inputs[0].equals("light")) {
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					if(containsItem(inputs[1], dbPlayer.getInventory().getInventory())) {
						String takeMsg = dbPlayer.light(getItembyName(inputs[1], dbPlayer.getInventory().getInventory()));
						Message<String, Integer> msg = new Message<String, Integer>(takeMsg, 0);
						db.insertIntoTextHistory(msg);
					}else if(containsItem(inputs[1], dbPlayer.getCurrentRoom().getInventory().getInventory())) {
						String takeMsg = dbPlayer.light(getItembyName(inputs[1], dbPlayer.getCurrentRoom().getInventory().getInventory()));
						Message<String, Integer> msg = new Message<String, Integer>(takeMsg, 0);
						db.insertIntoTextHistory(msg);
					}else {
						Message<String, Integer> msg = new Message<String, Integer>("You can't light " + inputs[1] + " on fire.", 0);
						db.insertIntoTextHistory(msg);
					}
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify an item!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single item!", 0);
					db.insertIntoTextHistory(msg);

				}
			}
			
			//equip
			else if(req.getParameter("textbox") != null && inputs[0].equals("equip")) {
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					if(containsItem(inputs[1], dbPlayer.getInventory().getInventory())) {
						String takeMsg = dbPlayer.equip(getGearbyName(inputs[1], dbPlayer.getCurrentRoom().getInventory().getInventory()));
						Message<String, Integer> msg = new Message<String, Integer>(takeMsg, 0);
						db.insertIntoTextHistory(msg);
					}else {
						Message<String, Integer> msg = new Message<String, Integer>("You can't equip " + inputs[1] + ".", 0);
						db.insertIntoTextHistory(msg);
					}
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a piece of gear!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single piece of gear!", 0);
					db.insertIntoTextHistory(msg);

				}			}
			
			//unequip
			else if(req.getParameter("textbox") != null && inputs[0].equals("unequip")) {
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					if(containsItem(inputs[1], dbPlayer.getInventory().getInventory())) {
						String takeMsg = dbPlayer.unequip(getGearbyName(inputs[1], dbPlayer.getCurrentRoom().getInventory().getInventory()));
						Message<String, Integer> msg = new Message<String, Integer>(takeMsg, 0);
						db.insertIntoTextHistory(msg);
					}else {
						Message<String, Integer> msg = new Message<String, Integer>("You can't drop " + inputs[1] + ".", 0);
						db.insertIntoTextHistory(msg);
					}
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify an item!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single item!", 0);
					db.insertIntoTextHistory(msg);

				}			}
			
			//use
			else if(req.getParameter("textbox") != null && inputs[0].equals("use")) {
				model.setTorqu3String("<img class=Vroom src=Vroom.png>");
			}
			
			//check
			else if(req.getParameter("textbox") != null && inputs[0].equals("check")) {
				if(inputs.length <= 2 && inputs.length > 1 && inputs[1] != null) {
					//inv
					if(inputs[1].equals("inventory")) {
						if(dbPlayer.getInventory().getInventory().size() != 0) {
							for(Item i : dbPlayer.getInventory().getInventory()) {
								Message<String, Integer> msg = new Message<String, Integer>(i.getName() + "\n", 0);
								db.insertIntoTextHistory(msg);
							}
						}else {
							Message<String, Integer> msg = new Message<String, Integer>("Your inventory is empty!", 0);
							db.insertIntoTextHistory(msg);
						}
					}
				
					//item
					else if(containsItem(inputs[1], dbPlayer.getInventory().getInventory())) {
						Item i = getItembyName(inputs[1], dbPlayer.getInventory().getInventory());
						Message<String, Integer> msg = new Message<String, Integer>("Affected Stat: " + i.getAffectedStat() + ", Description: " + i.getDescription() + ", Effect: " + i.getEffect() + ", Value: " + i.getValue() + ", Variety: " + i.getVariety(), 0);
						db.insertIntoTextHistory(msg);
					}else {
						Message<String, Integer> msg = new Message<String, Integer>("You can't check " + inputs[1] + ".", 0);
						db.insertIntoTextHistory(msg);
					}
				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a kind of check!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single check!", 0);
					db.insertIntoTextHistory(msg);

				}
			}
			
			//give error for invalid commands
			else if(req.getParameter("textbox") != null && !commandMap.containsKey(inputs[0]) && !inputs[0].equals("torqu3")){
				Message<String, Integer> msg = new Message<String, Integer>(inputs[0] + " is an invalid command!", 0);
				db.insertIntoTextHistory(msg);

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
					db.insertIntoTextHistory(msg);
					db.insertIntoTextHistory(msg2);

				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 2) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					db.insertIntoTextHistory(msg);

				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
					db.insertIntoTextHistory(msg);

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
					db.insertIntoTextHistory(msg2);

					}
					Message<String, Integer> msg = new Message<String, Integer>(castMsg, 3);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length<=1){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a spell or ability!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length<=2){
					Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
					db.insertIntoTextHistory(msg);

				}else if(!containsAbility(dbPlayer.getAbilities(), inputs[1])) {
					Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid spell!", 0);
					db.insertIntoTextHistory(msg);

				}else if(inputs.length > 3) {
					Message<String, Integer> msg = new Message<String, Integer>("Specify a single target!", 0);
					db.insertIntoTextHistory(msg);

				}else{
					Message<String, Integer> msg = new Message<String, Integer>(inputs[2] + " is an invalid target!", 0);
					db.insertIntoTextHistory(msg);

				}
			}
			
			//run
			else if(req.getParameter("textbox") != null && inputs[0].equals("run")) {
				if(inputs.length == 1) {
					String tmp = dbPlayer.run();
					Message<String, Integer> runMsg = new Message<String, Integer>(tmp, 1);
					db.insertIntoTextHistory(runMsg);
					Message<String, Integer> msg = new Message<String, Integer>("You ran away from combat.", 0);
					db.insertIntoTextHistory(msg);
				}else {
					Message<String, Integer> msg = new Message<String, Integer>(inputVal + " is an invalid command.", 0);
					db.insertIntoTextHistory(msg);
				}
			}else {
				Message<String, Integer> msg = new Message<String, Integer>("In order to " + inputVal + " you must run from combat first.", 0);
				db.insertIntoTextHistory(msg);
			}
		}
		
		//talking status
		//THIS IS THE SECOND PHASE OF THE CONVERSATION
		//comment
		else if(dbPlayer.getStatus().equals("talking")) {
			if(req.getParameter("textbox") != null  && isNumeric(inputs[0]) && Integer.parseInt(inputs[0]) > 0 && Integer.parseInt(inputs[0]) < 4) {
				if(inputs.length <= 1) {
						dbPlayer.setStatus(db.findResponse(dbPlayer.getCurrentRoom().getRoomId(), Integer.parseInt(inputs[0])));
				}
			}else if(req.getParameter("textbox") != null && inputs[0].equals("leave") && !dbPlayer.getIsDead()) {
				String tmp = dbPlayer.leave();
				Message<String, Integer> leaveMsg = new Message<String, Integer>(tmp, 1);
				db.insertIntoTextHistory(leaveMsg);
			}
			else if(isNumeric(inputs[0]) && Integer.parseInt(inputs[0]) >= 4) {
					Message<String, Integer> msg = new Message<String, Integer>("Please choose a valid option!", 0);
					db.insertIntoTextHistory(msg);
			}else {
				Message<String, Integer> msg = new Message<String, Integer>("In order to " + inputVal + " you must leave the conversation first.", 0);
				db.insertIntoTextHistory(msg);
			}
		}
		roomList.set(dbPlayer.getCurrentRoom().getRoomId()-1, dbPlayer.getCurrentRoom());
		db.updateRooms(roomList);
		db.updatePlayer(dbPlayer);
		model.setAtk(dbPlayer.getAtk());
		model.setDef(dbPlayer.getDef());
		model.setHP(dbPlayer.getHP());
		model.setResource(dbPlayer.getResource());
		model.setXP(dbPlayer.getXP());
		model.setGold(dbPlayer.getGold());
		db.updateEnemies(enemyList);
		db.updateNPCs(npcList);
		db.updateTextHistory();
		
		for(Room room : roomList) {
			
			Pair<Integer, Integer> coordPair = db.findCoordinates(room.getRoomId());
			
			if(room.getIsFound()) {
				mapArray[coordPair.getRight()][coordPair.getLeft()] = "□";
			}
			
			if(room.getRoomId() == dbPlayer.getCurrentRoom().getRoomId()) {
				mapArray[coordPair.getRight()][coordPair.getLeft()] = "<span class=circle>●</span>";
			}
			
		}
		
		String mapString = "";
		for(int i = 0; i < mapArray.length; i++) {
			for(int j = 0; j < mapArray[0].length; j++) {
				mapString+=mapArray[i][j];
			}
			mapString+= ",";
			
		}
		model.setMapString(mapString);
		//sets our outputstrings value, which is used to persist our past decisions
		req.setAttribute("outputstrings", (ArrayList<Message<String, Integer>>)db.findTextHistory());
		req.setAttribute("game", model);		


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
	
	public NPC getNPCbyRoomID(int roomID, HashMap<String, Actor> targets) {
		for(Actor target : targets.values()) {
			if(target.getClass() != targets.get("player").getClass()) {
				if(target.getCurrentRoom().getRoomId() == roomID) {
					return (NPC) target;
				}
			}
			
		}
		return new NPC(0, 0, 0, 0, 0, 0, 0, 0, null, "", "", 0, "", "", null, null, false);
	}
	
	public Boolean containsItem(String name, ArrayList<Item> playerInv) {
		for(Item i : playerInv) {
			if(i.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Item getItembyName(String name, ArrayList<Item> inv) {
		for(Item i : inv) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	public Gear getGearbyName(String name, ArrayList<Item> inv) {
		for(Item i : inv) {
			if(i.getName().equals(name)) {
				return (Gear) i;
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
	
	
	private String getString(HttpServletRequest req, String name) {
		return String.valueOf(req.getParameter(name));
	}
	
	public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
