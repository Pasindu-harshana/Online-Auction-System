package auction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import getconnection.DBConnection;


@WebServlet("/Registation")
public class Registration extends HttpServlet {
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
					String name = request.getParameter("name");
					String password = request.getParameter("password");
					String reenter = request.getParameter("re-enter");
					int mobileNo = Integer.parseInt(request.getParameter("mobileno"));
					String id ;
					setHitcount(getHitcount() + 1);
					id = "UID000"+getHitcount();
					
					String sql = "insert into registation(UID,name,password,mobileNo) values(?,?,?,?)";
					String sql2 ="select * from registation where name = ?";
					
					//validating password
					if(reenter.compareTo(password)!=0){
						request.setAttribute("massage3", "Password enters are doesn't match!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("Registation.jsp");
						dispatcher.forward(request, response);
					}
					//getting the database connectivity
					Connection con = DBConnection.getCon();
				
					//preparing the sql statement to retrieve data from the database
					PreparedStatement ps1 = con.prepareStatement(sql2);
					ps1.setString(1, name);
					ResultSet rs = ps1.executeQuery();
				
					//check weather the user is existing or not
					if (rs.next()){
						request.setAttribute("massage", "User Already exist!");
					
						//redirecting to user registration  page
						RequestDispatcher dispatcher = request.getRequestDispatcher("Registation.jsp");
						dispatcher.forward(request, response);
					}
					else{
						PreparedStatement ps2 = con.prepareStatement(sql);
						ps2.setString(1, id); 
						ps2.setString(2, name);
						ps2.setString(3, password);
						ps2.setInt(4, mobileNo);
						ps2.executeUpdate();
						
						request.setAttribute("massage", "You have been sucessfully registered!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("Registation.jsp");
						dispatcher.forward(request, response);
					}
				}catch(NumberFormatException e){
					request.setAttribute("massage2", "Invalid mobile No");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Registation.jsp");
					dispatcher.forward(request, response);
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}

	

}
