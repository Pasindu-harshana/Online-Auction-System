package fetchdata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import getconnection.DBConnection;

public class FetchItemData {
	
	public static List<Items>getItemList() throws SQLException, IOException{
		ArrayList <Items> item = new ArrayList<>();
		
		//get the connection
		Connection con = DBConnection.getCon();
		
		//create statement
		
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery("SELECT * FROM item");
		 
		while(rs.next()){
			String itemNo=rs.getString(1);
			String itemName=rs.getString(2);
			String itemDiscription =rs.getString(3);
			double itemPrice = rs.getDouble(4);
			
			//importing an image
			Blob image = rs.getBlob(5);
			InputStream inputStream = image.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[]buffer = new byte[4096];
			int bytesRead =-1;
			
			while((bytesRead =inputStream.read(buffer)) != -1){
				outputStream.write(buffer, 0,bytesRead);
			}
			
			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			
			inputStream.close();
			outputStream.close();
			//execute the item and loop over the result set
			Items it = new Items(itemNo,itemName,itemDiscription,itemPrice,base64Image);
			item.add(it);
		}
		
		return item;
		
		
	}
}
