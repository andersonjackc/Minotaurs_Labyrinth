package edu.ycp.cs320.Minotaurs_Labyrinth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Minotaurs_Labyrinth.controller.GuessingGameController;
import edu.ycp.cs320.Minotaurs_Labyrinth.model.GuessingGame;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");

		//if the addnumbs param isnt empty throw user addnums jsp
		if (req.getParameter("AddNumbers") != null) {
			System.out.println("doGet addNumbers");
			resp.sendRedirect("/Minotaurs_Labyrinth/_view/addNumbers.jsp");
		}
		//if multnumbers param isnt empty throw user multiplynumbers jsp
		if (req.getParameter("MultiplyNumbers") != null) {
			System.out.println("doGet multiplyNumbers");
			resp.sendRedirect("/Minotaurs_Labyrinth/_view/multiplyNumbers.jsp");
		}
		//if guessing game isnt empty throw user guessinggame jsps
		if (req.getParameter("GuessingGame") != null) {
			System.out.println("doGet guessingGame");
			resp.sendRedirect("/Minotaurs_Labyrinth/_view/guessingGame.jsp");
		}
	}
}