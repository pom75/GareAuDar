package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.FriendService;

public class AddFriendServlet extends HttpServlet { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1007713300894478018L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user1 = request.getParameter("user1");
		String user2 = request.getParameter("user2");
		response.setContentType("text/plain");
		response.getWriter().println(FriendService.addFriend(user1, user2));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}