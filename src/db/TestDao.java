package db;

import java.util.List;

public class TestDao {

	public static void main(String[] args) {
		
		TownDao townDao = new TownDao();
		
		List<String> provinces = townDao.getProvincesList();
		System.out.println(provinces);
		
		List<String> cities = townDao.getCitiesFromProvince("TO");
		System.out.println(cities);
		
		List<Double> latLng = townDao.getLatLngFromCity("Torino");
		System.out.println(latLng);

	}

}
