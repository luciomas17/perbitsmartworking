package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HSQLConnect {
	
	public static Connection connection() {
		Connection conn = null;
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:file:C:\\Users\\PAO1MI\\eclipse-workspace\\Perbit Smart Working\\db\\hsql\\db;ifexists=true", 
					"pws", "pws1234");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return conn;
	}

}
