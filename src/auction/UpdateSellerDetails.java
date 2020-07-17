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

import fetchdata.Sellers;
import getconnection.DBConnection;


@WebServlet("/UpdateSellerDetails")
public class UpdateSellerDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try{
				//getting seller values from the session
				HttpSession session = request.getSession();
				Sellers seller = (Sellers) session.getAttribute("seller");
				String sellerID = seller.getSellerID();
				String sellerName = request.getParameter("sellerName");
				String sellerPass = request.getParameter("sellerPass");
				String reEnter = request.getParameter("reEnter");
				int mobileNo=0;
				mobileNo = Integer.parseInt(request.getParameter("mobileNo"));
				
				Connection con = DBConnection.getCon();
				PreparedStatement ps = null;
				
				if(!sellerName.isEmpty()){
					String sql = "update seller set sellername =? where sellerID =?";
					
						ps = con.prepareStatement(sql);
						ps.setString(1, sellerName);
						ps.setString(2, sellerID);
						int i = ps.executeUpdate();
						if(i>0){
							request.setAttribute("massage1", "Seller name is Updated!");
						}
				}
				//validating updates
				if (!sellerPass.isEmpty()){
					String sql2 = "update seller set sellerpass=? where sellerID =?";
					if(reEnter.compareTo(sellerPass)!=0){
						request.setAttribute("massage2", "Password Enters are doesn't match!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateSellerDetails.jsp");
						dispatcher.forward(request, response);
					} 
					ps =con.prepareStatement(sql2);
					ps.setString(1, sellerPass);
					ps.setString(2, sellerID);
					int j = ps.executeUpdate();
					if(j>0){
						request.setAttribute("massage3", "Password is updated!");
					}
				}
				if(String.valueOf(mobileNo).length()>0){
					String sql3 = "update seller set mobileno=? where sellerID =?";
					if(String.valueOf(mobileNo).length()<10){
						request.setAttribute("massage4", "Invalid Mobile number (digits are less than 10)");
						RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateSellerDetails.jsp");
						dispatcher.forward(request, response);
					}
					ps=con.prepareStatement(sql3);
					ps.setInt(1,mobileNo);
					ps.setString(2, sellerID);
					int k = ps.executeUpdate();
					if(k>0){
						request.setAttribute("massage5", "Mobile number is updated!");
					}
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateSellerDetails.jsp");
				dispatcher.forward(request, response);
				
			}catch(NumberFormatException e){
				request.setAttribute("massage6", "Invalid Mobile No!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateSellerDetails.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
