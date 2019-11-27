package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import db.DataDao;
import db.TownDao;
import db.UserDao;

public class Model {
	
	private UserDao userDao;
	private DataDao dataDao;
	private TownDao townDao;
	private final LatLng perbitLatLng;
	private Map<String, String> divisionsResponsibleMap;
	private List<String> fuelTypes, roles, emailDomains;
	private boolean overwrite;
	
	public Model() {
		this.userDao = new UserDao();
		this.dataDao = new DataDao();
		this.townDao = new TownDao();
		this.perbitLatLng = new LatLng(45.013503,7.620564);
		this.divisionsResponsibleMap = new HashMap<>();
		addItemsToDivisionsResponsibleMap();
		this.fuelTypes = new ArrayList<>();
		addItemsToFuelTypes();
		this.roles = new ArrayList<>();
		addItemsToRoles();
		this.emailDomains = new ArrayList<>();
		addItemsToEmailDomains();
		this.overwrite = false;
	}

	private void addItemsToEmailDomains() {
		this.emailDomains.add("bosch.com");
		this.emailDomains.add("it.bosch.com");
	}

	private void addItemsToRoles() {
		this.roles.add("External");
		this.roles.add("Sales");
		this.roles.add("Logistics");
		this.roles.add("IT partner");
		/*
		 * To update!!!
		 */
	}

	private void addItemsToDivisionsResponsibleMap() {
		this.divisionsResponsibleMap.put("FI/SOI", "Hoferer");
		this.divisionsResponsibleMap.put("IT/SOE", "Hoferer");
		this.divisionsResponsibleMap.put("FI/SQW", "Fanelli");
		this.divisionsResponsibleMap.put("IT/SQW", "Fanelli");
		this.divisionsResponsibleMap.put("FI/SCC", "Mozzi");
		this.divisionsResponsibleMap.put("IT/SCC", "Mozzi");
		this.divisionsResponsibleMap.put("PERBIT/COR", "Mozzi");
		this.divisionsResponsibleMap.put("PERBIT/CTG", "Mozzi");
		this.divisionsResponsibleMap.put("PERBIT/HRL", "Formenti");
		this.divisionsResponsibleMap.put("CVO/CNH", "Russo");
		this.divisionsResponsibleMap.put("PS/CNH", "Russo");
		this.divisionsResponsibleMap.put("PS/CFI", "Monteamaro");
		this.divisionsResponsibleMap.put("CC/CFI", "Cobianchi");
		this.divisionsResponsibleMap.put("CC/SIT", "Cobianchi");
		this.divisionsResponsibleMap.put("CC/SFI", "Cobianchi");
		this.divisionsResponsibleMap.put("ED/SFI", "Dausch");
		this.divisionsResponsibleMap.put("ED/EFI-P", "Dausch");
		this.divisionsResponsibleMap.put("ED/SIT", "Dausch");
		this.divisionsResponsibleMap.put("CM/CFI", "Santangelo");
		this.divisionsResponsibleMap.put("AE-BE/CFI", "Santangelo");
		this.divisionsResponsibleMap.put("AE-BE/SIT", "Santangelo");
		this.divisionsResponsibleMap.put("AE-BE/SEC", "Santangelo");
		this.divisionsResponsibleMap.put("AA/CFI", "Tolle");
		this.divisionsResponsibleMap.put("AS/CFI", "Risso");
		this.divisionsResponsibleMap.put("AS/SIT", "Risso");
	}

	private void addItemsToFuelTypes() {
		this.fuelTypes.add("Gasoline");
		this.fuelTypes.add("Diesel");
		this.fuelTypes.add("LPG - Gasoline");
		this.fuelTypes.add("CNG - Gasoline");
		this.fuelTypes.add("CNG");
		this.fuelTypes.add("Electric - Gasoline");
		this.fuelTypes.add("Electric - Diesel");
		this.fuelTypes.add("Electric");
	}

	public double calculateKmsSavedADay(User user) {
		LatLng userLatLng = user.getDomicile().getLatlng();
		double result = LatLngTool.distance(userLatLng, this.perbitLatLng, LengthUnit.KILOMETER) * 2;
		
		return result;
	}
	
	public double calculateKmsSavedAYear(User user) {
		LatLng userLatLng = user.getDomicile().getLatlng();
		int smartDays = user.getSmartDays();
		double result = LatLngTool.distance(userLatLng, this.perbitLatLng, LengthUnit.KILOMETER) * 2 * smartDays;
		
		return result;
	}
	
	public double calculateGramsOfCO2SavedADay(User user) {
		double kms = calculateKmsSavedADay(user);
		double gramsOfCO2 = user.getGramsOfCO2();
		double result = kms * gramsOfCO2;
		
		return result;
	}
	
	public double calculateGramsOfCO2SavedAYear(User user) {
		double kms = calculateKmsSavedAYear(user);
		double gramsOfCO2 = user.getGramsOfCO2();
		double result = kms * gramsOfCO2;
		
		return result;
	}
	
	public void addNewUser(String user, String name, String surname, String email, String division, String responsible, String role, 
			String fuelType, double gramsOfCO2, String province, String city, String address, int smartDays, boolean consent) {
		
		// List<Double> latLnglist = townDao.getLatLngFromCity(city);
		// LatLng latLng = new LatLng(latLnglist.get(0), latLnglist.get(1));
		
		// double[] latLngVector = getLatLngFromAddress(province, city, address);
		// LatLng latLng = new LatLng(latLngVector[0], latLngVector[1]);
		
		LatLng latLng = new LatLng(0,0);
		
		Domicile domicile = new Domicile(province, city, address, latLng);
		
		User temp = new User(user, name, surname, email, division, responsible, role, fuelType, gramsOfCO2, domicile, smartDays, consent);
		if(userDao.isPresent(temp))
			return;
		if(!temp.isConsent()) 
			return;
			
		userDao.addNewUser(temp);
		dataDao.addNewData(temp, this.calculateKmsSavedADay(temp), this.calculateGramsOfCO2SavedADay(temp), this.calculateKmsSavedAYear(temp), this.calculateGramsOfCO2SavedAYear(temp));
	}
	
	public void editExistingUser(String user, String name, String surname, String email, String division, String responsible, String role, 
			String fuelType, double gramsOfCO2, String province, String city, String address, int smartDays, boolean consent) {
		
		// List<Double> latLnglist = townDao.getLatLngFromCity(city);
		// LatLng latLng = new LatLng(latLnglist.get(0), latLnglist.get(1));
		
		// double[] latLngVector = getLatLngFromAddress(province, city, address);
		// LatLng latLng = new LatLng(latLngVector[0], latLngVector[1]);
		
		LatLng latLng = new LatLng(0,0);
		
		Domicile domicile = new Domicile(province, city, address, latLng);
		
		User temp = new User(user, name, surname, email, division, responsible, role, fuelType, gramsOfCO2, domicile, smartDays, consent);
		if(!temp.isConsent())
			return;
		
		userDao.editExistingUser(temp);
		dataDao.editExistingData(temp, this.calculateKmsSavedADay(temp), this.calculateGramsOfCO2SavedADay(temp), this.calculateKmsSavedAYear(temp), this.calculateGramsOfCO2SavedAYear(temp));
	}
	
	private double[] getLatLngFromAddress(String province, String city, String address) {
		double[] result = null;
		String fullAddress = address + " " + city + " " + province;
		
		/* 
		 * algoritmo da implementare (Prova Api)
		 */
		
		return result;
	}

	public List<User> getUsersList() {
		List<User> temp = userDao.getUserList();
		Collections.sort(temp, new UserComparatorByUser());
		return temp;
	}
	
	public List<User> getUsersListFilteredByDivision(String division) {
		List<User> temp = userDao.getUserList();
		List<User> result = new ArrayList<>();
		for(User u : temp) {
			if(u.getDivision().equals(division))
				result.add(u);
		}
		Collections.sort(result, new UserComparatorByUser());
		
		return result;
	}
	
	public List<Data> getDataList() {
		List<Data> temp = dataDao.getDataList();
		Collections.sort(temp, new DataComparatorByUser());
		return temp;
	}
	
	public List<Data> getDataListOrderedByGramsOfCO2SavedADay(){
		List<Data> temp = dataDao.getDataList();
		Collections.sort(temp, new DataComparatorByGramsOfCO2SavedADay());
		return temp;
	}
	
	public List<Data> getDataListOrderedByGramsOfCO2SavedAYear(){
		List<Data> temp = dataDao.getDataList();
		Collections.sort(temp, new DataComparatorByGramsOfCO2SavedAYear());
		return temp;
	}
	
	public List<Data> getDataListOrderedByKmsSavedADay() {
		List<Data> temp = dataDao.getDataList();
		Collections.sort(temp, new DataComparatorByKmsSavedADay());
		return temp;
	}
	
	public List<Data> getDataListOrderedByKmsSavedAYear() {
		List<Data> temp = dataDao.getDataList();
		Collections.sort(temp, new DataComparatorByKmsSavedAYear());
		return temp;
	}
	


	public List<String> getDivisions() {
		return new ArrayList<>(this.divisionsResponsibleMap.keySet());
	}

	public List<String> getFuelTypes() {
		return this.fuelTypes;
	}

	public List<String> getProvinces() {
		return this.townDao.getProvincesList();
	}

	public List<String> getRoles() {
		return this.roles;
	}

	public List<String> getEmailDomains() {
		return this.emailDomains;
	}

	public List<String> getCitiesFromProvince(String province) {
		return this.townDao.getCitiesFromProvince(province);
	}

	public List<String> getResponsibleFromDivision(String division) {
		List<String> result = new ArrayList<>();
		result.add(this.divisionsResponsibleMap.get(division));
		return result;
	}

	public LatLng getLatLngFromCity(String city) {
		List<Double> temp = this.townDao.getLatLngFromCity(city);
		return new LatLng(temp.get(0), temp.get(1));
	}

	public boolean isUserPresent(String username) {
		User user = new User(username, null, null, null, null, null, null, null, 0, null, 0, false);
		return this.userDao.isPresent(user);
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public User getUserFromUsername(String user) {
		return this.userDao.getUserFromUsername(user);
	}

	public Data getDataFromUsername(String user) {
		return this.dataDao.getDataFromUsername(user);
	}

	
	
	// COMPARATORS
	
	public class UserComparatorByUser implements Comparator<User> {
		@Override
		public int compare(User u1, User u2) {
			return u1.getUser().compareTo(u2.getUser());
		}
	}
	
	public class DataComparatorByUser implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return d1.getUser().getUser().compareTo(d2.getUser().getUser());
		}
	}
	
	public class DataComparatorByGramsOfCO2SavedADay implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return (int) (d1.getGramsOfCO2SavedADay()-d2.getGramsOfCO2SavedADay());
		}
	}
	
	public class DataComparatorByGramsOfCO2SavedAYear implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return (int) (d1.getGramsOfCO2SavedAYear()-d2.getGramsOfCO2SavedAYear());
		}
	}
	
	public class DataComparatorByKmsSavedADay implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return (int) (d1.getKmsSavedADay()-d2.getKmsSavedADay());
		}
	}
	
	public class DataComparatorByKmsSavedAYear implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return (int) (d1.getKmsSavedAYear()-d2.getKmsSavedAYear());
		}
	}
	
}
