package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import getconnection.DBConnection;

public class DeleteUser {
	
	public static void deleteUser(String userID) throws SQLException{
		String sql1 = "delete from placebids where userID=?";
		String sql2 = "delete from registation where UID=?";
		
		Connection con =DBConnection.getCon();
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1, userID);	
		st1.executeUpdate();
		
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.setString(1, userID);
		st2.executeUpdate();
	}
	
	
}
