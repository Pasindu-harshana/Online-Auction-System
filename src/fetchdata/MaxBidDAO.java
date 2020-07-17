package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import getconnection.DBConnection;

public class MaxBidDAO {
	
	Connection con = DBConnection.getCon();
	//creating sql statement
	String sql = "SELECT MAX(biddingamount),userID FROM placebids WHERE ItemID=?";
	
	public double findMaxBid(String itemID) throws SQLException{
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, itemID);
		
		//execute sql query
		ResultSet rs = st.executeQuery();
		MaxBids maxbid = new MaxBids();
		
		//set variable parameter to MaxBids object
		while(rs.next()){
			maxbid.setMaxBidPrice(rs.getDouble(1));
			maxbid.setUserID(rs.getString(2));
			maxbid.setItemID(itemID);
		}
		return maxbid.getMaxBidPrice();
		
	}
}	
