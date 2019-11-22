package model;

public class Data {
	
	private User user;
	private double kmsSavedADay, gramsOfCO2SavedADay, kmsSavedAYear, gramsOfCO2SavedAYear;
	
	public Data(User user, double kmsSavedADay, double gramsOfCO2SavedADay, double kmsSavedAYear,
			double gramsOfCO2SavedAYear) {
		super();
		this.user = user;
		this.kmsSavedADay = kmsSavedADay;
		this.gramsOfCO2SavedADay = gramsOfCO2SavedADay;
		this.kmsSavedAYear = kmsSavedAYear;
		this.gramsOfCO2SavedAYear = gramsOfCO2SavedAYear;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getKmsSavedADay() {
		return kmsSavedADay;
	}

	public void setKmsSavedADay(double kmsSavedADay) {
		this.kmsSavedADay = kmsSavedADay;
	}

	public double getGramsOfCO2SavedADay() {
		return gramsOfCO2SavedADay;
	}

	public void setGramsOfCO2SavedADay(double gramsOfCO2SavedADay) {
		this.gramsOfCO2SavedADay = gramsOfCO2SavedADay;
	}

	public double getKmsSavedAYear() {
		return kmsSavedAYear;
	}

	public void setKmsSavedAYear(double kmsSavedAYear) {
		this.kmsSavedAYear = kmsSavedAYear;
	}

	public double getGramsOfCO2SavedAYear() {
		return gramsOfCO2SavedAYear;
	}

	public void setGramsOfCO2SavedAYear(double gramsOfCO2SavedAYear) {
		this.gramsOfCO2SavedAYear = gramsOfCO2SavedAYear;
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
		Data other = (Data) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s, %f kms, %f grams, %f kms, %f grams", user.getUser(), kmsSavedADay, gramsOfCO2SavedADay, kmsSavedAYear, gramsOfCO2SavedAYear);
	}
	
}
