package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {

	public boolean isPresent(User user) {
		String sql = "SELECT * FROM users WHERE user = ?";
		boolean flag = false;
		
		try {
			Connection conn = ConnectDB.connection();
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
		String sql = "INSERT INTO users (user, name, surname, email, divisionOrFunction, location, "
				+ "fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = ConnectDB.connection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUser());
			st.setString(2, user.getName());
			st.setString(3, user.getSurname());
			st.setString(4, user.getEmail());
			st.setString(5, user.getDivisionOrFunction());
			st.setString(6, user.getLocation());
			st.setString(7, user.getFuelType());
			st.setDouble(8, user.getGramsOfCO2());
			st.setInt(9, user.getSmartDays());
			st.setDouble(10, user.getKmsSaved());
			st.setInt(11, user.getTimeSaved());
			int consentInt = 0;
			if(user.isConsent())
				consentInt = 1;
			st.setInt(12, consentInt);
			
			st.execute();
			
			conn.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editExistingUser(User user) {
		String sqlDelete = "DELETE FROM users WHERE user = ?";
		try {
			Connection conn = ConnectDB.connection();
			PreparedStatement st = conn.prepareStatement(sqlDelete);
			st.setString(1, user.getUser());
			st.execute();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
		String sql = "INSERT INTO users (user, name, surname, email, divisionOrFunction, location, "
				+ "fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = ConnectDB.connection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUser());
			st.setString(2, user.getName());
			st.setString(3, user.getSurname());
			st.setString(4, user.getEmail());
			st.setString(5, user.getDivisionOrFunction());
			st.setString(6, user.getLocation());
			st.setString(7, user.getFuelType());
			st.setDouble(8, user.getGramsOfCO2());
			st.setInt(9, user.getSmartDays());
			st.setDouble(10, user.getKmsSaved());
			st.setInt(11, user.getTimeSaved());
			int consentInt = 0;
			if(user.isConsent())
				consentInt = 1;
			st.setInt(12, consentInt);
			
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
			Connection conn = ConnectDB.connection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					String user = res.getString("user");
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
						
					result.add(new User(user, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent));
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

	public User getUserFromUsername(String username) {
		String sql = "SELECT * FROM users";
		List<User> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.connection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					String user = res.getString("user");
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
						
					result.add(new User(user, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			for(User u : result) {
				if(u.getUser().equals(username))
					return u;
			}
			
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void clearDatabase() {
		String sql = "DELETE FROM users";
		
		try {
			Connection conn = ConnectDB.connection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.execute();
						
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
