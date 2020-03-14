package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Minotaurs_Labyrinth.controller.MinotaursLabyrinthController;
import edu.ycp.cs320.Minotaurs_Labyrinth.model.Minotaur;

public class MinotaursLabyrinthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Minotaurs_Labyrinth Servlet: doGet");	
		
		Minotaur model = new Minotaur();

		MinotaursLabyrinthController controller = new MinotaursLabyrinthController();
		
		controller.setModel(model);
		
		controller.initModel();

		req.setAttribute("game", model);		

		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Minotaurs_Labyrinth Servlet: doPost");
		
		//if user pushes index throw them back to index
		if (req.getParameter("Index") != null) {
			System.out.println("doGet Index");
			resp.sendRedirect("/Minotaurs_Labyrinth/_view/index.jsp");
		}
		
		// create GuessingGame model - model does not persist between requests
		// must recreate it each time a Post comes in 
		Minotaur model = new Minotaur();

		// create GuessingGame controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		MinotaursLabyrinthController controller = new MinotaursLabyrinthController();
				
		// assign model reference to controller so that controller can access model
		controller.setModel(model);
		
		controller.initModel();
		
		req.setAttribute("game", model);	
		
		if (req.getParameter("North") != null || req.getParameter("South") != null 
				|| req.getParameter("West") != null || req.getParameter("East") != null) {
				if(req.getParameter("North") != null) {
					model.moveNorth();
				}
				if(req.getParameter("South") != null) {
					model.moveSouth();
				}
				if(req.getParameter("West") != null) {
					model.moveWest();
				}
				if(req.getParameter("East") != null) {
					model.moveEast();
				}
		}
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/minotaursLabyrinth.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s
	private Double getDoubleFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Double.parseDouble(s);
		}
	}
}
