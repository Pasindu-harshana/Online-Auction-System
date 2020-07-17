package auction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import getconnection.DBConnection;


@WebServlet("/UpdateItem")
public class UpdateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			try{
				HttpSession session = request.getSession();
				String itemID = (String) session.getAttribute("itemID");
				String itemName = request.getParameter("itemName");
				String itemDes = request.getParameter("itemDes");
				double startBid =0;
				String startbid = request.getParameter("startPrice");
			
				if (!startbid.isEmpty()){
					startBid = Double.parseDouble(startbid);
				}
			
				Connection con = DBConnection.getCon();
				PreparedStatement ps = null;
			
				if(itemName.compareTo("null")!=0 && !itemName.isEmpty()){
					String sql = "update item set itemname = ? where itemno = ?";
					
						ps = con.prepareStatement(sql);
						ps.setString(1, itemName);
						ps.setString(2, itemID);
						int i = ps.executeUpdate();
						
						if(i>0){
							request.setAttribute("massage1", "Item Name Updated!");
						}
				
				}
			
				if (itemDes.compareTo("null")!=0 && !itemDes.isEmpty()){
					String sql1 = "update item set itemdiscription = ? where itemno = ?";
						ps = con.prepareStatement(sql1);
						ps.setString(1, itemDes);
						ps.setString(2, itemID);
						int j = ps.executeUpdate();
						
						if (j>0){
							request.setAttribute("massage2", "Item Description Updated!");
						}
				}
			
				if (startBid != 0){
					String sql2 = "update item set itemprice = ? where itemno = ?";
						ps = con.prepareStatement(sql2);
						ps.setDouble(1, startBid);
						ps.setString(2, itemID);
						int k = ps.executeUpdate();
						
						if(k>0){
							request.setAttribute("massage3", "Item Strat Bid Price Updated!");
						}
					
				}
			
				if(itemName.compareTo("null")==0 && itemDes.compareTo("null")==0 && startBid == 0){
					request.setAttribute("massage4", "No update is done!");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateItems.jsp");
				dispatcher.forward(request, response);
			}catch(NumberFormatException e){
				request.setAttribute("massage5", "Invalid bid price!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateItems.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
