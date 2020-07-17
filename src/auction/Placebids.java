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

import fetchdata.MaxBidDAO;
import fetchdata.Users;
import getconnection.DBConnection;



@WebServlet("/Placebids")
public class Placebids extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				try{
					double bid = Double.parseDouble(request.getParameter("bid"));
					double itemPrice =Double.parseDouble(request.getParameter("itemPrice"));
					String itemID = request.getParameter("itemID");
					String sql ="insert into placebids(biddingamount, userID,ItemID) values(?,?,?)";
					
					//creating MaxBidDAO object to  fetch data according to max bids
					MaxBidDAO maxbid = new MaxBidDAO();
					double maxbidprice = maxbid.findMaxBid(itemID);
				
					if(bid >= itemPrice){
					
						if(bid > maxbidprice){
							//get DBConnection
							Connection con = DBConnection.getCon();
							
							//creating session object
							HttpSession session =request.getSession();
							Users user = (Users) session.getAttribute("user");
							String userID = user.getUserID();
							//preparing sql statement
							PreparedStatement st = con.prepareStatement(sql);
						
							st.setDouble(1, bid);
							st.setString(2,userID );
							st.setString(3, itemID);
							int row = st.executeUpdate();
						
							if(row >0){
								//crating massage to view when bid place inside the database successfully
								request.setAttribute("massage2", "Bid is placed successfully!!!");
								RequestDispatcher dispatcher = request.getRequestDispatcher("view2");
								dispatcher.forward(request, response);
							}
						
						}
						else{
							//creating massage to view when place bid is less than current max bid
							request.setAttribute("massage", "Bid is less than current max bid => "+maxbidprice);
							RequestDispatcher dispatcher=request.getRequestDispatcher("view2");
							dispatcher.forward(request, response);
						}
				
					}
					else{
						//creating massage to view when place bid is less than start bid price
						request.setAttribute("massage", "Bid is less than start price!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("view2");
						dispatcher.forward(request, response);
					}
				}catch(NumberFormatException e){
					//creating massage to display when bid place is invalid data type
					request.setAttribute("massage", "Invalid Price!!!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("view2");
					dispatcher.forward(request, response);
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
