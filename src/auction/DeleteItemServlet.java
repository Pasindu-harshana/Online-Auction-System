package auction;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fetchdata.DeleteItemDAO;


@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get parameters from JSP
		String itemID = request.getParameter("itemID");
		String destpage = "viewsell";
		try {
			//calling deleteItem method in DeleteItemDAO class
			int no = DeleteItemDAO.deleteItem(itemID);
			
			if (no>1){
				request.setAttribute("massage", "Item is deleting....");
			}
			else{
				request.setAttribute("massage2", "Item successfully removed");
			}
			//Redirecting to destination page
			RequestDispatcher dispatcher= request.getRequestDispatcher(destpage);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}

}
