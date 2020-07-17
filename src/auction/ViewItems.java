package auction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fetchdata.FetchItemData;
import fetchdata.Items;


//@WebServlet("/ViewItems")
public class ViewItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Items>itemList = FetchItemData.getItemList();
			request.setAttribute("itemList", itemList);
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewItems.jsp"); 
		dispatcher.forward(request, response);
	}


}
