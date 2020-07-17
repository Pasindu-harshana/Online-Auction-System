package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import getconnection.DBConnection;

public class UserDAO {
	//creating a connection with database
	public Users checkUser(String name, String password) throws SQLException{
		
	
		Connection con = DBConnection.getCon();
		String sql = "Select * FROM registation WHERE name=? AND password=?";
		
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2,password);
		
		ResultSet rs = statement.executeQuery();
		
		Users user = null;
		while (rs.next()){
			user = new Users();
			user.setUserID(rs.getString(1));
			user.setName(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setMobileNo(rs.getInt(4));
		}
		return user;
	}
		
}
