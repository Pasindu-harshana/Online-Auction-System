package fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import getconnection.DBConnection;

public class SellerDAO {
	
	Connection con = DBConnection.getCon();
	String sql  = "select * from seller where sellername =? and sellerpass=?";

	public Sellers checkSeller (String sellerName, String pass) throws SQLException{
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, sellerName);
		st.setString(2, pass);
		
		ResultSet rs = st.executeQuery();
		
		Sellers seller = null;
		while(rs.next()){
			seller = new Sellers();
			seller.setSellerID(rs.getString(1));
			seller.setSellerName(rs.getString(2));
			seller.setPass(rs.getString(3));
			seller.setMobileNo(rs.getInt(4));
		}
		return seller;
	}
	
}
