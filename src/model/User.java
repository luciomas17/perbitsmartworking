package model;

public class User {
	
	private String user, name, surname, email, divisionOrFunction, location;
	private String fuelType;
	private double gramsOfCO2;
	private int smartDays;
	private double kmsSaved;
	private int timeSaved;
	private boolean consent;
	
	public User(String user, String name, String surname, String email, String divisionOrFunction, String location,
			String fuelType, double gramsOfCO2, int smartDays, double kmsSaved, int timeSaved, boolean consent) {
		super();
		this.user = user;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.divisionOrFunction = divisionOrFunction;
		this.location = location;
		this.fuelType = fuelType;
		this.gramsOfCO2 = gramsOfCO2;
		this.smartDays = smartDays;
		this.kmsSaved = kmsSaved;
		this.timeSaved = timeSaved;
		this.consent = consent;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDivisionOrFunction() {
		return divisionOrFunction;
	}

	public void setDivisionOrFunction(String divisionOrFunction) {
		this.divisionOrFunction = divisionOrFunction;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double getGramsOfCO2() {
		return gramsOfCO2;
	}

	public void setGramsOfCO2(double gramsOfCO2) {
		this.gramsOfCO2 = gramsOfCO2;
	}

	public int getSmartDays() {
		return smartDays;
	}

	public void setSmartDays(int smartDays) {
		this.smartDays = smartDays;
	}

	public double getKmsSaved() {
		return kmsSaved;
	}

	public void setKmsSaved(double kmsSaved) {
		this.kmsSaved = kmsSaved;
	}

	public int getTimeSaved() {
		return timeSaved;
	}

	public void setTimeSaved(int timeSaved) {
		this.timeSaved = timeSaved;
	}

	public boolean isConsent() {
		return consent;
	}

	public void setConsent(boolean consent) {
		this.consent = consent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
