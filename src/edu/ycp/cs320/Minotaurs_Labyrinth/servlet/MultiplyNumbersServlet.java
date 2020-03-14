package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Minotaurs_Labyrinth.controller.AddNumbersController;
import edu.ycp.cs320.Minotaurs_Labyrinth.controller.MultiplyNumbersController;
import edu.ycp.cs320.Minotaurs_Labyrinth.model.Numbers;

public class MultiplyNumbersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("MultiplyNumbers Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		System.out.println("AddNumbers Servlet: doPost");
		
		//if user pushes index throw them back to index
		if (req.getParameter("Index") != null) {
			System.out.println("doGet Index");
			resp.sendRedirect("/Minotaurs_Labyrinth/_view/index.jsp");
		}
		// create GuessingGame model - model does not persist between requests
		// must recreate it each time a Post comes in 
		Numbers model = new Numbers();

		// create GuessingGame controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		MultiplyNumbersController controller = new MultiplyNumbersController();
				
		// assign model reference to controller so that controller can access model
		controller.setModel(model);	
		
		// decode POSTed form parameters and dispatch to controller
		try {
			//Set users given parameters to variables
			Double first = getDoubleFromParameter(req.getParameter("first"));
			Double second = getDoubleFromParameter(req.getParameter("second"));
			Double third = getDoubleFromParameter(req.getParameter("third"));


			// check for errors in the form data before using is in a calculation (if its empty)
			if (first == null || second == null || third == null) {
				//These are STRINGS as we cannot save non doubles as doubles
				// We save them in order to send back the invalid
				//Doubles inside the page boxes
				model.setErrorfirst(req.getParameter("first"));
				model.setErrorsecond(req.getParameter("second"));
				model.setErrorthird(req.getParameter("third"));
				
				//Sets the error to missing one or more of the 3 doubles
				model.setNumbs();
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				//calls model.setFirst, setSecond, and setThird to store
				//the given variables in the model
				controller.setAll(first, second, third);
				
				//calls the controller, to call the model to calculates the product of the 3 numbers
				controller.MultiplyResult();
			}
		} catch (NumberFormatException e) {
			
			//does the same as above, same all 3 as strings
			//so we can send them back even if theyre invalid
			model.setErrorfirst(req.getParameter("first"));
			model.setErrorsecond(req.getParameter("second"));
			model.setErrorthird(req.getParameter("third"));
			model.setInvalid();
		}
		
		req.setAttribute("game", model);		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
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
