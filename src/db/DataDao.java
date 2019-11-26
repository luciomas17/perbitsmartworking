package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import model.Data;
import model.Town;
import model.User;

public class DataDao {

	public boolean addNewData(User user, double kmsSavedADay, double gramsOfCO2SavedADay, double kmsSavedAYear, double gramsOfCO2SavedAYear) {
		String sql = "INSERT INTO data (user, kmsSavedADay, gramsOfCO2SavedADay, kmsSavedAYear, gramsOfCO2SavedAYear) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			Connection conn = DBConnect.getConnection();
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
			Connection conn = DBConnect.getConnection();
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
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					String userString = res.getString("d.user");
					double kmsSavedADay = res.getDouble("d.kmsSavedADay");
					double gramsOfCO2SavedADay = res.getDouble("d.gramsOfCO2SavedADay");
					double kmsSavedAYear = res.getDouble("d.kmsSavedAYear");
					double gramsOfCO2SavedAYear = res.getDouble("d.gramsOfCO2SavedAYear");
					
					String name = res.getString("u.name");
					String surname = res.getString("u.surname");
					String email = res.getString("u.email");
					String division = res.getString("u.division");
					String responsible = res.getString("u.responsible");
					String role = res.getString("u.role");
					String fuelType = res.getString("u.fuelType");
					double gramsOfCO2 = res.getDouble("u.gramsOfCO2");
					
					String province = res.getString("u.province");
					String city = res.getString("u.city");
					double lat = res.getDouble("u.lat");
					double lng = res.getDouble("u.lng");
					LatLng latLng = new LatLng(lat, lng);
					Town domicile = new Town(province, city, latLng);
					
					int smartDays = res.getInt("u.smartDays");
					
					int consentInt = res.getInt("u.consent");
					boolean consent = false;
					if(consentInt == 1)
						consent = true;
					
					User user = new User(userString, name, surname, email, division, responsible, role, 
							fuelType, gramsOfCO2, domicile, smartDays, consent);
						
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

	public Data getDataFromUsername(String username) {
		String sql = "SELECT * FROM data d, users u WHERE d.user = u.user AND u.user = ?";
		Data result = null;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			st.setString(1, username);
			ResultSet res = st.executeQuery();
			
			if(res.next()) {
				try {
					String userString = res.getString("d.user");
					double kmsSavedADay = res.getDouble("d.kmsSavedADay");
					double gramsOfCO2SavedADay = res.getDouble("d.gramsOfCO2SavedADay");
					double kmsSavedAYear = res.getDouble("d.kmsSavedAYear");
					double gramsOfCO2SavedAYear = res.getDouble("d.gramsOfCO2SavedAYear");
					
					String name = res.getString("u.name");
					String surname = res.getString("u.surname");
					String email = res.getString("u.email");
					String division = res.getString("u.division");
					String responsible = res.getString("u.responsible");
					String role = res.getString("u.role");
					String fuelType = res.getString("u.fuelType");
					double gramsOfCO2 = res.getDouble("u.gramsOfCO2");
					
					String province = res.getString("u.province");
					String city = res.getString("u.city");
					double lat = res.getDouble("u.lat");
					double lng = res.getDouble("u.lng");
					LatLng latLng = new LatLng(lat, lng);
					Town domicile = new Town(province, city, latLng);
					
					int smartDays = res.getInt("u.smartDays");
					
					int consentInt = res.getInt("u.consent");
					boolean consent = false;
					if(consentInt == 1)
						consent = true;
					
					User user = new User(userString, name, surname, email, division, responsible, role, 
							fuelType, gramsOfCO2, domicile, smartDays, consent);
						
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

}
