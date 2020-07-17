package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import getconnection.DBConnection;

public class UserWinDAO {

	Connection con = DBConnection.getCon();
	List<MaxBids> list1 = new ArrayList<>();
	List<Items>list2 = new ArrayList<>();
	
	public List<MaxBids> findWins(String userID) throws SQLException{
		
		String sql = "select max(biddingamount),itemID from placebids where userID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, userID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			MaxBids maxbid = new MaxBids();
			maxbid.setMaxBidPrice(rs.getDouble(1));
			maxbid.setItemID(rs.getString(2));
			maxbid.setUserID(userID);
			list1.add(maxbid);
		}
		return list1;
	}
	
	public List<Items>itemsInfo (List<MaxBids>list) throws SQLException{
		
		String itemID;
		String sql = "select itemname, itemdiscription, sellerID from item where itemno = ?";
		PreparedStatement st = null;
		ResultSet rs = null;
		
		for(MaxBids max:list){
			itemID = max.getItemID();
			st = con.prepareStatement(sql);
			st.setString(1, itemID);
			rs = st.executeQuery();
			
			while(rs.next()){
				String itemName = rs.getString(1);
				String itemDes = rs.getString(2);
				String sellerId =rs.getString(3);
				Items item = new Items(itemName,itemDes,sellerId);
				list2.add(item);
				
			}
		}
		return list2;
		
	}
}
