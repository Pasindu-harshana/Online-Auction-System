package getconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection con = null;
	static public Connection getCon(){
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "01St*Saymyname");
			
		} catch ( SQLException e) {
			
			e.printStackTrace();
		}
		return con;
		
	}
}
