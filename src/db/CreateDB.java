package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateDB {

	public static void main(String[] args) throws Exception {

		Class.forName("org.h2.Driver").newInstance();
		@SuppressWarnings("unused")
		Connection conn = DriverManager.getConnection("jdbc:h2:" + "//bosch.com/dfsrb/DfsIT/LOC/Tn/Tn_G_TOALL/Smart Working Analysis/h2db/pws_db" + "2020", "pws", "pws1234");
		System.out.println("DB created!");
	}

}
