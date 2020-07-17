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


import fetchdata.Users;
import getconnection.DBConnection;


@WebServlet("/UpdateUserDetails")
public class UpdateUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try{
				HttpSession session = request.getSession();
				Users user = (Users) session.getAttribute("user");
				String userID = user.getUserID();
				String userName = request.getParameter("userName");
				String userPass = request.getParameter("userPass");
				String reEnter = request.getParameter("reEnter");
				int mobileNo=0;
				mobileNo = Integer.parseInt(request.getParameter("mobileNo"));
				
				Connection con = DBConnection.getCon();
				PreparedStatement ps = null;
				
				if(!userName.isEmpty()){
					String sql = "update registation set name =? where UID =?";
					
						ps = con.prepareStatement(sql);
						ps.setString(1, userName);
						ps.setString(2, userID);
						int i = ps.executeUpdate();
						if(i>0){
							request.setAttribute("massage1", "User name is Updated!");
						}
				}
				
				if (!userPass.isEmpty()){
					String sql2 = "update registation set password=? where UID =?";
					if(reEnter.compareTo(userPass)!=0){
						request.setAttribute("massage2", "Password Enters are doesn't match!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateUserDetails.jsp");
						dispatcher.forward(request, response);
					} 
					ps =con.prepareStatement(sql2);
					ps.setString(1, userPass);
					ps.setString(2, userID);
					int j = ps.executeUpdate();
					if(j>0){
						request.setAttribute("massage3", "Password is updated!");
					}
				}
				if(String.valueOf(mobileNo).length()>0){
					String sql3 = "update registation set mobileno=? where UID =?";
					if(String.valueOf(mobileNo).length()<10){
						request.setAttribute("massage4", "Invalid Mobile number (digits are less than 10)");
						RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateUserDetails.jsp");
						dispatcher.forward(request, response);
					}
					ps=con.prepareStatement(sql3);
					ps.setInt(1,mobileNo);
					ps.setString(2, userID);
					int k = ps.executeUpdate();
					if(k>0){
						request.setAttribute("massage5", "Mobile number is updated!");
					}
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateUserDetails.jsp");
				dispatcher.forward(request, response);
				
			}catch(NumberFormatException e){
				request.setAttribute("massage6", "Invalid Mobile No!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateUserDetails.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
