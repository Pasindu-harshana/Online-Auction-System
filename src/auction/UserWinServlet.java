package auction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fetchdata.Items;
import fetchdata.MaxBids;
import fetchdata.UserWinDAO;
import fetchdata.Users;


//@WebServlet("/UserWinServlet")
public class UserWinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		String userID = user.getUserID();
		UserWinDAO winInfo = new UserWinDAO();
		List<MaxBids>list1 = new ArrayList<>();
		List<Items>list2 = new ArrayList<>();
		
		try {
			list1 = winInfo.findWins(userID);
			list2 = winInfo.itemsInfo(list1);
			
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserWins.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
