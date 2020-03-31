package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
		
		String inputVal = getString(req, "textbox").toLowerCase();
		
		Integer location = getInteger(req, "location");
		
		Integer playerHP = getInteger(req, "playerHP");
		Integer enemyHP = getInteger(req, "enemyHP");
		Integer villagerHP = getInteger(req, "villagerHP");
		Integer ogreIsDead = getInteger(req, "enemyIsDead");
		Integer villagerIsDead = getInteger(req, "villagerIsDead");
		model.setPlayerHP(playerHP);
		model.setEnemyHP(enemyHP);
		model.setVillagerHP(villagerHP);
		model.setEnemyDead(ogreIsDead);
		model.setVillagerDead(villagerIsDead);

		
		if(model.getEnemyDead()==1) {
			model.getEnemy().setIsDead(true);
		}
		if(model.getVillagerDead()==1) {
			model.getVillager().setIsDead(true);
		}
		
		
		if(location==0) {
			model.getPlayer().setCurrentRoom(model.getNorthRoom());
		}else if(location==1) {
			model.getPlayer().setCurrentRoom(model.getSouthRoom());
		}else if(location==2) {
			model.getPlayer().setCurrentRoom(model.getEastRoom());
		}else if(location==3) {
			model.getPlayer().setCurrentRoom(model.getWestRoom());
		}else if(location==4) {
			model.getPlayer().setCurrentRoom(model.getCenterRoom());
		}
		//check for command, call the move method
		if (req.getParameter("textbox") != null && inputVal.equals("attack")){
			if(model.getPlayer().getCurrentRoom() == model.getNorthRoom()) {
			model.playerAtk();
			model.enemyAtk();
			}else if (model.getPlayer().getCurrentRoom() == model.getSouthRoom()) {
				model.playerAtkVillager();
				model.enemyAtkVillager();
			}
		}else if(req.getParameter("textbox") != null && inputVal.equals("talk")) {
			model.initResponses();
		}else if(req.getParameter("textbox") != null && inputVal.equals("north")) {
			model.setError(model.getPlayer().move(inputVal, model.getMap()));
		}else if(req.getParameter("textbox") != null && inputVal.equals("south")) {
			model.setError(model.getPlayer().move(inputVal, model.getMap()));
		}else if(req.getParameter("textbox") != null && inputVal.equals("west")) {
			model.setError(model.getPlayer().move(inputVal, model.getMap()));
		}else if(req.getParameter("textbox") != null && inputVal.equals("east")) {
			model.setError(model.getPlayer().move(inputVal, model.getMap()));
		}else if(req.getParameter("textbox") != null && !(inputVal.equals("north")) && !(inputVal.equals("south")) && !(inputVal.equals("east") && !(inputVal.equals("west")))) {
			model.setError("That command is not recognized!");
		}
		
		if(model.getPlayer().getCurrentRoom() == model.getNorthRoom()) {
			model.setRoomPosition(0);
		}else if(model.getPlayer().getCurrentRoom() == model.getSouthRoom()) {
			model.setRoomPosition(1);
		}else if(model.getPlayer().getCurrentRoom() == model.getEastRoom()) {
			model.setRoomPosition(2);
		}else if(model.getPlayer().getCurrentRoom() == model.getWestRoom()) {
			model.setRoomPosition(3);
		}else if(model.getPlayer().getCurrentRoom() == model.getCenterRoom()) {
			model.setRoomPosition(4);
		}
		
		
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
