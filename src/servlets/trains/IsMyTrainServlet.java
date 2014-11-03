package servlets.trains;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.TrainService;

public class IsMyTrainServlet extends HttpServlet { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String key = request.getParameter("key");
		String date = request.getParameter("date");
		String numT = request.getParameter("numT");
		String numG = request.getParameter("numG");
		
		response.setContentType("application/json");
		response.getWriter().println(TrainService.isMyTrain(key,user_id,date,numT,numG));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}