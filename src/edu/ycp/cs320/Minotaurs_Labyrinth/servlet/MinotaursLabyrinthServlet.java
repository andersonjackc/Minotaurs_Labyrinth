package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Message;
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

		//set attribute name for jsp
		req.setAttribute("game", model);		
		req.setAttribute("outputstrings", model.getOutputStrings());
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Minotaurs_Labyrinth Servlet: doPost");
		
		//model, controller and attribute for jsp setup
		Minotaur model = new Minotaur();
		MinotaursLabyrinthController controller = new MinotaursLabyrinthController();
		model.initMap();
		model.initPlayer();
		controller.setModel(model);
		req.setAttribute("game", model);
		req.setAttribute("outputstrings", model.getOutputStrings());
		
		//Create an empty list and fill it with the various interactions/descriptions
		ArrayList<Message<String, Integer>> emptyList = new ArrayList<Message<String, Integer>>();
		model.setOutputStrings(emptyList);
		String[] test = req.getParameterValues("test");
		String[] playerAction = req.getParameterValues("playerAction");
		
		int tempCount1 = 0;
		int tempCount2 = 0;
		for(String s: test) {
			tempCount1++;
			tempCount2 = 0;
			for(String x : playerAction) {
				tempCount2++;
				if(tempCount1 == tempCount2) {
					Message<String, Integer> newMsg = new Message<String, Integer>(s, Integer.parseInt(x));
					model.getOutputStrings().add(newMsg);
				}
			}
		}
		String inputVal = getString(req, "textbox");
		String[] inputs;
		Message<String, Integer> input = new Message<String, Integer>(inputVal, 1);
		model.getOutputStrings().add(input);
		inputVal = inputVal.toLowerCase();
		inputs = inputVal.split(" ");
		//grab the hidden persistence values
		Integer location = getInteger(req, "location");
		Integer playerHP = getInteger(req, "playerHP");
		Integer enemyHP = getInteger(req, "enemyHP");
		Integer villagerHP = getInteger(req, "villagerHP");
		Integer ogreIsDead = getInteger(req, "enemyIsDead");
		Integer villagerIsDead = getInteger(req, "villagerIsDead");
		
		//pass the persistence information to the model
		model.setPlayerHP(playerHP);
		model.setEnemyHP(enemyHP);
		model.setVillagerHP(villagerHP);
		model.setEnemyDead(ogreIsDead);
		model.setVillagerDead(villagerIsDead);

		
		//Set ogre/villager to dead if they have 0 hp or less
		if(model.getEnemyDead()==1) {
			model.getEnemy().setIsDead(true);
		}
		if(model.getVillagerDead()==1) {
			model.getVillager().setIsDead(true);
		}
		
		//Use persisted hidden player position value to keep them there upon post
		//System.out.println(location);
		//System.out.println(model.getRoomByRoomId(location).getDescription());
		model.getPlayer().setCurrentRoom(model.getRoomByRoomId(location));
		
		//Checks that textbox isnt empty, if it isnt empty check for our commands
		//if the player enters an unrecognized command, we set the error message
		if (req.getParameter("textbox") != null && inputs[0].equals("attack")){
			if(inputs.length>1 && model.getTargets().get(inputs[1])!=null && model.getPlayer().getCurrentRoom() == model.getTargets().get(inputs[1]).getCurrentRoom() && !inputs[1].equals("player")) {
				String atkMsg = model.getPlayer().basicAttack(model.getTargets().get(inputs[1]));
				String enemyAtkMsg = model.getTargets().get(inputs[1]).basicAttack(model.getPlayer());
				Message<String, Integer> msg = new Message<String, Integer>(atkMsg, 2);
				Message<String, Integer> msg2 = new Message<String, Integer>(enemyAtkMsg, 2);
				model.getOutputStrings().add(msg);
				model.getOutputStrings().add(msg2);
			}else if(inputs.length<=1){
				Message<String, Integer> msg = new Message<String, Integer>("You must specify a target!", 0);
				model.getOutputStrings().add(msg);
			}else{
				Message<String, Integer> msg = new Message<String, Integer>(inputs[1] + " is an invalid target", 0);
				model.getOutputStrings().add(msg);
			}
		}else if(req.getParameter("textbox") != null && inputVal.equals("talk") && model.getPlayer().getCurrentRoom() == model.getRoomByRoomId(3)) {
			if(!(model.getVillager().getIsDead())) {
				model.initResponses();
			}else {
				Message<String, Integer> msg = new Message<String, Integer>("You killed the villager you monster!", 0);
				model.getOutputStrings().add(msg);
			}
		}else if(req.getParameter("textbox") != null && inputVal.equals("north")) {
			model.setError(model.getPlayer().move(inputVal));
			if(model.getError().equals("")) {
				Message<String, Integer> msg = new Message<String, Integer>(model.getPlayer().getCurrentRoom().getDescription(), 0);
				model.getOutputStrings().add(msg);
			}
		}else if(req.getParameter("textbox") != null && inputVal.equals("south")) {
			model.setError(model.getPlayer().move(inputVal));
			if(model.getError().equals("")) {
				Message<String, Integer> msg = new Message<String, Integer>(model.getPlayer().getCurrentRoom().getDescription(), 0);
				model.getOutputStrings().add(msg);
			}
		}else if(req.getParameter("textbox") != null && inputVal.equals("west")) {
			model.setError(model.getPlayer().move(inputVal));
			if(model.getError().equals("")) {
				Message<String, Integer> msg = new Message<String, Integer>(model.getPlayer().getCurrentRoom().getDescription(), 0);
				model.getOutputStrings().add(msg);
			}
		}else if(req.getParameter("textbox") != null && inputVal.equals("east")) {
			model.setError(model.getPlayer().move(inputVal));
			if(model.getError().equals("")) {
				Message<String, Integer> msg = new Message<String, Integer>(model.getPlayer().getCurrentRoom().getDescription(), 0);
				model.getOutputStrings().add(msg);
			}
		}else if(req.getParameter("textbox") != null && !(inputVal.equals("north")) && !(inputVal.equals("south")) && !(inputVal.equals("east") && !(inputVal.equals("west")))) {
			Message<String, Integer> msg = new Message<String, Integer>("Unrecognized Command!", 0);
			model.getOutputStrings().add(msg);
		}
		
		//Used to persist player location
		//System.out.println(model.getPlayer().getCurrentRoom().getRoomId());
		model.setRoomPosition(model.getPlayer().getCurrentRoom().getRoomId());
		
		//sets our outputstrings value, which is used to persist our past decisions
		req.setAttribute("outputstrings", model.getOutputStrings());
		
		//re-post
		req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
		
	}

	// gets an Integer from the Posted form data, for the given attribute name
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	
	private String getString(HttpServletRequest req, String name) {
		return String.valueOf(req.getParameter(name));
	}
}
