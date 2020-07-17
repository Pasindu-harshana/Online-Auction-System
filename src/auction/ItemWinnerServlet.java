package auction;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fetchdata.MaxBids;
import fetchdata.Users;
import fetchdata.WinnerDAO;


public class ItemWinnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String itemID = request.getParameter("itemID");
		WinnerDAO wininfo = new WinnerDAO();
		
		try {
			//getting maximum bid price from the placebids table using getMaxBidDetaiils method in WinnerDAO class
			MaxBids maxbidInfo = wininfo.getMaxBidDetails(itemID);
			
			//getting max bided user details from getWinnerDetails method in WinnerDAO class
			Users winnerInfo = wininfo.getWinnerDetails(maxbidInfo.getUserID());
			request.setAttribute("maxbidInfo", maxbidInfo);
			request.setAttribute("winnerInfo", winnerInfo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewWinnerDetails.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
