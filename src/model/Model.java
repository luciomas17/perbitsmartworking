package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import db.DataDao;
import db.UserDao;

public class Model {
	
	private UserDao userDao;
	private DataDao dataDao;
	private List<String> divisions, functions, locations, fuelTypes, emailDomains, analysis;
	private boolean overwrite;
	private int perbitEmployees;
	
	public Model() {
		this.userDao = new UserDao();
		this.dataDao = new DataDao();
		this.divisions = new ArrayList<>();
		addItemsToDivisions();
		this.functions = new ArrayList<>();
		addItemsToFunctions();
		this.locations = new ArrayList<>();
		addItemsToLocations();
		this.fuelTypes = new ArrayList<>();
		addItemsToFuelTypes();
		this.emailDomains = new ArrayList<>();
		addItemsToEmailDomains();
		this.analysis = new ArrayList<>();
		addItemsToAnalysis();
		this.overwrite = false;
		this.perbitEmployees = 150;
	}

	public int getPerbitEmployees() {
		return this.perbitEmployees;
	}
	
	private void addItemsToAnalysis() {
		this.analysis.add("Partecipation");
		this.analysis.add("CO₂ saved");
		this.analysis.add("Time saved");
	}

	private void addItemsToLocations() {
		this.locations.add("Torino");
		this.locations.add("Milano");
	}

	private void addItemsToFunctions() {
		this.functions.add("Controlling/Cash Management");
		this.functions.add("Quality");
		this.functions.add("Logistics");
		this.functions.add("EO/SO");
	}

	private void addItemsToDivisions() {
		this.divisions.add("PS");
		this.divisions.add("CC");
		this.divisions.add("ED");
		this.divisions.add("CM");
		this.divisions.add("AE");
		this.divisions.add("AA");
		this.divisions.add("AS");
		this.divisions.add("CS");
	}

	private void addItemsToEmailDomains() {
		this.emailDomains.add("bosch.com");
		this.emailDomains.add("it.bosch.com");
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

	public double calculateKmsSavedAYear(User user) {
		return user.getKmsSaved() * user.getSmartDays();
	}
	
	public double calculateGramsOfCO2SavedADay(User user) {
		return user.getKmsSaved() * user.getGramsOfCO2();
	}
	
	public double calculateGramsOfCO2SavedAYear(User user) {
		return user.getKmsSaved() * user.getGramsOfCO2() * user.getSmartDays();
	}
	
	public void addNewUser(String user, String name, String surname, String email, String divisionOrFunction, String location, String fuelType, double gramsOfCO2, int smartDays, double kmsSaved, int timeSaved, boolean consent) {
		
		User temp = new User(user, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent);
		if(userDao.isPresent(temp))
			return;
		if(!temp.isConsent()) 
			return;
			
		userDao.addNewUser(temp);
		dataDao.addNewData(temp, temp.getKmsSaved(), this.calculateGramsOfCO2SavedADay(temp), this.calculateKmsSavedAYear(temp), this.calculateGramsOfCO2SavedAYear(temp));
	}
	
	public void editExistingUser(String user, String name, String surname, String email, String divisionOrFunction, String location, String fuelType, double gramsOfCO2, int smartDays, double kmsSaved, int timeSaved, boolean consent) {
		
		User temp = new User(user, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent);
		if(!temp.isConsent())
			return;
		
		userDao.editExistingUser(temp);
		dataDao.editExistingData(temp, temp.getKmsSaved(), this.calculateGramsOfCO2SavedADay(temp), this.calculateKmsSavedAYear(temp), this.calculateGramsOfCO2SavedAYear(temp));
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
			if(u.getDivisionOrFunction().equals(division))
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
		return this.divisions;
	}

	public List<String> getFuelTypes() {
		return this.fuelTypes;
	}

	public List<String> getEmailDomains() {
		return this.emailDomains;
	}
	
	public List<String> getLocations() {
		return this.locations;
	}

	public List<String> getFunctions() {
		return this.functions;
	}

	public boolean isUserPresent(String username) {
		User user = new User(username, null, null, null, null, null, null, 0, 0, 0, 0, false);
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
			return (int) (d2.getGramsOfCO2SavedADay()-d1.getGramsOfCO2SavedADay());
		}
	}
	
	public class DataComparatorByGramsOfCO2SavedAYear implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return (int) (d2.getGramsOfCO2SavedAYear()-d1.getGramsOfCO2SavedAYear());
		}
	}
	
	public class DataComparatorByKmsSavedADay implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return (int) (d2.getKmsSavedADay()-d1.getKmsSavedADay());
		}
	}
	
	public class DataComparatorByKmsSavedAYear implements Comparator<Data> {
		@Override
		public int compare(Data d1, Data d2) {
			return (int) (d2.getKmsSavedAYear()-d1.getKmsSavedAYear());
		}
	}

	public List<String> getAnalysis() {
		return this.analysis;
	}

	public List<String> getDivisionsAndFunction() {
		List<String> result = new ArrayList<>();
		result.addAll(this.divisions);
		Collections.sort(result);
		List<String> functions = this.functions;
		Collections.sort(functions);
		result.addAll(functions);
		
		return result;
	}

}
