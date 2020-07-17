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

import fetchdata.UserDAO;
import fetchdata.Users;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user input value form Login.jsp 
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//create userDAO object to access to registration details of a user using UserDAO class
		UserDAO userDAO = new UserDAO();
		
		try {
			Users user = userDAO.checkUser(name, password);
			String destPage = "Login.jsp";
			if (user != null){
				//Validating the user
				if(name.compareTo(user.getName())==0){
					if (password.compareTo(user.getPassword())==0){
						HttpSession session = request.getSession();
						session.setAttribute("user", user);
						destPage = "view2";
					}
					else{
						request.setAttribute("massage2", "Invalid password!!!");
						RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
						dispatcher.forward(request, response);
	
					}
				}
				else{
					request.setAttribute("massage3", "Invalid User Name!!!");
				}
			}
			
			else{
				request.setAttribute("massage", "Invalid User Name or Password!!!");
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
