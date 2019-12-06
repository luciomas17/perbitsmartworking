package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	private static final String jdbcURL = "jdbc:h2:" + "G:/5. Gestione Reparto/CO2/h2db/pws_db";

	public static Connection connection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL, "pws", "pws1234");
			
		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
		
		return conn;
	}

}
