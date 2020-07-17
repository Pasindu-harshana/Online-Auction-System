
package auction;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fetchdata.Sellers;
import getconnection.DBConnection;

@WebServlet("/InsertItem")
@MultipartConfig(maxFileSize = 16177215)
public class InsertItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int hitcount;
	
	public int getHitcount() {
		return hitcount;
	}


	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values for text fields
		try {
			try{
				//getting seller values from session
				HttpSession session=request.getSession();
				Sellers seller = (Sellers) session.getAttribute("seller");
				
				//getting other user input value from JSP page
				String sellerID = seller.getSellerID();
				String itemName = request.getParameter("ItemName");
				String itemDiscription = request.getParameter("Description");
				double  itemPrice =Double.parseDouble( request.getParameter("StartPrice"));
				InputStream inputStream = null;
				String itID;
				setHitcount(getHitcount() + 1);
				itID="IT00000"+getHitcount();
		
				Part filePart =request.getPart("Image");
		
				if (filePart != null){
					inputStream = filePart.getInputStream();
				}
				Connection con = null;
		
				con = DBConnection.getCon();
				
				String sql = "INSERT INTO item (itemno,itemname,itemdiscription,itemprice,ItemImage,sellerID) VALUES(?,?,?,?,?,?)";
			
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, itID);
				st.setString(2, itemName);
				st.setString(3, itemDiscription);
				st.setDouble(4, itemPrice);
				
				//set inserting image blob value to database
				if (inputStream != null)
					st.setBlob(5, inputStream);
				st.setString(6, sellerID);
				
				//executing prepared statement of SQL
				int row = st.executeUpdate();
				if (row > 0){
					request.setAttribute("massage", "Upload is successfull!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("InsertItem.jsp");
					dispatcher.forward(request, response);
				}
			}catch(NumberFormatException e){
				request.setAttribute("massage2", "Invalid Item Price");
				RequestDispatcher dispatcher = request.getRequestDispatcher("InsertItem.jsp");
				dispatcher.forward(request, response);
			}
		}catch (SQLException e) {
				
			e.printStackTrace();
		}
	}

}
