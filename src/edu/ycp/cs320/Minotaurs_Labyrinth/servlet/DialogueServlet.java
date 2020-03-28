package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Minotaurs_Labyrinth.controller.DialogueController;
import edu.ycp.cs320.Minotaurs_Labyrinth.model.Dialogue;

public class DialogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Dialogue Servlet: doGet");	
		
		//model and controller setup
		Dialogue model = new Dialogue();
		DialogueController controller = new DialogueController();
		controller.setModel(model);
		
		//fills map 
		controller.initModel();

		//set attribute name for jsp
		req.setAttribute("game", model);		

		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/dialogue.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Dialogue Servlet: doPost");
		
		//model, controller and attribute for jsp setup
		Dialogue model = new Dialogue();
		DialogueController controller = new DialogueController();
		controller.setModel(model);
		req.setAttribute("game", model);
		

		//used to persist health
		model.initPlayers();
		

		model.getNPCDesc();
		
		String inputVal = getString(req, "textbox").toLowerCase();
		
		if (req.getParameter("textbox") != null && inputVal.equals("talk") ){
			model.initResponses();
		}else if(req.getParameter("textbox") != null && !(inputVal.equals("talk")) && !(inputVal.equals("leave"))) {
			model.setError("That command is not recognized!");
		}
		
		if(req.getParameter("textbox") != null && inputVal.equals("leave")) {
			resp.sendRedirect(req.getContextPath() + "/minotaursLabyrinth");
		}else {
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/dialogue.jsp").forward(req, resp);
		}
		
	}
	
	private String getString(HttpServletRequest req, String name) {
		return String.valueOf(req.getParameter(name));
	}
	
}
