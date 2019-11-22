package model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		model.editExistingUser("PAO1MI", "Omar", "Panebianco", "fixed-term.omar.panebianco@it.bosch.com", "AA", "Tolle", "External", "Diesel", 119, "TO", "Torino", 20, true);
		System.out.println(model.getUsersList());
		System.out.println(model.getDataList());

	}

}
