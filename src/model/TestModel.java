package model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		model.clearDatabase();
		
		System.out.println(model.getUsersList());
		System.out.println(model.getDataList());

	}

}
