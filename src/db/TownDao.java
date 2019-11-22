package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TownDao {

	public List<String> getProvincesList() {
		String sql = "SELECT sigla FROM italy_provincies";
		List<String> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					result.add(res.getString("sigla"));
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
	
	public List<String> getCitiesFromProvince(String sigla) {
		String sql = "SELECT comune FROM italy_cities WHERE provincia = ?";
		List<String> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			st.setString(1, sigla);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					result.add(res.getString("comune"));
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
	
	public List<Double> getLatLngFromCity(String comune) {
		String sql = "SELECT lat, lng FROM italy_geo WHERE comune = ?";
		List<Double> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			st.setString(1, comune);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					result.add(res.getDouble("lat"));
					result.add(res.getDouble("lng"));
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
