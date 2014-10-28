package servlets.social;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.FollowService;

public class RemoveFollowServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2265345633717220569L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String user1 = request.getParameter("user1");
		String user2 = request.getParameter("user2");
		response.setContentType("text/plain");
		response.getWriter().println(FollowService.removeFollow(key,user1, user2));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
