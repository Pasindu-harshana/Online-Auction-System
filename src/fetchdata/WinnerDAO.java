package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import getconnection.DBConnection;

public class WinnerDAO {
	
	Connection con = DBConnection.getCon();
	
	public MaxBids getMaxBidDetails(String itemID) throws SQLException{
		String sql = "select max(biddingamount),userID from placebids where ItemID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, itemID);
		ResultSet rs = ps.executeQuery();
		MaxBids maxbidinfo = new MaxBids();
		
		while(rs.next()){
			maxbidinfo.setItemID(itemID);
			maxbidinfo.setMaxBidPrice(rs.getDouble(1));
			maxbidinfo.setUserID(rs.getString(2));
		}
		return maxbidinfo;
		
	}
	
	public Users getWinnerDetails (String userID) throws SQLException{
		String sql2 = "select name,mobileNo from registation where UID=?";
		PreparedStatement ps = con.prepareStatement(sql2);
		ps.setString(1, userID);
		ResultSet rs = ps.executeQuery();
		Users winner = new Users();
		
		while(rs.next()){
			winner.setName(rs.getString(1));
			winner.setMobileNo(rs.getInt(2));
		}
		return winner;
		
	}
}
