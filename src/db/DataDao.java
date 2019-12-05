package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Data;
import model.User;

public class DataDao {

	public boolean addNewData(User user, double kmsSavedADay, double gramsOfCO2SavedADay, double kmsSavedAYear, double gramsOfCO2SavedAYear) {
		String sql = "INSERT INTO data (user, kmsSavedADay, gramsOfCO2SavedADay, kmsSavedAYear, gramsOfCO2SavedAYear) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			Connection conn = HSQLConnect.connection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUser());
			st.setDouble(2, kmsSavedADay);
			st.setDouble(3, gramsOfCO2SavedADay);
			st.setDouble(4, kmsSavedAYear);
			st.setDouble(5, gramsOfCO2SavedAYear);
			
			st.execute();
			
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editExistingData(User user, double kmsSavedADay, double gramsOfCO2SavedADay, double kmsSavedAYear, double gramsOfCO2SavedAYear) {
		String sql = "UPDATE data SET user = ?, kmsSavedADay = ?, gramsOfCO2SavedADay = ?, kmsSavedAYear = ?, gramsOfCO2SavedAYear = ?";
		
		try {
			Connection conn = HSQLConnect.connection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUser());
			st.setDouble(2, kmsSavedADay);
			st.setDouble(3, gramsOfCO2SavedADay);
			st.setDouble(4, kmsSavedAYear);
			st.setDouble(5, gramsOfCO2SavedAYear);
			
			st.execute();
			
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Data> getDataList() {
		String sql = "SELECT * FROM data d, users u WHERE d.user = u.user";
		List<Data> result = new ArrayList<>();
		
		try {
			Connection conn = HSQLConnect.connection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					String username = res.getString("user");
					double kmsSavedADay = res.getDouble("kmsSavedADay");
					double gramsOfCO2SavedADay = res.getDouble("gramsOfCO2SavedADay");
					double kmsSavedAYear = res.getDouble("kmsSavedAYear");
					double gramsOfCO2SavedAYear = res.getDouble("gramsOfCO2SavedAYear");
					
					String name = res.getString("name");
					String surname = res.getString("surname");
					String email = res.getString("email");
					String divisionOrFunction = res.getString("divisionOrFunction");
					String location = res.getString("location");
					String fuelType = res.getString("fuelType");
					double gramsOfCO2 = res.getDouble("gramsOfCO2");
					int smartDays = res.getInt("smartDays");			
					double kmsSaved = res.getDouble("kmsSaved");
					int timeSaved = res.getInt("timeSaved");	
					int consentInt = res.getInt("consent");
					boolean consent = false;
					if(consentInt == 1)
						consent = true;
					
					User user = new User(username, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent);
						
					result.add(new Data(user, kmsSavedADay, gramsOfCO2SavedADay, kmsSavedAYear, gramsOfCO2SavedAYear));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Data getDataFromUsername(String toFind) {
		String sql = "SELECT * FROM data d, users u WHERE d.user = u.user AND u.user = ?";
		Data result = null;
		
		try {
			Connection conn = HSQLConnect.connection();
			PreparedStatement st = conn.prepareStatement(sql);	
			st.setString(1, toFind);
			ResultSet res = st.executeQuery();
			
			if(res.next()) {
				try {
					String username = res.getString("user");
					double kmsSavedADay = res.getDouble("kmsSavedADay");
					double gramsOfCO2SavedADay = res.getDouble("gramsOfCO2SavedADay");
					double kmsSavedAYear = res.getDouble("kmsSavedAYear");
					double gramsOfCO2SavedAYear = res.getDouble("gramsOfCO2SavedAYear");
					
					String name = res.getString("name");
					String surname = res.getString("surname");
					String email = res.getString("email");
					String divisionOrFunction = res.getString("divisionOrFunction");
					String location = res.getString("location");
					String fuelType = res.getString("fuelType");
					double gramsOfCO2 = res.getDouble("gramsOfCO2");
					int smartDays = res.getInt("smartDays");			
					double kmsSaved = res.getDouble("kmsSaved");
					int timeSaved = res.getInt("timeSaved");	
					int consentInt = res.getInt("consent");
					boolean consent = false;
					if(consentInt == 1)
						consent = true;
					
					User user = new User(username, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent);
						
					result = new Data(user, kmsSavedADay, gramsOfCO2SavedADay, kmsSavedAYear, gramsOfCO2SavedAYear);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void clearDatabase() {
		String sql = "DELETE FROM data";
		
		try {
			Connection conn = HSQLConnect.connection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.execute();
						
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
