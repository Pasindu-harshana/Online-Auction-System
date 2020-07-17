package auction;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fetchdata.DeleteUser;
import fetchdata.Users;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//getting user value from session
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("user");
			
			//calling deleteUser method in DeleteUser class
			DeleteUser.deleteUser(user.getUserID());
			
			request.setAttribute("massage", "User have been removed");
			RequestDispatcher dispathcer = request.getRequestDispatcher("Login.jsp");
			dispathcer.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
