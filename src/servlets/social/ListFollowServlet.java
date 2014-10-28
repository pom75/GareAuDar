package servlets.social;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.FollowService;


//Qui Follow l'user 
public class ListFollowServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user2 = request.getParameter("quiJeSuis");
		String user1 = request.getParameter("quiMeSuis");
		
		
		response.setContentType("text/plain");
		response.getWriter().println(FollowService.getFollow(user1,user2));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
