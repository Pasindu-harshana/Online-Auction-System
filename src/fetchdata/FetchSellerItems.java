package fetchdata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import getconnection.DBConnection;

public class FetchSellerItems {

	public static List<Items>getSellerItems(String sellerID) throws SQLException, IOException{
		
		ArrayList <Items> item = new ArrayList<>();
		Connection con = DBConnection.getCon();
		//create sql statement  to fetch item from the database
		String sql = "select itemno,itemname,itemdiscription,itemprice,itemImage from item where sellerID = ?";
		PreparedStatement st= con.prepareStatement(sql);
		st.setString(1, sellerID);
		//execute sql query to get data
		ResultSet rs = st.executeQuery();
		
		//setting fetch item to variables
			while (rs.next()){
				String itemNo = rs.getString(1);
				String itemName = rs.getString(2);
				String itemDiscription = rs.getString(3);
				double itemPrice = rs.getDouble(4);
				Blob image = rs.getBlob(5);
				InputStream inputStream = image.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int byteReads = -1;
				
				while((byteReads = inputStream.read(buffer)) != -1){
					outputStream.write(buffer,0,byteReads);
				}
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				inputStream.close();
				outputStream.close();
				
				//creating an item object
				Items it = new Items(itemNo,itemName,itemDiscription,itemPrice,base64Image);
				item.add(it);
			}
		
		return item;
		
	}
}
