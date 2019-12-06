package db;

public class TestDB {

	public static void main(String[] args) {

		UserDao dao = new UserDao();
		
		System.out.println(dao.getUserList());
	}

}
