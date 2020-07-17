package auction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import getconnection.DBConnection;

@WebServlet("/SellerRegistation")
public class SellerRegistation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int hitcount=0;
	
	public int getHitcount() {
		return hitcount;
	}

	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			try{
				String name = request.getParameter("sellername");
				String pass = request.getParameter("sellerpass");
				String reenter = request.getParameter("re-enter");
				int mobileno = Integer.parseInt(request.getParameter("mobileno"));
				String sid;
				setHitcount(getHitcount() + 1);
				sid="SID000"+getHitcount();
		
				String sql1 = "insert into seller (sellerID,sellername,sellerpass,mobileno) values(?,?,?,?)";
				String sql2 = "select * from seller where sellername = ?";
				
				//Validating password
				if(reenter.compareTo(pass)!=0){
					request.setAttribute("massage3", "Password enters are doesn't match!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("SellerRegistation.jsp");
					dispatcher.forward(request, response);
				}
		
				//getting the database connectivity
				Connection con = DBConnection.getCon();
		
				//preparing the sql statement to retrieve data from the database
				PreparedStatement st1 = con.prepareStatement(sql2);
				st1.setString(1, name);
				ResultSet rs = st1.executeQuery();
			
				//check weather the user is existing or not
				if(rs.next()){
					request.setAttribute("massage", "Seller is already exsist!");
					
					//redirecting to user registration  page
					RequestDispatcher dispatcher = request.getRequestDispatcher("SellerRegistation.jsp");
					dispatcher.forward(request, response);
				}
				else{
					PreparedStatement st2 = con.prepareStatement(sql1);
					st2.setString(1, sid);
					st2.setString(2, name);
					st2.setString(3, pass);
					st2.setInt(4, mobileno);
				
					st2.executeUpdate();
				
					request.setAttribute("massage", "You have been registerd sucessfully!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("SellerRegistation.jsp");
					dispatcher.forward(request, response);
				}
			}catch(NumberFormatException e){
				request.setAttribute("massage2", "Invalid Mobile Number");
				RequestDispatcher dispatcher = request.getRequestDispatcher("SellerRegistation.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	

}
