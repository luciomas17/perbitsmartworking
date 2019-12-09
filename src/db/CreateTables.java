package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

	public static void main(String[] args) {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:" + "//Tn01fs02/Tn_G_Saa$/5. Gestione Reparto/CO2/h2db/pws_db2024" + ";IFEXISTS=TRUE", "pws", "pws1234");
			Statement st = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS users "
					+ "(user VARCHAR(255), "
					+ "name VARCHAR(255), "
					+ "surname VARCHAR(255), "
					+ "email VARCHAR(255), "
					+ "divisionOrFunction VARCHAR(255),"
					+ "location VARCHAR(255), "
					+ "fuelType VARCHAR(255), "
					+ "gramsOfCO2 DOUBLE, "
					+ "smartDays INTEGER, "
					+ "kmsSaved DOUBLE, "
					+ "timeSaved INTEGER, "
					+ "consent INTEGER, "
					+ "PRIMARY KEY(user))";
			st.executeUpdate(sql);
			System.out.println("Table users created!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:" + "//Tn01fs02/Tn_G_Saa$/5. Gestione Reparto/CO2/h2db/pws_db2024" + ";IFEXISTS=TRUE", "pws", "pws1234");
			Statement st = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS data "
					+ "(user VARCHAR(255), "
					+ "kmsSavedADay DOUBLE, "
					+ "gramsOfCO2SavedADay DOUBLE, "
					+ "kmsSavedAYear DOUBLE, "
					+ "gramsOfCO2SavedAYear DOUBLE, "
					+ "PRIMARY KEY(user))";
			st.executeUpdate(sql);
			System.out.println("Table data created!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
