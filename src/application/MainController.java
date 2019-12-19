package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Data;
import model.Model;
import model.User;

public class MainController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgViewANU;

    @FXML
    private TextField txtANUUser;

    @FXML
    private TextField txtANUName;

    @FXML
    private TextField txtANUSurname;

    @FXML
    private TextField txtANUEmail;

    @FXML
    private ComboBox<String> boxANUEmail;

    @FXML
    private ComboBox<String> boxANUDivision;

    @FXML
    private ComboBox<String> boxANULocation;

    @FXML
    private ComboBox<String> boxANUFunction;

    @FXML
    private TextField txtANUSmartDays;

    @FXML
    private TextField txtANUTimeSaved;

    @FXML
    private TextField txtANUKmsSaved;

    @FXML
    private TextField txtANUCO2emissions;

    @FXML
    private ComboBox<String> boxANUFuelType;

    @FXML
    private Hyperlink linkAutoData;

    @FXML
    private CheckBox boxANUConsent;

    @FXML
    private HBox hboxUsersFinder;

    @FXML
    private TextField txtUsersUser;

    @FXML
    private ComboBox<String> boxUsersDivisions;

    @FXML
    private ImageView imgViewUsers;

    @FXML
    private StackPane paneUsersWV;

    @FXML
    private HBox hboxDataFinder;

    @FXML
    private TextField txtDataUser;

    @FXML
    private ImageView imgViewData;

    @FXML
    private StackPane paneDataWV;

    @FXML
    private ComboBox<String> boxAnalysis;

    @FXML
    private ImageView imgViewAnalysis;

    @FXML
    private StackPane paneAnalysis;

    @FXML
    private TextField txtAdmin;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private VBox vboxAdmin;
    
    @FXML
    private Text txtANUYear;
    
    @FXML
    private ComboBox<String> boxYears;
    
    @FXML
    private Hyperlink linkNextGreenCar;
	
	private Model model;

    @FXML
    void doANUReset(ActionEvent event) {
    	this.txtANUUser.clear();
    	this.txtANUName.clear();
    	this.txtANUSurname.clear();
    	this.txtANUEmail.clear();
    	this.boxANUEmail.getSelectionModel().clearSelection();
    	this.boxANUDivision.getSelectionModel().clearSelection();
    	this.boxANUFunction.getSelectionModel().clearSelection();
    	this.boxANULocation.getSelectionModel().clearSelection();
    	this.txtANUSmartDays.clear();
    	this.txtANUKmsSaved.clear();
    	this.txtANUTimeSaved.clear();
    	this.boxANUFuelType.getSelectionModel().clearSelection();
    	this.txtANUCO2emissions.clear();
    	this.boxANUConsent.setSelected(false);
    }

	@FXML
    void doANUSubmit(ActionEvent event) {
    	String user = this.txtANUUser.getText().toUpperCase();
    	if(user.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your username please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	String name = this.txtANUName.getText().toUpperCase();
    	    	
    	String surname = this.txtANUSurname.getText().toUpperCase();
    	    	
    	String email = this.txtANUEmail.getText().toLowerCase();
    	if(email.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your email please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	email += "@";
    	if(this.boxANUEmail.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your email domain please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else
    		email += this.boxANUEmail.getSelectionModel().getSelectedItem();
    	
    	String divisionOrFunction = "";
    	if(!this.boxANUDivision.getSelectionModel().isEmpty() && this.boxANUDivision.getSelectionModel().getSelectedItem().equals(""))
    		this.boxANUDivision.getSelectionModel().clearSelection();
    	if(!this.boxANUFunction.getSelectionModel().isEmpty() && this.boxANUFunction.getSelectionModel().getSelectedItem().equals(""))
    		this.boxANUFunction.getSelectionModel().clearSelection();
    	if(!this.boxANUDivision.getSelectionModel().isEmpty() && !this.boxANUFunction.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select only one between division and function options please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if(this.boxANUDivision.getSelectionModel().isEmpty() && this.boxANUFunction.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your division or your function please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	if(!this.boxANUDivision.getSelectionModel().isEmpty())
    		divisionOrFunction = this.boxANUDivision.getSelectionModel().getSelectedItem();
    	else
    		divisionOrFunction = this.boxANUFunction.getSelectionModel().getSelectedItem();
    	
    	String location = "";
    	if(this.boxANULocation.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your location please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
    		location = this.boxANULocation.getSelectionModel().getSelectedItem();
    	
    	int smartDays = 0;
    	if(this.txtANUSmartDays.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your days of smart count in " + model.getYearSelected() + " please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
			try {
				smartDays = Integer.parseInt(this.txtANUSmartDays.getText());
			} catch (NumberFormatException e1) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("Error: incorrect days of smart number format.");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.setResizable(false);
					window.show();
					return;
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				e1.printStackTrace();
			}
    	}
    	
    	double kmsSaved = 0;
    	if(this.txtANUKmsSaved.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type average kms saved a day please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
			try {
				kmsSaved = Double.parseDouble(this.txtANUKmsSaved.getText());
			} catch (NumberFormatException e1) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("Error: incorrect kms number format.");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.setResizable(false);
					window.show();
					return;
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				e1.printStackTrace();
			}
    	}
    	
    	int timeSaved = 0;
    	if(this.txtANUTimeSaved.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type average minutes saved a day please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
			try {
				timeSaved = Integer.parseInt(this.txtANUTimeSaved.getText());
			} catch (NumberFormatException e1) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("Error: incorrect time number format.");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.setResizable(false);
					window.show();
					return;
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				e1.printStackTrace();
			}
    	}
    	
    	String fuelType = "";
    	if(this.boxANUFuelType.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your car's fuel type please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
			fuelType = this.boxANUFuelType.getSelectionModel().getSelectedItem();
    	
    	double gramsOfCO2 = 0;
    	if(this.txtANUCO2emissions.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your car's CO₂ emissions please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
			try {
				gramsOfCO2 = Double.parseDouble(this.txtANUCO2emissions.getText());
			} catch (NumberFormatException e1) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("Error: incorrect CO₂ emissions number format.");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.setResizable(false);
					window.show();
					return;
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				e1.printStackTrace();
			}
    	}
    	
    	boolean consent = false;
    	if(!this.boxANUConsent.isSelected()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("We need you to give your consent to process these sensitive data to continue.\n"
						+ "If you agree with this, check the box please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
    		consent = true;
    	
    	if(model.isUserPresent(user)) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog2FXML.fxml"));
				BorderPane root = loader.load();
				Dialog2Controller controller = loader.getController();
				controller.setTxtDialog("User already exists.\n"
						+ "Do you want to overwrite existing data?");
				controller.setModel(model);
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.showAndWait();
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    		
    		if(model.isOverwrite()) {
				model.editExistingUser(user, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("User updated!");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.setResizable(false);
					window.show();
		
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
    	} else {
    		model.addNewUser(user, name, surname, email, divisionOrFunction, location, fuelType, gramsOfCO2, smartDays, kmsSaved, timeSaved, consent);
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("User added!");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
    	LocalDate date = LocalDate.now();
    	LocalTime time = LocalTime.now();
    	final String from = "Perbit.CO2smartworking@it.bosch.com"; 
	    final String host = "rb-smtp-int.bosch.com";
    	String to = "fixed-term.omar.panebianco@it.bosch.com"; //HR office in the future
		String cc = email;
	    String subject = "Perbit CO2 Smart Working - Consent of processing sensisive data for user [" + user + "]";
	    String body = "[THIS IS AN AUTOMATED MESSAGE - PLEASE DO NOT REPLY DIRECTLY TO THIS EMAIL]\n\n"
	    		+ "To the attention of PERBIT's HR office,\n\n"
	    		+ "The user " + user + " has knowingly given his/her consent to process his/her sensitive data to Perbit CO2 Smart Working application by checking the corresponding checkbox.\n\n"
	    		+ "This happened\non: " + date + "\nat: " + time + "\n\n"
	    		+ "Thanks.\n\n"
	    		+ "[THIS IS AN AUTOMATED MESSAGE - PLEASE DO NOT REPLY DIRECTLY TO THIS EMAIL]";
	  
	    Properties properties = System.getProperties();  
	    properties.setProperty("mail.smtp.host", host);  
	    Session session = Session.getDefaultInstance(properties);  
	  
	    try {  
	    	MimeMessage message = new MimeMessage(session);  
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc)); 
	        message.setSubject(subject);  
	        message.setText(body);
	        Transport.send(message);
	  
	    } catch (MessagingException mex) {
	    	mex.printStackTrace();
	    }
	    
	    doReload(null);
    }

    @FXML
    void doDataFilterByCO2SavedADay(ActionEvent event) {
    	this.paneDataWV.getChildren().clear();
    	List<Data> temp = model.getDataListOrderedByGramsOfCO2SavedADay();
    	WebView wv = new WebView();
		WebEngine we = wv.getEngine();
		String content = generateHTMLForData(temp);
		we.loadContent(content, "text/html");
		this.paneDataWV.getChildren().add(wv);
    }

    @FXML
    void doDataFilterByCO2SavedAYear(ActionEvent event) {
    	this.paneDataWV.getChildren().clear();
    	List<Data> temp = model.getDataListOrderedByGramsOfCO2SavedAYear();
    	WebView wv = new WebView();
		WebEngine we = wv.getEngine();
		String content = generateHTMLForData(temp);
		we.loadContent(content, "text/html");
		this.paneDataWV.getChildren().add(wv);
    }

    @FXML
    void doDataFilterByKmsSavedADay(ActionEvent event) {
    	this.paneDataWV.getChildren().clear();
    	List<Data> temp = model.getDataListOrderedByKmsSavedADay();
    	WebView wv = new WebView();
		WebEngine we = wv.getEngine();
		String content = generateHTMLForData(temp);
		we.loadContent(content, "text/html");
		this.paneDataWV.getChildren().add(wv);
    }

    @FXML
    void doDataFilterByKmsSavedAYear(ActionEvent event) {
    	this.paneDataWV.getChildren().clear();
    	List<Data> temp = model.getDataListOrderedByKmsSavedAYear();
    	WebView wv = new WebView();
		WebEngine we = wv.getEngine();
		String content = generateHTMLForData(temp);
		we.loadContent(content, "text/html");
		this.paneDataWV.getChildren().add(wv);
    }

    @FXML
    void doDataFindUserByUsername(ActionEvent event) {
    	String user = this.txtDataUser.getText().toUpperCase();
    	
    	if(user.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type an username please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
    	Data result = model.getDataFromUsername(user);
    	if(result == null) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog(user + " not found.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	else {
    		this.paneDataWV.getChildren().clear();
	    	List<Data> temp = new ArrayList<>();
	    	temp.add(result);
	    	WebView wv = new WebView();
			WebEngine we = wv.getEngine();
			String content = generateHTMLForData(temp);
			we.loadContent(content, "text/html");
			this.paneDataWV.getChildren().add(wv);
    	}
    }
    
    @FXML
    void doReload(ActionEvent event) {
    	this.paneDataWV.getChildren().clear();
    	this.addItemsToWVDataOutput();
    	this.txtDataUser.clear();
    	
    	this.paneUsersWV.getChildren().clear();
    	this.addItemsToWVUsersOutput();
    	this.txtUsersUser.clear();
    	this.boxUsersDivisions.getSelectionModel().clearSelection();
    	
    	doViewAnalysis(null);
    }

    @FXML
    void doUsersFilterByDivision(ActionEvent event) {
    	if(this.boxUsersDivisions.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select a division please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
    	String division = this.boxUsersDivisions.getSelectionModel().getSelectedItem();
    	List<User> result = model.getUsersListFilteredByDivision(division);
    	if(result.isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("No user found for " + division + " division.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	else {
    		this.paneUsersWV.getChildren().clear();
	    	WebView wv = new WebView();
			WebEngine we = wv.getEngine();
			String content = generateHTMLForUsers(result);
			we.loadContent(content, "text/html");
			this.paneUsersWV.getChildren().add(wv);
    	}
    }

    @FXML
    void doUsersFindUserByUsername(ActionEvent event) {
    	String user = this.txtUsersUser.getText().toUpperCase();
    	
    	if(user.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type an username please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
    	User result = model.getUserFromUsername(user);
    	if(result == null) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog(user + " not found.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
    		this.paneUsersWV.getChildren().clear();
	    	List<User> temp = new ArrayList<>();
	    	temp.add(result);
	    	WebView wv = new WebView();
			WebEngine we = wv.getEngine();
			String content = generateHTMLForUsers(temp);
			we.loadContent(content, "text/html");
			this.paneUsersWV.getChildren().add(wv);
    	}
    }
    
	@FXML
	void doViewAnalysis(ActionEvent event) {
		this.paneAnalysis.getChildren().clear();
		
		String analysis = this.boxAnalysis.getSelectionModel().getSelectedItem();
		VBox vb = new VBox();
		vb.setPadding(new Insets(10, 10, 10, 10));
		vb.prefHeightProperty().bind(this.paneAnalysis.heightProperty());
		vb.prefWidthProperty().bind(this.paneAnalysis.widthProperty());
		vb.setSpacing(10);
		
		BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#86b225"), BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT);
		Border border = new Border(borderStroke);
		
		if(analysis.equals("Partecipation")) {	
			int perbitEmployees = model.getPerbitEmployees();
			int users = model.getUsersList().size();
			
			double partecipation = ((double) users / (double) perbitEmployees);
			if(partecipation > 1)
				partecipation = 1;
			
			//PieChart
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data(String.format("Partecipants %.1f", partecipation * 100) + "%", partecipation),
				new PieChart.Data(String.format("Not partecipants %.1f", (1 - partecipation) * 100) + "%", 1 - partecipation));
			
			PieChart pieChart = new PieChart(pieChartData);
			pieChart.setTitle("Partecipation overview");
			pieChart.setLegendVisible(false);
			pieChart.setBorder(border);
			pieChart.setStartAngle(90);
			
			pieChart.getData().get(0).getNode().setStyle("-fx-pie-color: #86b225;");
			pieChart.getData().get(1).getNode().setStyle("-fx-pie-color: #525F6B;");
			
			vb.getChildren().add(pieChart);			
			
			//BarChart
	        CategoryAxis xAxis = new CategoryAxis();
	        NumberAxis yAxis = new NumberAxis(0, 30, 5);
	        BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
	        barChart.setTitle("Partecipants per division / function");
	        barChart.setLegendVisible(false);
	        barChart.setBorder(border);
	        barChart.setBarGap(0);
	        xAxis.setLabel("Division / Function");
	        yAxis.setLabel("Partecipant");
	        
	        Series<String, Number> series = new XYChart.Series<>();
	        for(String s : model.getDivisionsAndFunction()) {
		       	series.setName(s);
		        if(s.length() < 10)
		        	series.getData().add(new XYChart.Data<>(s, model.getUsersListFilteredByDivision(s).size()));
		        else {
		        	String name = s.substring(0, 11) + "...";
		        	series.getData().add(new XYChart.Data<>(name, model.getUsersListFilteredByDivision(s).size()));
		        }
	        }       
	        barChart.getData().add(series);
	        
	        for(int i = 0; i < model.getDivisionsAndFunction().size(); i ++)
	        	barChart.getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: #86b225;");
	        
	        vb.getChildren().add(barChart);	        
	        
		} else if(analysis.equals("CO₂ saved")) {
			//Pane
			StackPane paneCO2Saved = new StackPane();
			paneCO2Saved.setBorder(border);
			paneCO2Saved.setAlignment(Pos.CENTER);
			HBox hb = new HBox();
			hb.setAlignment(Pos.CENTER);
			hb.setSpacing(10);
			hb.setPadding(new Insets(10,10,10,10));
			
			VBox vb2 = new VBox();
			vb2.setAlignment(Pos.CENTER);
			vb2.setSpacing(20);
			vb2.setPadding(new Insets(10,10,10,10));
			vb2.setBorder(border);
			
			Label title = new Label("Total CO₂ saved in " + model.getYearSelected());
			title.setStyle("-fx-font-size: 18;");
			vb2.getChildren().add(title);
			
			double totalCO2Saved = 0;
			for(Data d : model.getDataList())
				totalCO2Saved += d.getGramsOfCO2SavedAYear();
			totalCO2Saved = totalCO2Saved/1000;
			Label CO2amount = new Label(String.format("%.2f Kgs", totalCO2Saved));
			CO2amount.setStyle("-fx-font-size: 36; -fx-font-weight: bold;");
			vb2.getChildren().add(CO2amount);
			hb.getChildren().add(vb2);
			
			VBox vb3 = new VBox();
			vb3.setAlignment(Pos.CENTER);
			vb3.setSpacing(10);
			vb3.setPadding(new Insets(10,10,10,10));
			vb3.setBorder(border);
			
			ImageView iv = new ImageView();
			iv.setImage(new Image(getClass().getResourceAsStream("forest.png")));
			iv.setFitHeight(100);
			iv.setFitWidth(100);
			vb3.getChildren().add(iv);
			int treeSaved = (int) (totalCO2Saved / model.getTreesPerKgOfCO2());
			title = new Label("You all contributed to save " + treeSaved + " trees this year!");
			title.setStyle("-fx-font-size: 18;");
			vb3.getChildren().add(title);
			hb.getChildren().add(vb3);
						
			paneCO2Saved.getChildren().add(hb);
			vb.getChildren().add(paneCO2Saved);
			
			//BarChart
	        CategoryAxis xAxis = new CategoryAxis();
	        NumberAxis yAxis = new NumberAxis(0, 5000, 500);
	        BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
	        barChart.setTitle("CO₂ saved per division / function");
	        barChart.setLegendVisible(false);
	        barChart.setBorder(border);
	        barChart.setBarGap(0);
	        xAxis.setLabel("Division / Function");
	        yAxis.setLabel("Kilograms of CO₂ saved");
	        
	        Series<String, Number> series = new XYChart.Series<>();
	        for(String s : model.getDivisionsAndFunction()) {
		       	series.setName(s);
		       	totalCO2Saved = 0;
		       	for(User u : model.getUsersListFilteredByDivision(s))
		       		totalCO2Saved += model.calculateGramsOfCO2SavedAYear(u);
		       	totalCO2Saved = totalCO2Saved/1000;
		 
		        if(s.length() < 10)
		        	series.getData().add(new XYChart.Data<>(s, totalCO2Saved));
		        else {
		        	String name = s.substring(0, 11) + "...";
		        	series.getData().add(new XYChart.Data<>(name, totalCO2Saved));
		        }
	        }       
	        barChart.getData().add(series);
	        
	        for(int i = 0; i < model.getDivisionsAndFunction().size(); i ++)
	        	barChart.getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: #86b225;");
	        
	        vb.getChildren().add(barChart);	    
	        
	        //Pane2
			StackPane bestDivision = new StackPane();
			bestDivision.setBorder(border);
			bestDivision.setAlignment(Pos.CENTER);
			HBox hb2 = new HBox();
			hb2.setAlignment(Pos.CENTER);
			hb2.setSpacing(20);
			hb2.setPadding(new Insets(10,0,10,0));
			
			Label title2 = new Label("Division / function which saved the most of CO₂ in " + model.getYearSelected() + "  ⇾");
			title2.setStyle("-fx-font-size: 18;");
			hb2.getChildren().add(title2);
			
			double bestCO2Saved = 0;
			Label bestDivisionOrFunction = new Label("");
			for(String s : model.getDivisionsAndFunction()) {
				totalCO2Saved = 0;
		       	for(User u : model.getUsersListFilteredByDivision(s))
		       		totalCO2Saved += model.calculateGramsOfCO2SavedAYear(u);

		       	if(totalCO2Saved > bestCO2Saved) {
		       		bestCO2Saved = totalCO2Saved;
		       		bestDivisionOrFunction.setText(s);
		       	}
			}
			
			bestDivisionOrFunction.setStyle("-fx-font-size: 36; -fx-font-weight: bold;");
			hb2.getChildren().add(bestDivisionOrFunction);
			
			bestDivision.getChildren().add(hb2);
			vb.getChildren().add(bestDivision);
			
		} else if(analysis.equals("Time saved")) {
			//Pane
			StackPane paneTimeSaved = new StackPane();
			paneTimeSaved.setBorder(border);
			paneTimeSaved.setAlignment(Pos.CENTER);
			VBox vb2 = new VBox();
			vb2.setAlignment(Pos.CENTER);
			vb2.setSpacing(20);
			vb2.setPadding(new Insets(10,0,10,0));
			
			Label title = new Label("Total time saved in " + model.getYearSelected());
			title.setStyle("-fx-font-size: 18;");
			vb2.getChildren().add(title);
			
			int totalTimeSaved = 0;
			for(User u : model.getUsersList())
				totalTimeSaved += u.getTimeSaved() * u.getSmartDays();
			
			int days = (totalTimeSaved / 60) / 24;
			int hours = (totalTimeSaved - ((days * 24) * 60)) / 60;
			int minutes = totalTimeSaved - ((days * 24) * 60) - (hours * 60);
			
			Label CO2amount = new Label(String.format("%d d  %d h  %d m", days, hours, minutes));
			CO2amount.setStyle("-fx-font-size: 36; -fx-font-weight: bold;");
			vb2.getChildren().add(CO2amount);
			
			paneTimeSaved.getChildren().add(vb2);
			vb.getChildren().add(paneTimeSaved);
			
			//BarChart
	        CategoryAxis xAxis = new CategoryAxis();
	        NumberAxis yAxis = new NumberAxis(0, 1000, 100);
	        BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
	        barChart.setTitle("Time saved per division / function");
	        barChart.setLegendVisible(false);
	        barChart.setBorder(border);
	        barChart.setBarGap(0);
	        xAxis.setLabel("Division / Function");
	        yAxis.setLabel("Hours saved");
	        
	        Series<String, Number> series = new XYChart.Series<>();
	        for(String s : model.getDivisionsAndFunction()) {
		       	series.setName(s);
		       	totalTimeSaved = 0;
		       	for(User u : model.getUsersListFilteredByDivision(s))
		       		totalTimeSaved += u.getTimeSaved() * u.getSmartDays();
		       	totalTimeSaved = totalTimeSaved/60;
		 
		        if(s.length() < 10)
		        	series.getData().add(new XYChart.Data<>(s, totalTimeSaved));
		        else {
		        	String name = s.substring(0, 11) + "...";
		        	series.getData().add(new XYChart.Data<>(name, totalTimeSaved));
		        }
	        }       
	        barChart.getData().add(series);
	        
	        for(int i = 0; i < model.getDivisionsAndFunction().size(); i ++)
	        	barChart.getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: #008ECF;");
	        
	        vb.getChildren().add(barChart);	    
	        
	        //Pane2
			StackPane bestDivision = new StackPane();
			bestDivision.setBorder(border);
			bestDivision.setAlignment(Pos.CENTER);
			HBox hb = new HBox();
			hb.setAlignment(Pos.CENTER);
			hb.setSpacing(20);
			hb.setPadding(new Insets(10,0,10,0));
			
			Label title2 = new Label("Division / function which saved the most of time in " + model.getYearSelected() + "  ⇾");
			title2.setStyle("-fx-font-size: 18;");
			hb.getChildren().add(title2);
			
			double bestTimeSaved = 0;
			Label bestDivisionOrFunction = new Label("");
			for(String s : model.getDivisionsAndFunction()) {
				totalTimeSaved = 0;
		       	for(User u : model.getUsersListFilteredByDivision(s))
		       		totalTimeSaved += u.getTimeSaved() * u.getSmartDays();

		       	if(totalTimeSaved > bestTimeSaved) {
		       		bestTimeSaved = totalTimeSaved;
		       		bestDivisionOrFunction.setText(s);
		       	}
			}
			
			bestDivisionOrFunction.setStyle("-fx-font-size: 36; -fx-font-weight: bold;");
			hb.getChildren().add(bestDivisionOrFunction);
			
			bestDivision.getChildren().add(hb);
			vb.getChildren().add(bestDivision);
			
		}
		
		this.paneAnalysis.getChildren().add(vb);
	}
    

	
	@FXML
    void doEnter(ActionEvent event) {
		if(this.txtAdmin.getText().equals(model.getAdmin()) && this.txtPassword.getText().equals(model.getPwdAdmin())) {
			this.vboxAdmin.setDisable(false);
			this.vboxAdmin.setOpacity(1);
			
		} else {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Wrong admin and/or password.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.setResizable(false);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

    }
	
	@FXML
    void doExit(ActionEvent event) {
		this.txtAdmin.clear();
		this.txtPassword.clear();
		
		this.vboxAdmin.setDisable(true);
		this.vboxAdmin.setOpacity(0);
		
		model.setAdminLogged(false);
		this.hboxUsersFinder.setDisable(true);
		this.hboxDataFinder.setDisable(true);
		doReload(null);
    }
	
	@FXML
    void doShowHideUsernames(ActionEvent event) {
		if(model.isAdminLogged()) {
			model.setAdminLogged(false);
			this.hboxUsersFinder.setDisable(true);
			this.hboxDataFinder.setDisable(true);
			
		} else if(!model.isAdminLogged()) {
			model.setAdminLogged(true);
			this.hboxUsersFinder.setDisable(false);
			this.hboxDataFinder.setDisable(false);
		}
		
		doReload(null);
	    
	    try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
			BorderPane root = loader.load();
			DialogController controller = loader.getController();
			controller.setTxtDialog("Done!");
			Parent content = root;
			Scene scene = new Scene(content);
			Stage window = new Stage();
			window.setScene(scene);
			window.setResizable(false);
			window.show();
			return;

		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
	
	@FXML
    void doClearDatabase(ActionEvent event) {
		model.clearDatabase();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
			BorderPane root = loader.load();
			DialogController controller = loader.getController();
			controller.setTxtDialog("Done!");
			Parent content = root;
			Scene scene = new Scene(content);
			Stage window = new Stage();
			window.setScene(scene);
			window.setResizable(false);
			window.show();
			return;

		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
	
    public void setModel(Model model) {
    	this.model = model;
    	addItemsToBoxANUEmail();
    	addItemsToBoxANUDivision();
    	addItemsToBoxANUFunction();
    	addItemsToBoxANULocation();
    	addItemsToBoxANUFuelType();
    	addItemsToBoxUsersDivisions();
    	setOnActionToLinkAutoData();
    	setOnActionToLinkNextGreenCar();
    	addItemsToWVUsersOutput();
    	addItemsToWVDataOutput();
    	addItemsToBoxAnalysis();
    	addPathToImgViews();
    	setTxtANUYear();
    }

	private void setTxtANUYear() {
		this.txtANUYear.setText("days (in " + model.getYearSelected() + ")");
	}

	private void addPathToImgViews() {
		this.imgViewANU.setImage(new Image(getClass().getResourceAsStream("eco.png")));
		this.imgViewUsers.setImage(new Image(getClass().getResourceAsStream("reload.png")));
		this.imgViewData.setImage(new Image(getClass().getResourceAsStream("reload.png")));
		this.imgViewAnalysis.setImage(new Image(getClass().getResourceAsStream("reload.png")));
	}

	private void addItemsToBoxAnalysis() {
		this.boxAnalysis.getItems().addAll(model.getAnalysis());
		this.boxAnalysis.getSelectionModel().select(1);
		doViewAnalysis(null);
	}

	private void addItemsToWVDataOutput() {
		WebView wv = new WebView();
		wv.prefHeightProperty().bind(this.paneDataWV.heightProperty());
		wv.prefWidthProperty().bind(this.paneDataWV.widthProperty());
		WebEngine we = wv.getEngine();
		String content = generateHTMLForData(model.getDataList());
		we.loadContent(content, "text/html");
		this.paneDataWV.getChildren().add(wv);
	}

	private String generateHTMLForData(List<Data> dataList) {
		String result = "";
		
		result += "<table width=95% style='font-family:Segoe UI; font-size:12px; text-align:center'>";
			result += "<tr style='color:#86b225; font-style:italic; font-weight:bold'>";
				result += "<td width=20% style='padding-bottom:10px' valign='bottom'>Username</td>";
				result += "<td width=12% style='padding-bottom:10px' valign='bottom'>Days of smart</td>";
				result += "<td width=17% style='padding-bottom:10px' valign='bottom'>Kms saved a day</td>";
				result += "<td width=17% style='padding-bottom:10px' valign='bottom'>Kilograms of C0₂ saved a day</td>";
				result += "<td width=17% style='padding-bottom:10px' valign='bottom'>Kms saved a year</td>";
				result += "<td width=17% style='padding-bottom:10px' valign='bottom'>Kilograms of C0₂ saved a year</td>";
			result += "</tr>";
			if(dataList.isEmpty()) {
				result += "<tr style='font-style:italic'>";
					result += "<td colspan=6>No data found.</td>";
				result += "</tr>";
			} else {
				for(Data d : dataList) {
					String username = d.getUser().getUser();
					if(!model.isAdminLogged()) {
						int usernameLenght = username.length();
						username = username.substring(0, 1);
						for(int i = 0; i < usernameLenght - 1; i ++)
							username += "*";
					}
					
					result += "<tr>";
						result += "<td>" + username + "</td>";
						result += "<td>" + d.getUser().getSmartDays() + "</td>";
						result += "<td>" + d.getKmsSavedADay() + "</td>";
						result += "<td>" + String.format("%.2f", d.getGramsOfCO2SavedADay()/1000) + "</td>";
						result += "<td>" + d.getKmsSavedAYear() + "</td>";
						result += "<td>" + String.format("%.2f", d.getGramsOfCO2SavedAYear()/1000) + "</td>";
					result += "</tr>";
				}
			}
		result += "</table>";
		
		return result;
	}

	private void addItemsToWVUsersOutput() {
		WebView wv = new WebView();
		wv.prefHeightProperty().bind(this.paneUsersWV.heightProperty());
		wv.prefWidthProperty().bind(this.paneUsersWV.widthProperty());
		WebEngine we = wv.getEngine();
		String content = generateHTMLForUsers(model.getUsersList());
		we.loadContent(content, "text/html");
		this.paneUsersWV.getChildren().add(wv);
	}

	private String generateHTMLForUsers(List<User> usersList) {
		String result = "";
		
		result += "<table width=95% style='font-family:Segoe UI; font-size:12px; text-align:center'>";
			result += "<tr style='color:#86b225; font-style:italic; font-weight:bold'>";
				result += "<td width=20% style='padding-bottom:10px' valign='bottom'>Username</td>";
				result += "<td width=35% style='padding-bottom:10px' valign='bottom'>Division / Function</td>";
				result += "<td width=20% style='padding-bottom:10px' valign='bottom'>Location</td>";
				result += "<td width=25% style='padding-bottom:10px' valign='bottom'>Fuel Type</td>";
			result += "</tr>";
			if(usersList.isEmpty()) {
				result += "<tr style='font-style:italic'>";
					result += "<td colspan=4>No user found.</td>";
				result += "</tr>";
			} else {
				for(User u : usersList) {
					String username = u.getUser();
					if(!model.isAdminLogged()) {
						int usernameLenght = username.length();
						username = username.substring(0, 1);
						for(int i = 0; i < usernameLenght - 1; i ++)
							username += "*";
					}
					
					result += "<tr>";
						result += "<td>" + username + "</td>";
						result += "<td>" + u.getDivisionOrFunction() + "</td>";
						result += "<td>" + u.getLocation() + "</td>";
						result += "<td>" + u.getFuelType() + "</td>";
					result += "</tr>";
				}
			}
		result += "</table>";
		
		return result;
	}

	private void setOnActionToLinkAutoData() {
		this.linkAutoData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	try {
					Desktop.getDesktop().browse(new URI("https://www.auto-data.net/en/"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
            }
        });
	}
	
	private void setOnActionToLinkNextGreenCar() {
		this.linkNextGreenCar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	try {
					Desktop.getDesktop().browse(new URI("https://www.nextgreencar.com/emissions/make-model/"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
            }
        });
	}

	private void addItemsToBoxUsersDivisions() {
		List<String> temp = model.getDivisions();
		temp.remove(0);
		Collections.sort(temp);
		List<String> temp2 = model.getFunctions();
		temp2.remove(0);
		Collections.sort(temp2);
		temp2.remove("Other");
		temp2.add("Other");
		this.boxUsersDivisions.getItems().addAll(temp);
		this.boxUsersDivisions.getItems().addAll(temp2);
	}

	private void addItemsToBoxANUFuelType() {
		List<String> temp = model.getFuelTypes();
		Collections.sort(temp);
		this.boxANUFuelType.getItems().addAll(temp);
	}

	private void addItemsToBoxANUDivision() {
		List<String> temp = model.getDivisions();
		Collections.sort(temp);
		temp.add(0, "");
		this.boxANUDivision.getItems().addAll(temp);
	}

	private void addItemsToBoxANUEmail() {
		List<String> temp = model.getEmailDomains();
		Collections.sort(temp);
		this.boxANUEmail.getItems().addAll(temp);
	}
	
    private void addItemsToBoxANULocation() {
    	List<String> temp = model.getLocations();
		Collections.sort(temp);
		this.boxANULocation.getItems().addAll(temp);
	}

	private void addItemsToBoxANUFunction() {
		List<String> temp = model.getFunctions();
		Collections.sort(temp);
		temp.add(0, "");
		temp.add("Other");
		this.boxANUFunction.getItems().addAll(temp);
	}
	
	@FXML
    void initialize() {
        assert imgViewANU != null : "fx:id=\"imgViewANU\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUUser != null : "fx:id=\"txtANUUser\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUName != null : "fx:id=\"txtANUName\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUSurname != null : "fx:id=\"txtANUSurname\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUEmail != null : "fx:id=\"txtANUEmail\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUEmail != null : "fx:id=\"boxANUEmail\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUDivision != null : "fx:id=\"boxANUDivision\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANULocation != null : "fx:id=\"boxANULocation\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUFunction != null : "fx:id=\"boxANUFunction\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUSmartDays != null : "fx:id=\"txtANUSmartDays\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUTimeSaved != null : "fx:id=\"txtANUTimeSaved\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUKmsSaved != null : "fx:id=\"txtANUKmsSaved\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUCO2emissions != null : "fx:id=\"txtANUCO2emissions\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUFuelType != null : "fx:id=\"boxANUFuelType\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert linkAutoData != null : "fx:id=\"linkAutoData\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUConsent != null : "fx:id=\"boxANUConsent\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert hboxUsersFinder != null : "fx:id=\"hboxUsersFinder\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtUsersUser != null : "fx:id=\"txtUsersUser\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxUsersDivisions != null : "fx:id=\"boxUsersDivisions\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert imgViewUsers != null : "fx:id=\"imgViewUsers\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert paneUsersWV != null : "fx:id=\"paneUsersWV\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert hboxDataFinder != null : "fx:id=\"hboxDataFinder\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtDataUser != null : "fx:id=\"txtDataUser\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert imgViewData != null : "fx:id=\"imgViewData\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert paneDataWV != null : "fx:id=\"paneDataWV\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxAnalysis != null : "fx:id=\"boxAnalysis\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert imgViewAnalysis != null : "fx:id=\"imgViewAnalysis\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert paneAnalysis != null : "fx:id=\"paneAnalysis\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtAdmin != null : "fx:id=\"txtAdmin\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert vboxAdmin != null : "fx:id=\"vboxAdmin\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUYear != null : "fx:id=\"txtANUyear\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxYears != null : "fx:id=\"boxYears\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert linkNextGreenCar != null : "fx:id=\"linkNextGreenCar\" was not injected: check your FXML file 'pswFXML.fxml'.";

    }
}
