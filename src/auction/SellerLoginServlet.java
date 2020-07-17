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

import fetchdata.SellerDAO;
import fetchdata.Sellers;

@WebServlet("/SellerLoginServlet")
public class SellerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("sellername");
		String pass = request.getParameter("sellerpass");
		
		//creating sellerDAO object to fetch data from seller 
		SellerDAO sellerDAO = new SellerDAO();
		
		
		try {
			//fetching data from the database
			Sellers seller = sellerDAO.checkSeller(name, pass);
			String destPage = "SellerLogin.jsp";
			if(seller != null){
				if(name.compareTo(seller.getSellerName())==0){
					if(pass.compareTo(seller.getPass())==0){
						HttpSession session2 = request.getSession();
						session2.setAttribute("seller", seller);
						destPage ="viewsell";
					}
					else{
						request.setAttribute("massage3", "Invalid password!!!");
					}
				}
				else{
					request.setAttribute("massage4", "Invalid seller name!!!");
				}
			}
			else{
				request.setAttribute("massage2", "Invalid seller login details!!!");
			}
			//Redirecting to destination page
			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
