package model;

public class User {
	
	private String user, name, surname, email, division, responsible, role;
	private String fuelType;
	private double gramsOfCO2;
	private Domicile domicile;
	private int smartDays;
	private boolean consent;
	
	public User(String user, String name, String surname, String email, String division, String responsible, String role, 
			String fuelType, double gramsOfCO2, Domicile domicile, int smartDays, boolean consent) {
		super();
		this.user = user;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.division = division;
		this.responsible = responsible;
		this.role = role;
		this.fuelType = fuelType;
		this.gramsOfCO2 = gramsOfCO2;
		this.domicile = domicile;
		this.smartDays = smartDays;
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

	public String getDivision() {
		return division;
	}
	
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	
	public String getResponsible() {
		return responsible;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Domicile getDomicile() {
		return domicile;
	}

	public void setDomicile(Domicile domicile) {
		this.domicile = domicile;
	}

	public int getSmartDays() {
		return smartDays;
	}

	public void setSmartDays(int smartDays) {
		this.smartDays = smartDays;
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

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s %s, %d days", user, division, role, fuelType, domicile.getTown(), domicile.getProvince(), smartDays);
	}
	
}
