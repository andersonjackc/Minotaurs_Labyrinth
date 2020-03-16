package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Minotaurs_Labyrinth.controller.CombatController;
import edu.ycp.cs320.Minotaurs_Labyrinth.model.Combat;

public class CombatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Combat Servlet: doGet");	
		
		//model and controller setup
		Combat model = new Combat();
		CombatController controller = new CombatController();
		controller.setModel(model);
		
		//fills map 
		controller.initModel();

		//set attribute name for jsp
		req.setAttribute("game", model);		

		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/combat.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Combat Servlet: doPost");
		
		//model, controller and attribute for jsp setup
		Combat model = new Combat();
		CombatController controller = new CombatController();
		controller.setModel(model);
		req.setAttribute("game", model);
		

		//used to persist health
		model.initPlayers();
		Integer playerHP = getInteger(req, "playerHP");
		Integer enemyHP = getInteger(req, "enemyHP");
		model.setPlayerHP(playerHP);
		model.setEnemyHP(enemyHP);


		
		if (req.getParameter("Attack") != null){
			model.playerAtk();
			model.enemyAtk();
		}
		
		// Forward to view to render the result HTML document
		if(model.getEnemyHP() > 0) {
			req.getRequestDispatcher("/_view/combat.jsp").forward(req, resp);
		}else if(model.getEnemyHP() <= 0) {
			resp.sendRedirect(req.getContextPath() + "/minotaursLabyrinth");
		}
		
		
	}

	// gets an Integer from the Posted form data, for the given attribute name
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
}
