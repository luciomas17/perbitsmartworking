package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import model.Town;
import model.User;

public class UserDao {

	public boolean isPresent(User user) {
		String sql = "SELECT * FROM users WHERE user = ?";
		boolean flag = false;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUser());
			ResultSet res = st.executeQuery();
			
			if(res.next())
				flag = true;
			
			conn.close();
			return flag;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addNewUser(User user) {
		String sql = "INSERT INTO users (user, name, surname, email, division, responsible, role, "
				+ "fuelType, gramsOfCO2, province, city, lat, lng, smartDays, consent) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUser());
			st.setString(2, user.getName());
			st.setString(3, user.getSurname());
			st.setString(4, user.getEmail());
			st.setString(5, user.getDivision());
			st.setString(6, user.getResponsible());
			st.setString(7, user.getRole());
			st.setString(8, user.getFuelType());
			st.setDouble(9, user.getGramsOfCO2());
			st.setString(10, user.getDomicile().getProvince());
			st.setString(11, user.getDomicile().getName());
			st.setDouble(12, user.getDomicile().getLatlng().getLatitude());
			st.setDouble(13, user.getDomicile().getLatlng().getLongitude());
			st.setInt(14, user.getSmartDays());
			int consentInt = 0;
			if(user.isConsent())
				consentInt = 1;
			st.setInt(15, consentInt);
			
			st.execute();
			
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editExistingUser(User user) {
		String sql = "UPDATE users SET user = ?, name = ?, surname = ?, email, division = ?, responsible = ?, role = ?, "
				+ "fuelType = ?, gramsOfCO2 = ?, province = ?, city = ?, lat = ?, lng = ?, smartDays = ?, consent = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUser());
			st.setString(2, user.getName());
			st.setString(3, user.getSurname());
			st.setString(4, user.getEmail());
			st.setString(5, user.getDivision());
			st.setString(6, user.getResponsible());
			st.setString(7, user.getRole());
			st.setString(8, user.getFuelType());
			st.setDouble(9, user.getGramsOfCO2());
			st.setString(10, user.getDomicile().getProvince());
			st.setString(11, user.getDomicile().getName());
			st.setDouble(12, user.getDomicile().getLatlng().getLatitude());
			st.setDouble(13, user.getDomicile().getLatlng().getLongitude());
			st.setInt(14, user.getSmartDays());
			int consentInt = 0;
			if(user.isConsent())
				consentInt = 1;
			st.setInt(15, consentInt);
			
			st.execute();
			
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<User> getUserList() {
		String sql = "SELECT * FROM users";
		List<User> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					String user = res.getString("user");
					String name = res.getString("name");
					String surname = res.getString("surname");
					String email = res.getString("email");
					String division = res.getString("division");
					String responsible = res.getString("responsible");
					String role = res.getString("role");
					String fuelType = res.getString("fuelType");
					double gramsOfCO2 = res.getDouble("gramsOfCO2");
					
					String province = res.getString("province");
					String city = res.getString("city");
					double lat = res.getDouble("lat");
					double lng = res.getDouble("lng");
					LatLng latLng = new LatLng(lat, lng);
					Town domicile = new Town(province, city, latLng);
					
					int smartDays = res.getInt("smartDays");
					
					int consentInt = res.getInt("consent");
					boolean consent = false;
					if(consentInt == 1)
						consent = true;
						
					result.add(new User(user, name, surname, email, division, responsible, role, 
							fuelType, gramsOfCO2, domicile, smartDays, consent));
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
