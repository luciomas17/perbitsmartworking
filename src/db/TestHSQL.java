package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestHSQL {

	public static void main(String[] args) {		
		String sql = "SELECT * FROM data";
		
		try {
			Connection conn = HSQLConnect.connection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next())
				System.out.println(res.getString("user"));
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
