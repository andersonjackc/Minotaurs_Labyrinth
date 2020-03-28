package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;
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
		controller.setModel(model);
		req.setAttribute("game", model);
		
		//used to persist x and y location on our sample map
		Integer xLoc = getInteger(req, "xLoc");
		Integer yLoc = getInteger(req, "yLoc");
		model.setPosition(xLoc, yLoc);
		
		String inputVal = getString(req, "textbox").toLowerCase();
		
		//check for command, call the move method
		
		if(req.getParameter("textbox") != null && inputVal.equals("north")) {
			model.moveNorth();
		}else if(req.getParameter("textbox") != null && inputVal.equals("south")) {
			model.moveSouth();
		}else if(req.getParameter("textbox") != null && inputVal.equals("west")) {
			model.moveWest();
		}else if(req.getParameter("textbox") != null && inputVal.equals("east")) {
			model.moveEast();
		}else if(req.getParameter("textbox") != null && !(inputVal.equals("north")) && !(inputVal.equals("south")) && !(inputVal.equals("east") && !(inputVal.equals("west")))) {
			model.setError("That command is not recognized!");
		}
		
		if(model.getMap()[0][1] == 1 ) {
			 resp.sendRedirect(req.getContextPath() + "/combat");
		}else if(model.getMap()[1][0] == 1) {
			req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
		}else if(model.getMap()[1][2] == 1) {
			req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
		}else if(model.getMap()[2][1] == 1) {
			 resp.sendRedirect(req.getContextPath() + "/dialogue");
		}else {
			req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
		}
	}

	// gets an Integer from the Posted form data, for the given attribute name
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	
	private String getString(HttpServletRequest req, String name) {
		return String.valueOf(req.getParameter(name));
	}
}
