package auction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/LogoutServlet2")
public class LogoutServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			//removing seller object from the session
			session.removeAttribute("seller");
			session.invalidate();
			//redirecting to the seller login page
			RequestDispatcher dispatcher = request.getRequestDispatcher("SellerLogin.jsp");
			dispatcher.forward(request, response);
		}
	}


}
