package auction; 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fetchdata.DeleteSeller;
import fetchdata.Sellers;

@WebServlet("/DeleteSellerServlet")
public class DeleteSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//getting seller session value from the session
			HttpSession session = request.getSession();
			Sellers seller = (Sellers) session.getAttribute("seller");
			
			//calling deleteSeller method in DeleteSeller class
			DeleteSeller.deleteSeller(seller.getSellerID());
			request.setAttribute("massage2", "Seller have been removed");
			RequestDispatcher dispathcer = request.getRequestDispatcher("SellerLogin.jsp");
			dispathcer.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
