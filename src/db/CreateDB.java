package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateDB {

	public static void main(String[] args) throws Exception {

		Class.forName("org.h2.Driver").newInstance();
		@SuppressWarnings("unused")
		Connection conn = DriverManager.getConnection("jdbc:h2:" + "./src/db/pws_db", "pws", "pws1234");
		System.out.println("DB created!");
	}

}
