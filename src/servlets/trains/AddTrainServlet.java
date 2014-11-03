package servlets.trains;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.TrainService;

public class AddTrainServlet extends HttpServlet { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String user_id = request.getParameter("user");
		String numT = request.getParameter("numT");
		String date = request.getParameter("date");
		String numG = request.getParameter("numG");
		String term = request.getParameter("term");
		
		response.setContentType("application/json");
		response.getWriter().println(TrainService.addTrain(key, user_id, numT,date,numG, term));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
