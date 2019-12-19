package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.DialogController;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;

public class ConnectDB {
	
	private static Model model = new Model();
	private static String jdbcURL = "jdbc:h2:" + "//bosch.com/dfsrb/DfsIT/LOC/Tn/Tn_G_TOALL/Smart Working Analysis/h2db/pws_db" + model.getYearSelected() + ";IFEXISTS=TRUE";

	public static Connection connection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL, "pws", "pws1234");
			
		} catch (SQLException sql) {
			System.err.println("Unable to connect to DB");	
			try {
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Unable to connect to DB.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				
			} catch (IOException io) {
				io.printStackTrace();
			}
			
			throw new RuntimeException(sql);
		}
		
		return conn;
	}

}
