package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import getconnection.DBConnection;

public class DeleteSeller {
	
	public static void deleteSeller(String sellerID) throws SQLException{
		//creating SQL statements to delete relevant seller details to sellerID
		String sql1 = "delete auction.placebids from auction.placebids inner join auction.item  on auction.item.itemno=auction.placebids.ItemID where sellerID = (select sellerID from auction.seller where sellerID=?)";
		String sql2 = "delete item from item where sellerID=?";
		String sql3 = "delete from seller where sellerID =?";
		
		//get a connection from MYSQL database using the getCon method in DBConnection class
		Connection con = DBConnection.getCon();
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, sellerID);
		ps1.executeUpdate();
		
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, sellerID);
		ps2.executeUpdate();
		
		PreparedStatement ps3 = con.prepareStatement(sql3);
		ps3.setString(1, sellerID);
		ps3.executeUpdate();
		
	}
}
