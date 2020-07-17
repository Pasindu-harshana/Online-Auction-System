package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import getconnection.DBConnection;

public class DeleteItemDAO {
	
	public static int deleteItem(String itemNo) throws SQLException{
		String sql1= "delete from placebids where ItemID = ?";
		String  sql2 ="delete from item where itemno = ? ";
		
		Connection con = DBConnection.getCon();
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, itemNo);
		ps1.executeUpdate();
		
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, itemNo);
		int i = ps2.executeUpdate();
		
		return i;
		
	}
}
