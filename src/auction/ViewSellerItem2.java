package auction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fetchdata.FetchSellerItems;
import fetchdata.Items;
import fetchdata.Sellers;


//@WebServlet("/ViewSellerItem2")
public class ViewSellerItem2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Sellers seller =(Sellers)session.getAttribute("seller");
		String sellerID = seller.getSellerID();

		try {
			List<Items>itemList = FetchSellerItems.getSellerItems(sellerID);
			request.setAttribute("itemList", itemList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewSellerItem.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
